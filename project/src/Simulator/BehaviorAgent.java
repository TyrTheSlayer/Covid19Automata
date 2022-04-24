/**
 * @author Brenton Candelaria
 *
 * @description A class to handle intelligent behaviors of the automata
 */

package Simulator;

import java.util.Random;
import DataObjects.Person;
import DataObjects.Status;
import Grid.Building;
import Grid.GridPanel;
import Grid.Tile;
import Path.Path;

public class BehaviorAgent {
    private GridPanel grid;
    private int width;
    private int height;
    public int ticksPerDay;
    private int ticks;

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
     * A method to convey to the BA what time it is
     * This method should only be used once to set the tPD
     * @param ticks The current ticks
     * @param ticksPerDay The ticks per day
     */
    public void updateTime(int ticks, int ticksPerDay) {
        this.ticks = ticks;
        this.ticksPerDay = ticksPerDay;
    }

    /**
     * A method to update the time of day in the BA
     * @param ticks The ticks of the day
     */
    public void updateTime(int ticks) {
        this.ticks = ticks % ticksPerDay;
    }

    public void hold(Person p) {
        Intent i = grid.intents.get(grid.people.indexOf(p));
        i.setIntent(Intent.Behavior.BUILDING, i.getDuration() + 1);
    }
    
    /**
     * Generates a random intent for a cell to act on
     * @return A fully initialized Intent object
     */
    public Intent genIntent(Person p) {
        if (p.getX() == -666) // Check if dead
            return new Intent(Intent.Behavior.DEAD, 0);
        if (p.getX() == -2 && grid.intents.get(grid.people.indexOf(p)).getIntent() != Intent.Behavior.BUILDING) // In a building
            return new Intent(Intent.Behavior.BUILDING, 20);
        if (p.getX() == -2 && grid.intents.get(grid.people.indexOf(p)).getDuration() <= 0) {
            for (Building b : grid.getBuildings()) {
                Object[] list = b.getOccupants().toArray();
                for (int j = 0; j < list.length; j++) {
                    Person p2 = (Person) list[j];
                    if (p == p2) {
                        if (!b.exit(p))
                            return grid.intents.get(grid.people.indexOf(p));
                    }
                }
            }
        }
        Building b = p.getTarget(ticks, ticksPerDay); // Get a target
        if (b != null) { // If this person has a target, path to it
            Tile entrance = b.getRandEntrance();
            Path path = new Path(grid, grid.getTile(p.getX(), p.getY()));
            if (!path.findPath(grid.getTile(p.getX(),p.getY()), entrance)) {
                System.out.println("Cannot path to dest building");
                return new Intent(Intent.Behavior.SLEEP, 1);
            }
            Intent i = new Intent(Intent.Behavior.PATHTO, path.getLength());
            i.setPath(entrance, path);
            return i;
        }
        // Gen a random intent
        Random rand = new Random();
        Intent i = new Intent(Intent.Behavior.getRandomBehavior(), rand.nextInt(20)); // Generates a random intent with max duration of 20 ticks
        if (i.getIntent() == Intent.Behavior.PATHTO) { // Initializes a random destination cell if the person wants to path somewhere
            if (grid.getBuildings().size() > 0 && rand.nextInt(5) == 0) { // Makes 20% of paths go to a building
                Tile t = grid.getBuildings().get(rand.nextInt(grid.getBuildings().size())).getRandEntrance();
                Path path = new Path(grid, grid.getTile(p.getX(), p.getY()));
                if (!path.findPath(grid.getTile(p.getX(), p.getY()), t))
                    return new Intent(Intent.Behavior.SLEEP, rand.nextInt(1));
                i.setIntent(Intent.Behavior.PATHTO, path.getLength());
                i.setPath(t, path);
                return i;
            }
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            while (!grid.getTile(x, y).isAccessible()) {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            }
            Path path = new Path(grid, grid.getTile(p.getX(), p.getY()));
            if (!path.findPath(grid.getTile(p.getX(), p.getY()), grid.getTile(x, y)))
                return new Intent(Intent.Behavior.SLEEP, rand.nextInt(1));
            i.setIntent(Intent.Behavior.PATHTO, path.getLength());
            i.setPath(grid.getTile(x, y), path);
        }
        // Return the intent
        return i;
    }

    /**
     * Gets the time of day in the sim
     * @return The current time
     */
    public int getTime() {
        return this.ticks;
    }

    /**
     * Acts to perform the intended action on behalf of a cell. Currently calls other methods in this class, but allows for pre-processing before doing so
     * @param p The person to act on
     * @param i The intended action
     * @return Typically returns the duration of the intent, or a negative number corresponding to a special status
     */
    public int action(Person p, Intent i) {
        switch(i.getIntent()) {
            // Most of these are as of yet unimplemented, but should not be too difficult
            case SLEEP:
                return i.tickIntent();

            case DEAD:
                return -666;

            case BUILDING:
                int d = i.tickIntent();
                if (d <= 1) // Complicated way to force people to leave a building
                    for (Building b : grid.getBuildings()) {
                        Object[] list = b.getOccupants().toArray();
                        for (int j = 0; j < list.length; j++) {
                            Person p2 = (Person) list[j];
                            if (p == p2) {
                                b.exit(p);
                            }
                        }
                    }
                return d;

            case ROAM:
                return roam(p);

            case PATHTO:
                if (p.getX() == -2) {
                    i.setIntent(Intent.Behavior.BUILDING, 2);
                    return 0;
                }

                Path path;
                if((path = i.getPath()) == null || (path.getLength() == -1)) {
                    i.setIntent(Intent.Behavior.SLEEP, 1);
                    return i.tickIntent();
                }
                Tile t = path.nextStep();
                if (t == null) {
                    return genIntent(p).tickIntent();
                }
                if (grid.getTile(p.getX(), p.getY()) == null) return -1;
                if ((grid.getTile(p.getX(), p.getY()) == t) && (path.getLength() > 2)){
                    t = path.courtesyStep();
                }
                t.takePerson(grid.getTile(p.getX(), p.getY()));
                return i.tickIntent();

                //return 1;
            case QUARANTINE:
                if (p.getX() >= 0) {
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
     * @return 1 on fail, 0 on success
     */
    public int roam(Person p) {
        Random rand = new Random();
        int x = p.getX();
        int y = p.getY();

        //Auto return if someone's dead
        if(x < 0 && y < 0)
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
            return 0;
        }

        return 1;
    }
}

