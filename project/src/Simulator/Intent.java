/**
 * @author Brenton Candelaria
 *
 * @description A class to handle status/actions for a cell. Stores data to be used by the BehaviorAgent
 */

package Simulator;

import java.util.Random;

public class Intent {
    // Enumerate potential actions
    public enum Behavior {
        SLEEP, ROAM, PATHTO, QUARANTINE, DEAD;

        // Method to get a random behavior
        public static Behavior getRandomBehavior() {
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }
    }

    private Behavior intent;
    private int duration;
    private int x;
    private int y;

    /**
     * Default constructor, sets a cell to be asleep and off the grid
     */
    public Intent() {
        this.intent  = Behavior.SLEEP;
        this.duration = 0;
        this.x = -1;
        this.y = -1;
    }

    /**
     * Generates a specified intent, initializes the intent to be off the grid
     * @param b
     * @param dur
     */
    public Intent(Behavior b, int dur) {
        this.intent = b;
        this.duration = dur;
        this.x = -1;
        this.y = -1;
    }

    /**
     * Constructor to clone an intent
     * @param i The intent to clone
     */
    public Intent(Intent i) {
        this.intent = i.intent;
        this.duration = i.duration;
        this.x = i.x;
        this.y = i.y;
    }

    // Getters
    public Behavior getIntent() { return this.intent; }
    public int getDuration() { return this.duration; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    /**
     * Setter to overwrite intent, only partially filled to change a state (perhaps from SLEEP to QUARANTINE)
     * @param b
     * @param dur
     */
    public void setIntent(Behavior b, int dur) {
        this.intent = b;
        this.duration = dur;
        this.x = -1;
        this.y = -1;
    }

    /**
     * Method to age the intents of each cell
     * @return The remaining duration
     */
    public int tickIntent() {
        if (this.duration > 0) {
            this.duration--;
            return this.duration;
        }
        return -1;
    }

    /**
     * Sets a path for a person if it intends to travel somewhere
     * @param x The x coordinate of the destination cell
     * @param y The y coordinate of the destination cell
     */
    public void setPath(int x, int y) {
        this.intent = Behavior.PATHTO;
        this.duration = 10;
        this.x = x;
        this.y = y;
    }

}
