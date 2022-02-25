/**
 * @author Wesley Camphouse
 *
 * The actual person object, keeps track of all the data related to a given person
 */

package custom_classes;

import java.util.ArrayList;

public class Person {
    private Status status;
    private int x;
    private int y;
    private Virus virus;
    private ArrayList<Factor> factors;

    /**
     * Tells whether or not the person should cough this tick
     *
     * @return True if they should, false otherwise
     */
    public boolean cough() {
        return false;
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
}
