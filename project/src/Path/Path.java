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
    private ArrayList<Tile> Path;
    private GridPanel grid;
    private int gridDist[][];

    public Path(GridPanel grid, Tile src) {
        this.Path = new ArrayList<>();
        this.grid = grid;
        this.gridDist = new int[grid.viewableWidth][grid.viewableHeight];
        for (int i = 0; i < grid.viewableWidth; i++) {
            for(int j = 0; j < grid.viewableHeight; j++) {
                gridDist[i][j] = -2;
                if(!grid.getTile(i, j).isAccessible()) gridDist[i][j] = -1;
            }
        }
        gridDist[src.getX()][src.getY()] = 0;
    }

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

    public int getLength() {
        return this.Path == null ? -1 : this.Path.size();
    }

    private ArrayList<Tile> getPath() {
        return this.Path;
    }

    public Tile nextStep() {
        if (Path.size() < 1) {
            this.Path = null;
            return null;
        }
        if (Path.size() < 3) {
            Tile t = Path.get(0);
            Path.remove(0);
            this.Path = null;
            return t;
        }
        Tile t = Path.get(1);
        if (t.isAccessible()) {
            Path.remove(0);
            return t;
        }
        int i = 2;
        for(;i < Path.size() && !Path.get(i).isAccessible(); i++);
        if (i == Path.size()) return Path.get(0);
        Path p = new Path(this.grid, Path.get(0));
        p.findPath(Path.get(0), Path.get(i));
        if (p.getPath() == null) return Path.get(0);
        p.getPath().remove(p.getPath().size()-1);
        p.getPath().remove(0);
        for(int j = 1; j < i; j++) Path.remove(j);
        for (i = 0; i < p.getPath().size(); i++) Path.add(i + 1, p.getPath().get(i));
        t = Path.get(1);
        Path.remove(0);
        return t;
    }

    public Tile courtesyStep() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i == 0) && (j == 0)) continue;
                int x = Path.get(0).getX() + i;
                int y = Path.get(0).getY() + j;
                if (!inBounds(x,y)) continue;
                Tile t = grid.getTile(x, y);
                if (t.isAccessible()) {
                    Path.add(0, t);
                    return t;
                }
            }
        }
        return Path.get(0);
    }

    public boolean findPath(Tile src, Tile dest) {
        if (greedyBFS(src, dest)) {
            buildPath(dest);
            return true;
        }
        square(src, dest);
        if (gridDist[dest.getX()][dest.getY()] > -1) buildPath(dest);
        if (Path.size() > 0) return true;
        Path = null;
        return false;
    }

    private void buildPath(Tile dest) {
        Path.add(0, dest);
        Tile x = dest;
        while (gridDist[x.getX()][x.getY()] > 0) {
            for(int i = -1; i < 2; i++) {
                for(int j = -1; j < 2; j++) {
                    if (!inBounds(x.getX() + i, x.getY() + j)) continue;
                    if (gridDist[x.getX() + i][x.getY() + j] == (gridDist[x.getX()][x.getY()] - 1)) {
                        Path.add(0, x);
                        x = grid.getTile(x.getX() + i, x.getY() + j);
                        i = 2;
                        j = 2;
                    } else if ((x.getX() == dest.getX()) && (x.getY() == dest.getY())) break;
                }
            }
            if ((x.getX() == dest.getX()) && (x.getY() == dest.getY())) break;
        }
        Path.add(0, x);
    }


    private boolean inBounds(int x, int y) {
        if (   ( (x < 0) || (x >= grid.viewableWidth) )   ||   (  (y < 0) || (y >= grid.viewableHeight)  )  ) return false;
        return true;
    }

    private Boolean greedyBFS(Tile src, Tile dest) {
        if (src == null || dest == null) return false;
        if ((src.getX() == dest.getX()) && (src.getY() == dest.getY())) {
            return true;
        }
        int selection = getDir(src, dest);
        int dist = gridDist[src.getX()][src.getY()];
        if (selection % 2 == 0) { // Selection aligns to grid
            if (selection %4 == 0) { // Selection has same x coord
                for(int i = -1; i < 2; i++) {
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
