/**
 * @author Samuel Nix, Summer Bronson
 *
 * Pathing algorithms
 */

package Path;

import Grid.GridPanel;
import Grid.Tile;

import java.util.ArrayList;

public class Path {
    private ArrayList<Tile> Path; // Used to store steps
    private GridPanel grid; // The grid
    private int gridDist[][]; // Dynprog go brrr

    // NOTE: All pathfinding is done in a single execution step
    // Technically, a sub-step, as each person paths on their BA.action() step.

    /**
     * Initializes a Path with the grid and start tile src
     * @param grid The grid
     * @param src A tile to start from
     */
    public Path(GridPanel grid, Tile src) {
        this.Path = new ArrayList<>();
        this.grid = grid;
        this.gridDist = new int[grid.viewableWidth][grid.viewableHeight];
        for (int i = 0; i < grid.viewableWidth; i++) {
            for(int j = 0; j < grid.viewableHeight; j++) {
                gridDist[i][j] = -2; // Denote undiscovered cells with -2
                if(!grid.getTile(i, j).isAccessible()) gridDist[i][j] = -1; // Denote inaccessible cells with -1
            }
        }
        gridDist[src.getX()][src.getY()] = 0; // Set our start to 0, base case
    }

    /**
     * Helper function for getting adjacent dists
     * @param x The x coord
     * @param y The y coord
     * @return The max distance at (x, y)
     */
    private int findMax(int x, int y) {
        int max = -1;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ( !inBounds(x + i, y + j) )
                    continue;
                if ((gridDist[x + i][y + j] > -1) && (gridDist[x + i][y + j] > max)) {
                    max = gridDist[x + i][y + j];
                }
            }
        }
        return max;
    }

    /**
     * Helper function to determine best-step prior
     * @param x The x coord
     * @param y The y coord
     * @return The least non-negative cell distance
     */
    private int findMinNeighbor(int x, int y) {
        int min = findMax(x, y);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ( !inBounds(x + i, y + j) )
                    continue;
                if ( (gridDist[x + i][y + j] > -1) && (gridDist[x + i][y + j] < min)) {
                    min = gridDist[x + i][y + j];
                }
            }
        }
        return min;
    }

    /**
     * Public method to determine path length
     * @return
     */
    public int getLength() {
        return this.Path == null ? -1 : this.Path.size();
    }

    /**
     * Public method to get this path's step list
     * @return
     */
    private ArrayList<Tile> getPath() {
        return this.Path;
    }

    /**
     * Method to determine the next tile to move to
     * Note that Path looks like (start/current_tile, ..., end)
     * At end, Path looks like (end, end) by definition
     *
     * Next step also handles re-pathing when the next tile in Path is occupied
     * @return A Tile to step to. Null at end, t otherwise
     */
    public Tile nextStep() {
        if (Path.size() < 1) { // If somehow Path.size is 0 or less (empty path)
            this.Path = null;
            return null;
        }
        if (Path.size() < 3) { // If we have finished pathing
            Tile t = Path.get(0);
            Path.remove(0);
            this.Path = null;
            return t;
        }
        Tile t = Path.get(1); // Get next step
        if (t.isAccessible()) { // Assuming it's valid, return that
            Path.remove(0);
            return t;
        }
        // T isn't accessible, repath
        int i = 2;
        for(;i < Path.size() && !Path.get(i).isAccessible(); i++); // Find first candidate
        if (i == Path.size()) return Path.get(0); // Return same tile if no candidates in remainder of path
        Path p = new Path(this.grid, Path.get(0)); // Make a new path from here to candidate
        p.findPath(Path.get(0), Path.get(i));
        if (p.getPath() == null) return Path.get(0); // If no path exists, return this tile
        p.getPath().remove(p.getPath().size()-1); // Prune start and end
        p.getPath().remove(0);
        for(int j = 1; j < i; j++) Path.remove(j); // Remove old (bad) path steps
        for (i = 0; i < p.getPath().size(); i++) Path.add(i + 1, p.getPath().get(i)); // Replace with repath
        t = Path.get(1);
        Path.remove(0);
        return t;
    }

    /**
     * This is used to allow cells to take a one-step roam to get out of the way of other cells (whenever possible)
     * This should resolve a few behaviors, like cells sitting in the same spot forever, or deadlocking
     * I don't think deadlocking is entirely eliminated though, just mostly not a problem.
     * @return A tile to step to
     */
    public Tile courtesyStep() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i == 0) && (j == 0)) continue; // Don't go to the same tile
                int x = Path.get(0).getX() + i;
                int y = Path.get(0).getY() + j;
                if (!inBounds(x,y)) continue; // Bound checking
                Tile t = grid.getTile(x, y);
                if (t.isAccessible()) { // If we can step there
                    Path.add(0, t); // Prepend it to the path
                    return t; // Return that tile
                }
            }
        }
        return Path.get(0); // No stepping, we're surrounded
    }

    /**
     * Function to build a path from src to dest
     * @param src A start tile
     * @param dest An end tile
     * @return True on success, false o/w
     */
    public boolean findPath(Tile src, Tile dest) {
        if (greedyBFS(src, dest)) { // Try GBFS
            buildPath(dest);
            return true;
        }
        square(src, dest); // Try Sam's method
        if (gridDist[dest.getX()][dest.getY()] > -1) buildPath(dest);
        if (Path.size() > 0) return true;
        Path = null;
        return false;
    }

    /**
     * A function to construct a list of steps from the solved path grid (dynprog go brrr)
     * @param dest The destination tile
     */
    private void buildPath(Tile dest) {
        Path.add(0, dest); // Prepend the end
        Tile x = dest; // Walk through the list, basically
        while (gridDist[x.getX()][x.getY()] > 0) { // As long as the src tile hasn't been found
            for(int i = -1; i < 2; i++) {
                for(int j = -1; j < 2; j++) { // Search in adjacent cells
                    if (!inBounds(x.getX() + i, x.getY() + j)) continue;
                    if (gridDist[x.getX() + i][x.getY() + j] == (gridDist[x.getX()][x.getY()] - 1)) { // If the previous step is found
                        Path.add(0, x);
                        x = grid.getTile(x.getX() + i, x.getY() + j); // Prepend and shift x to that previous cell
                        i = 2;
                        j = 2;
                    } else if ((x.getX() == dest.getX()) && (x.getY() == dest.getY())) break; // Break out on match
                }
            }
            if ((x.getX() == dest.getX()) && (x.getY() == dest.getY())) break; // Break out on match
        }
        Path.add(0, x); // Prepend x (x should have dist 0, i.e. Source)
    }


    /**
     * A helper function for bound checking
     * @param x The x coord
     * @param y The y coord
     * @return Whether the selected tile is in bounds or not
     */
    private boolean inBounds(int x, int y) {
        if (   ( (x < 0) || (x >= grid.viewableWidth) )   ||   (  (y < 0) || (y >= grid.viewableHeight)  )  ) return false;
        return true;
    }

    /**
     * A greedy breadth-first-search algorithm, searches in the direction the dest tile is in on the assumption there are not any blockages in the way
     * @param src The start tile
     * @param dest The end tile
     * @return True on success, false on failure
     */
    private Boolean greedyBFS(Tile src, Tile dest) {
        if (src == null || dest == null) return false; // Fail out on bad tiles
        if ((src.getX() == dest.getX()) && (src.getY() == dest.getY())) { // Quick return on simple sol'n
            return true;
        }
        int selection = getDir(src, dest); // Find direction
        int dist = gridDist[src.getX()][src.getY()]; // Set dist to this tile's distance
        if (selection % 2 == 0) { // Selection aligns to grid
            if (selection %4 == 0) { // Selection has same x coord
                for(int i = -1; i < 2; i++) { // Match the x coord, change y
                    if (selection == 0) {
                        if (!inBounds(src.getX() + 1, src.getY() + i) || i == 0) continue;
                        gridDist[src.getX() + 1][src.getY() + i] = grid.getTile(src.getX(), src.getY() + i).isAccessible() ? dist + 1 : -1;
                    } else {
                        if (!inBounds(src.getX() - 1, src.getY() + i) || i == 0) continue;
                        gridDist[src.getX() - 1][src.getY() + i] = grid.getTile(src.getX(), src.getY() + i).isAccessible() ? dist + 1 : -1;
                    }
                }
                if (selection == 0) {
                    return greedyBFS(grid.getTile(src.getX() + 1, src.getY()), dest);
                }
                return greedyBFS(grid.getTile(src.getX() - 1, src.getY()), dest);
            } else { // Selection has same y cord
                for(int i = -1; i < 2; i++) {
                    if (selection == 2) {
                        if (!inBounds(src.getX() + i, src.getY() + 1) || i == 0) continue;
                        gridDist[src.getX() + i][src.getY()] = grid.getTile(src.getX() + i, src.getY() + 1).isAccessible() ? dist + 1 : -1;
                    } else {
                        if (!inBounds(src.getX() + i, src.getY() - 1) || i == 0) continue;
                        gridDist[src.getX() + i][src.getY()] = grid.getTile(src.getX() + i, src.getY() - 1).isAccessible() ? dist + 1 : -1;
                    }
                }
                if (selection == 2) {
                    return greedyBFS(grid.getTile(src.getX(), src.getY() + 1), dest);
                }
                return greedyBFS(grid.getTile(src.getX(), src.getY() - 1), dest);
            }
        } else { // Diagonal
            if (selection > 4) { // need to check 6
                if (inBounds(src.getX(), src.getY() - 1))
                    gridDist[src.getX()][src.getY()-1] = grid.getTile(src.getX(), src.getY() - 1).isAccessible() ? dist + 1 : -1;
            } else { // Need to check 2
                if (inBounds(src.getX(), src.getY() + 1))
                    gridDist[src.getX()][src.getY()+1] = grid.getTile(src.getX(), src.getY() + 1).isAccessible() ? dist + 1 : -1;
            }

            if ((selection - 1) % 6 == 0) { // Need to check 0
                if (inBounds(src.getX() + 1, src.getY()))
                    gridDist[src.getX() + 1][src.getY()] = grid.getTile(src.getX() + 1, src.getY()).isAccessible() ? dist + 1 : -1;
            } else {
                if (inBounds(src.getX() - 1, src.getY()))
                    gridDist[src.getX() - 1][src.getY()] = grid.getTile(src.getX() - 1, src.getY()).isAccessible() ? dist + 1 : -1;
            }
            switch (selection) {
                case 1:
                    if (!inBounds(src.getX() + 1, src.getY() + 1)) return false;
                    gridDist[src.getX() + 1][src.getY() + 1] = grid.getTile(src.getX() + 1, src.getY() + 1).isAccessible() ?  dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() + 1, src.getY() + 1), dest);
                case 3:
                    if (!inBounds(src.getX() - 1, src.getY() + 1)) return false;
                    gridDist[src.getX() - 1][src.getY() + 1] = grid.getTile(src.getX() - 1, src.getY() + 1).isAccessible() ? dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() - 1, src.getY() + 1), dest);
                case 5:
                    if (!inBounds(src.getX() - 1, src.getY() - 1)) return false;
                    gridDist[src.getX() - 1][src.getY() - 1] = grid.getTile(src.getX() -1, src.getY() - 1).isAccessible() ? dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() - 1, src.getY() - 1), dest);
                case 7:
                    if (!inBounds(src.getX() + 1, src.getY() - 1)) return false;
                    gridDist[src.getX() + 1][src.getY() - 1] = grid.getTile(src.getX() + 1, src.getY() - 1).isAccessible() ? dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() + 1, src.getY() - 1), dest);
                default:
                    break;
            }
        }
        return false;
    }

    /**
     * Helper function to determine "cardinality" of where the dest is in reference to the src
     * Divided into eight regions centered along W, NW, N, ... SW and with +/- 22.5 deg
     * @param src
     * @param dest
     * @return
     */
    private int getDir(Tile src, Tile dest) {
        double dx = dest.getX() - src.getX();
        double dy = dest.getY() - src.getY();
        double hyp = Math.sqrt( Math.pow(dx,2) + Math.pow(dy, 2) );
        double cos = dx/hyp;
        double sin = dy/hyp;
        double theta = Math.acos(cos) - (Math.PI / 8); // Finds the angle, offsets it by Pi/8 to align to increments of Pi/4
        if ((sin < 0) || (sin > Math.PI)) { // Checks for the extended domain of acos
            theta += Math.PI;
        }
        return (int) (Math.ceil(theta * 4 / Math.PI) % 8); // Return grid direction to search
    }

    /**
     * Searches in a square-like BFS pattern if GBFS fails
     * @param src Src tile
     * @param dest Dest tile
     */
    private void square(Tile src, Tile dest) {
        int dx = dest.getX()-src.getX();
        int dy = dest.getY()-src.getY();
        int i = 0;
        int j = 0;
        int oldi = i;
        int oldj = j;

        int left = Math.min(dest.getX(),src.getX());
        int right = Math.max(grid.getViewableWidth() - dest.getX(), grid.getViewableWidth() - src.getX());
        int up = Math.max(grid.getViewableHeight() - dest.getY(), grid.getViewableHeight() - src.getY());
        int down = Math.min(dest.getY(),src.getY());

        while (gridDist[dest.getX()][dest.getY()] == -2 && inBounds(Math.abs(dx), Math.abs(dy))) {
            //bounds checking


            //important stuff
            for (i = oldi; i <= Math.abs(dx); i++) {
                for (j = oldj; j <= Math.abs(dy); j++) {
                    // if cases are pathing for different directions
                    if(gridDist[src.getX() + i * (int) Math.signum(dx) ][src.getY() + j * (int) Math.signum(dy) ] == -2) {
                        int q = findMinNeighbor(src.getX() + i * (int) Math.signum(dx), src.getY() + j * (int) Math.signum(dy));
                        boolean accessible = grid.getTile(src.getX() + i * (int) Math.signum(dx), src.getY() + j * (int) Math.signum(dy)).isAccessible();
                        gridDist[src.getX() + i * (int) Math.signum(dx)][src.getY() + j * (int) Math.signum(dy)] = ((q >= 0) && accessible ? q + 1 : -1);
                    }
                }
            }

            //incrementing right
            if(dx >= 0 && right > 0){
                ++dx;
                --right;
            } else if (dx < 0 && right > 0){
                --dx;
                --right;
            }

            //incrementing up
            if(dy >= 0 && up > 0){
                ++dy;
                --up;
            } else if (dy < 0 && up > 0){
                --dy;
                --up;
            }

            //incrementing left
            if(left > 0){
                --i;
                --left;
            }

            //incrementing down
            if (down > 0) {
                --j;
                --down;
            }
        }
    }
}
