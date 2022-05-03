/**
 * A basic enum to track building types
 */

package Grid;

import java.awt.*;
import java.util.ArrayList;

public enum BuildingType {
    SCHOOL(10, 3, 60, Color.decode("#9e746f")),
    STORE(5, 5, 50, Color.decode("#9e7f6f")),
    LIBRARY(10, 5, 100, Color.decode("#9e936f")),
    DRUGSTORE(2, 2, 30, Color.decode("#889e6f")),
    CONCERTHALL(13, 13, 1000, Color.decode("#6f9e97")),
    CASINO(7, 7, 300, Color.decode("#6f769e")),
    RUBBERDUCKFACTORY(10, 8, 200, Color.decode("#826f9e")),
    HOSPITAL(10, 10, 300, Color.decode("#9d6f9e"));

    private int w;
    private int h;
    private int capacity;
    private Color c;


    /**
     * Make a new building type with the given width and height
     *
     * @param w The width
     * @param h The height
     * @param cap The capacity
     */
    BuildingType(int w, int h, int cap, Color c) {
        this.w = w;
        this.h = h;
        this.capacity = cap;
        this.c = c;
    }

    /**
     * Get the width of the building type
     *
     * @return The width of the building type
     */
    public int getW() {
        return w;
    }

    /**
     * Get the height of the building type
     *
     * @return The height of the building type
     */
    public int getH() {
        return h;
    }

    /**
     * Gets the capacity of the building
     *
     * @return The capacity of the building
     */
    public int getCapacity() {
        return capacity;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    /**
     * Allocates tiles for the building. Does nothing with the tiles, just tells which ones they are.
     *
     * @param tiles The matrix of tiles
     * @param x The x coordinate of the UL corner
     * @param y The y coordinate of the UL corner
     * @return An arraylist of tiles to allocate, or null if allocation failed
     */
    public ArrayList<Tile> allocateTiles(Tile[][] tiles, int x, int y) {
        ArrayList<Tile> needed = new ArrayList<>();

        //Check for invalid x & y
        if(x < 0 || x > tiles.length || y < 0 || y > tiles[0].length)
            return null;

        //Check available space
        if(w + x > tiles.length || h + y > tiles[0].length)
            return null;

        //Add tiles to the list
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < h; j++) {
                needed.add(tiles[i + x][j + y]);
                this.setC(this.c);
                System.out.println(getC().toString());
            }
        }

        //Return the list
        return needed;
    }
}
