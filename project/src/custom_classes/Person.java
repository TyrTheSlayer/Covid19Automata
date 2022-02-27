/**
 * @author Wesley Camphouse
 *
 * The actual person object, keeps track of all the data related to a given person
 */

package custom_classes;

import java.awt.*;
import java.util.ArrayList;

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
    }

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
        return false;
    }

    /**
     * Kills the person
     */
    public void die() {
        this.status = Status.DEAD;
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
     * @param target The other person
     * @return True if the other person was infected, false otherwise
     */
    public boolean infect(Person target) {
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
        canvas.fillOval(size * this.x, size * this.y, size, size);

        //Restore the color
        canvas.setColor(oldCol);
    }
}
