/**
 * @author Samuel Nix
 * @author Summer Bronson
 *
 * Sets up a grid to be displayed by Mainframe
 */

package Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GridPanel extends JPanel {
    // width and height of viewable grid in tiles, excluding the outer border
    public int viewableHeight;
    public int viewableWidth;

    // width and height of window in pixels, including visible borders
    public int gridPixelWidth;
    public int gridPixelHeight;

    // size of one square tile
    private final int baseTileSize;
    private int tileSize;

    // upper left border coordinates of screen
    public int topLeftX;
    public int topLeftY;

    // actual pixel offsets
    private int leftSideOffset=0;
    private int topSideOffset=0;

    // viewable and actual grid
    private Tile[][] gridViewable;
    public Map<Point, Integer> grid = new ConcurrentHashMap<Point, Integer>();

    /**
     * Creates a new GridPanel
     * @param newtileSize the height and width of any particular cell
     * @param viewableHeight viewable height of the grid (how many cells are generated + 2)
     * @param viewableWidth viewable width of the grid (how many cells are generated + 2)
     * @param gridPixelWidth initial size of the width of window (should be newtileSize * viewableWidth)
     * @param gridPixelHeight initial size of the height of window (should be newtileSize * viewableHeight)
     * @param topLeftX coordinate of top left X
     * @param topLeftY coordinate of top left Y
     */
    public GridPanel(int newtileSize, int viewableHeight, int viewableWidth, int gridPixelWidth, int gridPixelHeight, int topLeftX, int topLeftY) {
        // setup panel
        super(null);
        setLayout(null);

        this.baseTileSize = newtileSize;
        this.tileSize = newtileSize;
        this.viewableHeight = viewableHeight;
        this.viewableWidth = viewableWidth;
        this.gridPixelWidth = gridPixelWidth;
        this.gridPixelHeight = gridPixelHeight;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        gridViewable = new Tile[viewableHeight+2][viewableWidth+2];

        for (int i=0; i<viewableHeight+2; i++) {
            for (int j=0; j<viewableWidth+2; j++) {
                Tile newTile = createTile(topLeftX+j, topLeftY+i);
                gridViewable[i][j] = newTile;
            }
        }
        repaint();
    }

    /**
     * Creates a new Tile
     * @param xcoord x coordinate
     * @param ycoord y coordinate
     * @return
     */
    private Tile createTile(int xcoord, int ycoord) {
        Tile newTile = new Tile(xcoord, ycoord);
        return newTile;
    }

    /**
     * Paints a basic grid
     * @param g the canvas
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        removeAll();
        g2.fillRect(0, 0, gridPixelWidth, gridPixelHeight);

        //Draw each tile
        for(int i = 0; i < viewableHeight + 2; i++) {
            for(int j = 0; j < viewableWidth + 2; j++) {
                this.gridViewable[i][j].draw(this.tileSize, g2);
            }
        }

        for (int i=0; i<gridPixelHeight; i++) {
            for (int j = 0; j < gridPixelWidth; j++) {
                //if (j%2 == 0 && i%2 == 0 || i%2 == 1 && j%2 == 1) {
                    /*g2.setColor(Color.RED);
                    g2.fillRect(-(tileSize-leftSideOffset)+j*tileSize, -(tileSize-topSideOffset)+i*tileSize, tileSize, tileSize);*/
                    g2.setColor(Color.BLACK);
                    g2.drawRect(-(tileSize-leftSideOffset)+j*tileSize, -(tileSize-topSideOffset)+i*tileSize, tileSize, tileSize);
                //}
            }
        }
    }
}
