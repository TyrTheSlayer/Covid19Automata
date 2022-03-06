/**
 * @author Wesley Camphouse
 *
 * The actual person object, keeps track of all the data related to a given person
 */

package DataObjects;

import Simulator.Factor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Person {
    private Status status;
    private int x;
    private int y;
    private Virus virus;
    private ArrayList<Factor> factors;

    //Constructors
    /**
     * Makes a new, healthy person at the given location, with the given list of factors
     *
     * @param x The x location
     * @param y The y location
     * @param factors The list of factors
     */
    public Person(int x, int y, ArrayList<Factor> factors) {
        this.x = x;
        this.y = y;
        this.factors = factors;
        this.status = Status.ALIVE;
        this.virus = null;
    }

    //Setters
    /**
     * Sets the person's location to the given coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Directly set the person to have a given virus instance
     *
     * @param virus The virus to infect them with
     */
    public void setVirus(Virus virus) {
        this.virus = virus;
        this.status = Status.INFECTED;
    }

    //Getters
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    //Methods
    /**
     * Moves a person the given number of tiles vertically or horizontally
     *
     * @param x The horizontal amount
     * @param y The vertical amount
     */
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Updates the person's virus
     */
    public void updateVirus() {
        //Auto-leave the method if the person is uninfected
        if(this.virus == null)
            return;

        //Update any timers on the virus
        this.virus.tick();

        //Update the person based on the virus stage
        switch (this.virus.getStage()) {
            case RECOVERED: //They recovered
                this.virus = null;
                this.status = Status.RECOVERED;
                break;
            case FATAL: //They died
                this.die();
                break;
        }
    }

    /**
     * Adds a factor to the person
     *
     * @param factor The factor to add
     */
    public void addFactor(Factor factor) {
        this.factors.add(factor);
    }

    /**
     * Tells whether or not the person should cough this tick
     *
     * @return True if they should, false otherwise
     */
    public boolean cough() {
        //Don't cough if they're not sick
        if(!this.isContagious())
            return false;

        //If they're sick, there's a chance they'll cough
        Random rand = new Random();

        //Calculate the chance using the factors
        double chance = 0.3;
        for(Factor i : this.factors) {
            chance = i.applyFactorGive(chance);
        }

        //Check if a random number beats the calculated chance
        if(rand.nextDouble() < chance) {
            return true;
        }
        return false;
    }

    /**
     * Kills the person
     */
    public void die() {
        this.status = Status.DEAD;
        this.virus = null;
        this.x = -666;
        this.y = -666;
    }

    /**
     * Tells whether or not the person is contagious
     *
     * @return True if they are, false otherwise
     */
    public boolean isContagious() {
        //Immediately return false if they're not infected
        if(this.virus == null)
            return false;

        //Otherwise, only return false if the virus is incubating
        if(this.virus.getStage() == VirusStage.INCUBATING)
            return false;

        return true;
    }

    /**
     * Tries to infect a person with this one's virus, applying it if they succeed
     *
     * @param infector The person who is trying to infect us
     * @return True if we are infected, false otherwise
     */
    public boolean infect(Person infector) {
        //Auto reject if we're already infected or recovered
        if(this.virus != null || this.status == Status.RECOVERED)
            return false;

        //Base chance
        double chance = 0.016;

        //Apply all the factors to the chance
        for(Factor i : this.factors) {
            chance = i.applyFactorGet(chance);
        }

        //Check if a random number beats the chance
        Random rand = new Random();
        //If it does, give us the virus, return true
        if(rand.nextDouble() < chance) {
            this.virus = infector.virus.getType().genVirus(this);
            this.status = Status.INFECTED;
            return true;
        }

        return false;
    }

    /**
     * Draws the person given the size and graphics environment
     *
     * @param size The size of the person
     * @param canvas The graphics environment
     */
    public void draw(int size, Graphics2D canvas) {
        //Store the old color
        Color oldCol = canvas.getColor();

        //Determine person color and then draw them
        switch (this.status) {
            case ALIVE:
                canvas.setColor(Color.BLUE);
                break;
            case INFECTED:
                canvas.setColor(Color.MAGENTA);
                break;
            case DEAD:
                canvas.setColor(Color.BLACK);
                break;
            case RECOVERED:
                canvas.setColor(Color.CYAN);
                break;
        }
        canvas.fillOval((size * this.x) + size/4, (size * this.y) + size/4,
                (int)Math.ceil((double)size/2), (int)Math.ceil((double)size/2));

        //Restore the color
        canvas.setColor(oldCol);
    }
}
