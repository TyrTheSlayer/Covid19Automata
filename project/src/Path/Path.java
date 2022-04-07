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
        gridDist[src.getX()][src.getY()] = 0;
    }


    private Path greedyBFS(Tile src, Tile dest) {
        int selectino = getDir(src, dest);
    }

    private int getDir(Tile src, Tile dest) {
        double dx = dest.getX()-src.getX();
        double dy = dest.getY() - src.getY();
        double hyp = Math.sqrt( Math.pow(dx,2) + Math.pow(dy, 2) );
        double cos = dx/hyp;
        double sin = dy/hyp;
        double theta = Math.acos(cos);
        if ((sin < 0) || (sin > Math.PI)) {
            theta += Math.PI;
        }
        // if sin > 0, NOT 567
        // if sin < 0, NOT 123
        // if cos > 0, NOT 345
        // if cos < 0, NOT 107


    }
}
