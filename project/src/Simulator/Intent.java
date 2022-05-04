package Simulator;

import java.util.Random;
import Path.Path;
import Grid.Tile;

/**
 * @author Brenton Candelaria
 *
 * A class to handle status/actions for a cell. Stores data to be used by the BehaviorAgent
 */
public class Intent {
    // Enumerate potential actions
    public enum Behavior {
        SLEEP, ROAM, PATHTO, QUARANTINE, DEAD, BUILDING;

        // Method to get a random behavior
        public static Behavior getRandomBehavior() {
            Random rand = new Random();
            return values()[rand.nextInt(4)]; // Change back to -1 after fixing things
        }
    }

    private Behavior intent;
    private int duration;
    private Tile dest;
    private Path path;
    /**
     * Default constructor, sets a cell to be asleep and off the grid
     */
    public Intent() {
        this.intent  = Behavior.SLEEP;
        this.duration = 0;
        this.dest = null;
        this.path = null;
    }

    /**
     * Generates a specified intent, initializes the intent to be off the grid
     * @param b The behavior to use, presumes not a path
     * @param dur The duration of the intent
     */
    public Intent(Behavior b, int dur) {
        this.intent = b;
        this.duration = dur;
        this.dest = null;
        this.path = null;
    }

    /**
     * Constructor to clone an intent
     * @param i The intent to clone
     */
    public Intent(Intent i) {
        this.intent = i.intent;
        this.duration = i.duration;
        this.dest = i.dest;
        this.path = i.path;
    }

    // Getters

    /**
     * Gets the set intent
     * @return The intent, as ROAM, SLEEP, etc.
     */
    public Behavior getIntent() { return this.intent; }

    /**
     * Gets the duration of the intent. Typically, how long until a new intent is generated
     * @return The duration
     */
    public int getDuration() { return this.duration; }

    /**
     * Setter to overwrite intent, only partially filled to change a state (perhaps from SLEEP to QUARANTINE)
     * @param b The behavior to set
     * @param dur The duration of the set behavior
     */
    public void setIntent(Behavior b, int dur) {
        this.intent = b;
        this.duration = dur;
        if (b != Behavior.PATHTO) { // If we aren't pathing, clear old paths
            this.path = null;
            this.dest = null;
        }
    }

    /**
     * Method to age the intents of each cell
     * @return The remaining duration
     */
    public int tickIntent() {
        if (path != null) // If we're pathing, set to path length
            this.duration = path.getLength();
        if (this.duration > 0) { // Age the dur by 1
            this.duration--;
            return this.duration;
        }
        return -1; // Ret -1 on completed intent
    }

    /**
     * Returns the path, if one exists, for this intent
     * @return The path
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Sets a path for a person if it intends to travel somewhere
     *
     * @param dest The destination the intent wants to get to
     * @param p The path to follow
     */
    public void setPath(Tile dest, Path p) {
        this.intent = Behavior.PATHTO;
        this.duration = p.getLength();
        this.dest = dest;
        this.path = p;
    }

    /**
     * Gives the string representation of the intent
     *
     * @return The string representation of the intent
     */
    public String toString() {
        String string = "Intent: " + this.intent + "\n";
        string += "Duration: " + this.duration + "\n";
        return string;
    }

}
