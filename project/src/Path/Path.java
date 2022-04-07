package Path;

import Grid.GridPanel;
import Grid.Tile;

import java.util.ArrayList;

public class Path {
    private ArrayList<Tile> Path;
    private GridPanel grid;

    public Path findPath(Tile src, Tile dest) {
        this.Path = new ArrayList<>();
        return this;
    }

    private Tile findCenter(Tile src, Tile dest) {
        int dx = dest.getX()-src.getX();
        int dy = dest.getY()-src.getY();
        return grid.getTile(src.getX() + dx, src.getY() + dy);
    }

    private Path square(Tile src, Tile dest) {
        int dx = dest.getX()-src.getX();
        int dy = dest.getY()-src.getY();



        return null;
    }
}
