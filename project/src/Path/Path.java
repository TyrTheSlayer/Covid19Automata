/**
 * @author Samuel Nix, Summer Bronson
 *
 * Pathing algorithms
 */

package Path;

import Grid.GridPanel;
import Grid.Tile;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Path {
    private ArrayList<Tile> Path;
    private GridPanel grid;
    private int gridDist[][];

    public Path(GridPanel grid, Tile src, Tile dest) {
        this.Path = new ArrayList<>();
        this.grid = grid;
        this.gridDist = new int[grid.viewableWidth][grid.viewableHeight];
        for (int i = 0; i < grid.viewableWidth; i++) {
            for(int j = 0; j < grid.viewableHeight; j++) {
                gridDist[i][j] = -2;
            }
        }

        gridDist[src.getX()][src.getY()] = 0;
    }

    private int findMinNeighbor(int x, int y) {
        int min = -1;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((gridDist[x + i][y + j] < min) && (gridDist[x + i][y + j] > -1)) {
                    min = gridDist[x + i][y + j];
                }
            }
        }
        return min;
    }

    private Boolean greedyBFS(Tile src, Tile dest) {
        if ((src.getX() == dest.getX()) && (src.getY() == dest.getY())) {
            gridDist[dest.getX()][dest.getY()] = findMinNeighbor(dest.getX(), dest.getY()) + 1;
            return true;
        }
        int selection = getDir(src, dest);
        int dist = gridDist[src.getX()][src.getY()];
        if (selection % 2 == 0) { // Selection aligns to grid
            if (selection %4 == 0) { // Selection has same x coord
                for(int i = -1; i < 2; i++) {
                    gridDist[src.getX()][src.getY() + i] = grid.getTile(src.getX(), src.getY() + i).isAccessible() ? dist + 1 : -1;
                }
                if (selection == 0) {
                    return greedyBFS(grid.getTile(src.getX() + 1, src.getY()), dest);
                }
                return greedyBFS(grid.getTile(src.getX() - 1, src.getY()), dest);
            } else { // Selection has same y cord
                for(int i = -1; i < 2; i++) {
                    gridDist[src.getX() + i][src.getY()] = grid.getTile(src.getX() + i, src.getY()).isAccessible() ? dist + 1 : -1;
                }
                if (selection == 2) {
                    return greedyBFS(grid.getTile(src.getX(), src.getY() + 1), dest);
                }
                return greedyBFS(grid.getTile(src.getX(), src.getY() - 1), dest);
            }
        } else { // Diagonal
            if (selection > 4) { // need to check 6
                gridDist[src.getX()][src.getY()-1] = grid.getTile(src.getX(), src.getY() - 1).isAccessible() ? dist + 1 : -1;
            } else { // Need to check 2
                gridDist[src.getX()][src.getY()+1] = grid.getTile(src.getX(), src.getY() + 1).isAccessible() ? dist + 1 : -1;
            }

            if ((selection - 1) % 6 == 0) { // Need to check 0
                gridDist[src.getX() + 1][src.getY()] = grid.getTile(src.getX() + 1, src.getY()).isAccessible() ? dist + 1 : -1;
            } else {
                gridDist[src.getX() - 1][src.getY()] = grid.getTile(src.getX() - 1, src.getY()).isAccessible() ? dist + 1 : -1;
            }
            switch (selection) {
                case 1:
                    gridDist[src.getX() + 1][src.getY() + 1] = grid.getTile(src.getX() + 1, src.getY() + 1).isAccessible() ?  dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() + 1, src.getY() + 1), dest);
                case 3:
                    gridDist[src.getX() - 1][src.getY() + 1] = grid.getTile(src.getX() - 1, src.getY() + 1).isAccessible() ? dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() - 1, src.getY() + 1), dest);
                case 5:
                    gridDist[src.getX() - 1][src.getY() - 1] = grid.getTile(src.getX() -1, src.getY() - 1).isAccessible() ? dist + 1 : -1;
                    return greedyBFS(grid.getTile(src.getX() - 1, src.getY() - 1), dest);
                case 7:
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

    private Path square(Tile src, Tile dest) {
        int dx = dest.getX()-src.getX();
        int dy = dest.getY()-src.getY();
        Path p = new Path(grid, src, dest);

        for (int i = 0; i <= abs(dx); i++) {
            for (int j = 0; j <= abs(dy); j++) {
                // if cases are pathing for different directions
                if (dx >= 0 && dy >= 0) {
                    int q = findMinNeighbor(src.getX() + i, src.getY() + j);
                    p.gridDist[src.getX() + i][src.getY() + j] = q > 0 ? q : -1;
                } else if (dx >= 0 && dy < 0) {
                    int q = findMinNeighbor(src.getX() + i, src.getY() - j);
                    p.gridDist[src.getX() + i][src.getY() - j] = q > 0 ? q : -1;
                } else if (dx < 0 && dy >= 0) {
                    int q = findMinNeighbor(src.getX() - i, src.getY() + j);
                    p.gridDist[src.getX() - i][src.getY() + j] = q > 0 ? q : -1;
                } else {
                    int q = findMinNeighbor(src.getX() - i, src.getY() - j);
                    p.gridDist[src.getX() - i][src.getY() - j] = q > 0 ? q : -1;
                }
            }
        }

        return p;
    }
}
