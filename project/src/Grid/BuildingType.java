/**
 * A basic enum to track building types
 */

package Grid;

import java.util.ArrayList;

public enum BuildingType {
    SCHOOL(10, 3),
    STORE(5, 5),
    HOSPITAL(10, 5);

    private int w;
    private int h;


    /**
     * Make a new building type with the given width and height
     *
     * @param w The width
     * @param h The height
     */
    BuildingType(int w, int h) {
        this.w = w;
        this.h = h;
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
     * Allocates tiles for the building. Does nothing with the tiles, just tells which ones they are.
     *
     * @param tiles The matrix of tiles
     * @param x The x coordinate of the UL corner
     * @param y The y coordinate of the UL corner
     * @return An arraylist of tiles to allocate, or null if allocation failed
     */
    public ArrayList<Tile> allocateTiles(Tile[][] tiles, int x, int y) {
        ArrayList<Tile> needed = new ArrayList<>();

        //Switch based on building type to generate according to the pattern
        switch (this) {
            case SCHOOL: //For the school
                int w = 10;
                int h = 3;

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
                    }
                }
                break;

            case STORE: //For the store
                w = 5;
                h = 5;

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
                    }
                }
                break;

            case HOSPITAL: //For the hospital
                w = 10;
                h = 5;

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
                    }
                }
                break;
        }

        //Return the list
        return needed;
    }
}
