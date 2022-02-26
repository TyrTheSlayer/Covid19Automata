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


    //Methods
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
