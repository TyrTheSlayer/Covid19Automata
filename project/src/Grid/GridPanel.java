/**
 * @author Samuel Nix
 * @author Summer Bronson
 *
 * Sets up a grid to be displayed by Mainframe
 */

package Grid;

import custom_classes.Person;
import custom_classes.VirusType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GridPanel extends JPanel implements Runnable {
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

    //The list of people and their intents
    private ArrayList<Person> people;
    private ArrayList<Intent> intents;

    private BehaviorAgent agent;

    private Thread t;
    private long pause_len;
    private boolean running;
    private boolean initialized = false;
    private int i = 0;

    /**
     * Creates a new GridPanel
     * @param newtileSize the height and width of any particular cell
     * @param viewableHeight viewable height of the grid (how many cells are generated + 2)
     * @param viewableWidth viewable width of the grid (how many cells are generated + 2)
     * @param topLeftX coordinate of top left X usually 0
     * @param topLeftY coordinate of top left Y usually 0
     */
    public GridPanel(int newtileSize, int viewableHeight, int viewableWidth, int topLeftX, int topLeftY) {
        // setup panel
        super(null);
        setLayout(null);


        this.baseTileSize = newtileSize;
        this.tileSize = newtileSize;
        this.viewableHeight = viewableHeight;
        this.viewableWidth = viewableWidth;
        this.gridPixelWidth = newtileSize * viewableWidth + 13; //don't ask why it is 13
        this.gridPixelHeight = newtileSize * viewableHeight + 35; //35 pixels is offset for bar at top of window
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        gridViewable = new Tile[viewableHeight][viewableWidth];
        this.people = new ArrayList<>();
        this.intents = new ArrayList<>();
        t = new Thread(this);
        this.agent = new BehaviorAgent(this);

        //create the grid
        for (int i=0; i<viewableHeight; i++) {
            for (int j=0; j<viewableWidth; j++) {
                Tile newTile = createTile(topLeftX+i, topLeftY+j); // This might need to be reversed - Brenton
                gridViewable[i][j] = newTile;
            }
        }

        //Put a person in every 5th tile
        for(int i = 0; i < viewableHeight; i++) {
            for(int j = 0; j < viewableWidth; j += 5) {
                Person p = new Person(j, i, new ArrayList<>());
                this.people.add(p);
                this.intents.add(new Intent(Intent.Behavior.ROAM, 99)); // This line forces all people to roam, to test the roam method
                this.gridViewable[i][j].setOccupant(p);
            }
        }

        //Infect every 10th person, starting with the first
        VirusType basic = new VirusType();
        for(int i = 0; i < this.people.size(); i += 10) {
            this.people.get(i).setVirus(basic.genVirus(this.people.get(i)));
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

    //Getters
    public int getViewableHeight() { return this.viewableHeight; }
    public int getViewableWidth() { return this.viewableWidth; }

    /**
     * Returns the Tile at the specified index
     * @param x The x coord
     * @param y The y coord
     * @return The Tile at (x, y)
     */
    public Tile getTile(int x, int y) {
        if (((x >= 0) && (x < viewableWidth)) && ((x >= 0) && (y < viewableHeight))) return gridViewable[x][y];
        return null;
    }

    /**
     * A method to tick. Currently handles actions for each Person object in the sim via the BehaviorAgent and ascribes intents when they expire
     */
    public void step() {
        for (int i = 0 ;i < people.size(); i++) {
            if(intents.get(i).tickIntent() < 0) {
                intents.set(i, agent.genIntent());
            }
            agent.action(people.get(i), intents.get(i));
        }
    }

    /**
     * A method to update the pause duration between frames. Used from MainFrame to change frame update rate
     * @param pause_len The new len, shouldn't be used directly, instead like (1000/rate)
     */
    public void setPause_len(long pause_len) { this.pause_len = pause_len; }

    /**
     * Helper method to start/stop thread execution
     */
    public void start() {
        if (!initialized) {
            t.start();
            initialized = true;
        }
        running = true;
    }

    /**
     * Compliment of start()
     */
    public void pause() { running = false;}

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
        for(int i = 0; i < viewableHeight; i++) {
            for(int j = 0; j < viewableWidth; j++) {
                this.gridViewable[i][j].draw(this.tileSize, g2);
            }
        }

        // generates the grey outline for the grid
        for (int i=0; i<gridPixelHeight; i++) {
            for (int j = 0; j < gridPixelWidth; j++) {
                    g2.setColor(Color.BLACK);
                    g2.drawRect(-(tileSize-leftSideOffset)+j*tileSize, -(tileSize-topSideOffset)+i*tileSize, tileSize, tileSize);
            }
        }
    }

    /**
     * Our rynnable thread. Step() should be used to handle all per-tick operations, while this enables pre-tick and post-tick operations to be done between frames.
     */
    @Override
    public void run() {
        while(true) {
            // Pre-tick code
            long old = System.currentTimeMillis(); // Get old sys time


            // Tick
            if (running) {
                step(); // Process tick
                repaint(); // Repaint
            }


            // Post-tick code
            long diff = System.currentTimeMillis() - old; // Find exTime
            if (diff < pause_len) { // Wait if necessary to maintain FPS
                try {
                    t.sleep(pause_len - diff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
