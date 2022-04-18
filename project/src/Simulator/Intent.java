/**
 * @author Brenton Candelaria
 *
 * @description A class to handle status/actions for a cell. Stores data to be used by the BehaviorAgent
 */

package Simulator;

import java.util.Random;
import Path.Path;
import Grid.Tile;

public class Intent {
    // Enumerate potential actions
    public enum Behavior {
        SLEEP, ROAM, QUARANTINE, PATHTO, DEAD;

        // Method to get a random behavior
        public static Behavior getRandomBehavior() {
            Random rand = new Random();
            return values()[rand.nextInt(values().length-1)]; // Change back to -1 after fixing things
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
     * @param b
     * @param dur
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
    public Behavior getIntent() { return this.intent; }
    public int getDuration() { return this.duration; }

    /**
     * Setter to overwrite intent, only partially filled to change a state (perhaps from SLEEP to QUARANTINE)
     * @param b
     * @param dur
     */
    public void setIntent(Behavior b, int dur) {
        this.intent = b;
        this.duration = dur;
    }

    /**
     * Method to age the intents of each cell
     * @return The remaining duration
     */
    public int tickIntent() {
        if (path != null)
            this.duration = path.getLength();
        if (this.duration > 0) {
            this.duration--;
            return this.duration;
        }
        return -1;
    }

    public Path getPath() {
        return this.path;
    }

    /**
     * Sets a path for a person if it intends to travel somewhere
     */
    public void setPath(Tile dest, Path p) {
        this.intent = Behavior.PATHTO;
        this.duration = p.getLength();
        this.dest = dest;
        this.path = p;
    }

}
