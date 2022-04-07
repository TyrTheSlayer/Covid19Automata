package Path;

import Grid.GridPanel;
import Grid.Tile;

import java.util.ArrayList;

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

    private Path greedyBFS(Tile src, Tile dest) {
        int selection = getDir(src, dest);
        if (selection % 2 == 0) { // Selection aligns to grid
            if (selection %4 == 0) { // Selection has same x coord

            } else { // Selection has same y cord

            }
        } else { // Diagonal

        }
    }

    private int getDir(Tile src, Tile dest) {
        double dx = dest.getX()-src.getX();
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
}
