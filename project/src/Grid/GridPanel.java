/**
 * @author Samuel Nix, Summer Bronson, Aedan Wells, Janathan Carsten
 *
 * Sets up a grid to be displayed by Mainframe
 */

package Grid;

import Simulator.BehaviorAgent;
import Simulator.Factor;
import DataObjects.Person;
import Simulator.Intent;
import Simulator.SimSettings;
import DataObjects.VirusType;
import Simulator.DataOut;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GridPanel extends JPanel implements Runnable {
    // width and height of viewable grid in tiles, excluding the outer border
    public int viewableHeight;
    public int viewableWidth;
    private int ticks = 0;
    private final int TICKS_PER_RECORD = 360;



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
    private ArrayList<Factor> factor;

    private DataOut data = new DataOut(100);

    private BehaviorAgent agent;

    private Thread t;
    private long pause_len;
    private boolean running;
    private boolean initialized = false;
    private int i = 0;

    private SimSettings settings;
    /**
     * Creates a new GridPanel
     * @param newtileSize the height and width of any particular cell
     * @param viewableHeight viewable height of the grid (how many cells are generated + 2)
     * @param viewableWidth viewable width of the grid (how many cells are generated + 2)
     * @param topLeftX coordinate of top left X usually 0
     * @param topLeftY coordinate of top left Y usually 0
     */
    public GridPanel(int newtileSize, int viewableHeight, int viewableWidth, int topLeftX, int topLeftY, SimSettings settings) {
        // setup panel
        super(null);
        setLayout(null);


        this.baseTileSize = newtileSize;
        this.tileSize = newtileSize;
        this.viewableHeight = viewableHeight;
        this.viewableWidth = viewableWidth;
        this.gridPixelWidth = newtileSize * viewableWidth + 13; //don't ask why it's 13
        this.gridPixelHeight = newtileSize * viewableHeight + 70; //70 pixels is offset for bar at top and bottom of window
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        gridViewable = new Tile[viewableWidth][viewableHeight];
        this.people = new ArrayList<>();
        this.intents = new ArrayList<>();
        this.factor = new ArrayList<>();
        t = new Thread(this);
        this.agent = new BehaviorAgent(this);
        this.settings = settings;

        //create the grid
        for (int i=0; i<viewableWidth; i++) {
            for (int j=0; j<viewableHeight; j++) {
                Tile newTile = createTile(topLeftX+i, topLeftY+j); // This might need to be reversed - Brenton
                gridViewable[i][j] = newTile;
            }
        }

        //Put a person in every 5th tile 6X30
/*
        for(int i = 0; i < viewableWidth; i++) {
            for(int j = 0; j < viewableHeight; j += 5) {
                Person p = new Person(j, i, new ArrayList<>());
                this.people.add(p);
                this.intents.add(agent.genIntent()); // This line forces all people to roam, to test the roam method
                this.gridViewable[i][j].setOccupant(p);
            }
        }

        //Infect every 10th person, starting with the first
        VirusType basic = new VirusType();
        for(int i = 0; i < this.people.size(); i += 10000000) {
            this.people.get(i).setVirus(basic.genVirus(this.people.get(i)));
        }
        repaint();
        */

        initPeople(settings.getInitialInfected(), settings.getPopulation());
    }


    /**
     * Populate People arraylist
     * place people on grid randomly and set the number of infected people
     * @param infected infected initially
     * @param population total population
     */

    public void initPeople(double infected, int population){
        Random rn = new Random();
        int i = 0;
        while(i < population) {
            int randx = rn.nextInt(this.viewableWidth);
            int randy = rn.nextInt(this.viewableHeight);
            if (this.gridViewable[randx][randy].getOccupant() == null) {
                Factor f = new Factor();
                Person p = new Person(randx, randy, f);
                this.factor.add(f);
                this.people.add(p);
                this.intents.add(agent.genIntent());
                this.gridViewable[randx][randy].setOccupant(p);
                i++;
            }
        }

        VirusType basic = new VirusType();
        int numInfected = (int) Math.floor(infected * population);

        for(int j = 0; j < this.people.size(); j += population / numInfected){
            this.people.get(j).setVirus(basic.genVirus(this.people.get(j)));
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
     * Gets the neighbors of the tile at coordinates (x, y)
     *
     * @param x The x location of the tile
     * @param y The y location of the tile
     */
    public Tile[] getNeighborsForTile(int x, int y) {
        //Auto-return null if the x and y are bad
        if(x < 0 || x >= this.viewableWidth || y < 0 || y >= this.viewableHeight)
            return null;

        //Make the array, fill it with neighbors
        ArrayList<Tile> neighbors = new ArrayList<>();
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                //Add the tile if x and y are valid and this isn't the tile we're on
                if(j >= 0 && j < this.viewableHeight && i >= 0 && i < this.viewableWidth &&
                        (j != y || i != x))
                    neighbors.add(this.gridViewable[i][j]);
            }
        }

        return neighbors.toArray(new Tile[1]);
    }

    //Methods
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

        //Update the infection
        for(Person i : this.people) {
            int oldX = i.getX();
            int oldY = i.getY();

            //If they're already dead, continue to the next person
            if(oldX == -666 && oldY == -666)
                continue;

            //Update everyone's infection
            i.updateVirus();

            //If they died, clear their tile and go to the next loop
            if(i.getX() == -666 && i.getY() == -666) {
                this.gridViewable[oldX][oldY].clearOccupant();
                continue;
            }

            //Try and infect people
            if(i.cough()) {
                //Find all of the neighbors, try to infect them
                Tile[] targets = getNeighborsForTile(i.getX(), i.getY());
                for(Tile j : targets) {
                    if(j.getOccupant() != null)
                        j.getOccupant().infect(i);
                }
            }
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
        for(int i = 0; i < viewableWidth; i++) {
            for(int j = 0; j < viewableHeight; j++) {
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
                ticks++;
            }


            // Post-tick code

            //This should write out to the record
            if (ticks % TICKS_PER_RECORD == 0) {
                int s = 0;
                int i = 0;
                int r = 0;
                int d = 0;
                int v = 0;
                for (Person p : people) {
                    switch(p.getStatus()) {
                        case DEAD: d++; break;
                        case INFECTED: i++; break;
                        case ALIVE: s++; break;
                        case RECOVERED: r++; break;
                    }
                    if(p.getFactors().isVaccinated()) v++;
                }
                data.addRecord(s, i, r, d, v);
            }


            // Final code, to maintain framerate
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
