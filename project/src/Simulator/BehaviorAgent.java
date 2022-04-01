/**
 * @author Brenton Candelaria
 *
 * @description A class to handle intelligent behaviors of the automata
 */

package Simulator;

import java.util.Random;
import DataObjects.Person;
import Grid.GridPanel;
import Grid.Tile;

public class BehaviorAgent {
    private GridPanel grid;
    private int width;
    private int height;

    /**
     * Initializes a behavior agent
     * @param grid The grid to act on
     */
    public BehaviorAgent(GridPanel grid) {
        this.grid = grid;
        this.width = grid.getViewableWidth();
        this.height = grid.getViewableHeight();
    }

    /**
     * Generates a random intent for a cell to act on
     * @return A fully initialized Intent object
     */
    public Intent genIntent() {
        Random rand = new Random();
        Intent i = new Intent(Intent.Behavior.getRandomBehavior(), rand.nextInt(20)); // Generates a random intent with max duration of 20 ticks
        if (i.getIntent() == Intent.Behavior.PATHTO) { // Initializes a random destination cell if the person wants to path somewhere
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            i.setPath(x, y);
        }
        // Return the intent
        return i;
    }

    /**
     * Acts to perform the intended action on behalf of a cell. Currently calls other methods in this class, but allows for pre-processing before doing so
     * @param p The person to act on
     * @param i The intended action
     * @return
     */
    public int action(Person p, Intent i) {
        switch(i.getIntent()) {
            // Most of these are as of yet unimplemented, but should not be too difficult
            case SLEEP:
                return i.tickIntent();

            case DEAD:
                return -666;

            case ROAM:
                return roam(p);

            case PATHTO:
                break;

            case QUARANTINE:
                if (p.getX() > 0) {
                    grid.getTile(p.getX(), p.getY()).setAccessible(true);
                    grid.getTile(p.getX(), p.getY()).clearOccupant();
                    p.setPosition(-1, -1);
                }
                return i.tickIntent();

            default:
                break;
        }
        return -1;
    }

    /**
     * Causes a cell to roam randomly, moving 1 cell in any direction
     * @param p The person to move
     * @return
     */
    public int roam(Person p) {
        Random rand = new Random();
        int x = p.getX();
        int y = p.getY();

        //Auto return if someone's dead
        if(x == -666 && y == -666)
            return 1;

        // Choose a random adjacent cell
        y += rand.nextInt(3) - 1;
        x += rand.nextInt(3) - 1;

        // Bound detection
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        if (x > width-1)
            x = width-1;
        if (y > height-1)
            y = height-1;

        // Skip if we chose the same tile
        if ((x == p.getX()) && (y == p.getY()))
            return 1;

        // Check destination tile for occupancy
        Tile tile = grid.getTile(x,y);
        if (tile.isAccessible()) {
//            System.out.println("Tile ("+x+", "+y+") is taking from ("+p.getX()+", "+p.getY()+")");
            tile.takePerson(grid.getTile(p.getX(), p.getY()));
            return 1;
        }

        return 0;
    }
}

