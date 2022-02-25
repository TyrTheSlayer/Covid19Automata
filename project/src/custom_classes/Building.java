/**
 * @author Wesley Camphouse
 *
 * The class that represents a building
 */

package custom_classes;

import GridVisualization.Tile;

import java.util.ArrayList;

public class Building {
    private double size;
    ArrayList<Person> occupants;
    Tile[] entrances;
    Tile[] exits;

    /**
     * Allows a person to enter the building
     *
     * @param person The person looking to enter
     */
    public void enter(Person person) {
        this.occupants.add(person);
    }

    /**
     * Allows a person to exit the building
     *
     * @param person The person looking to leave
     */
    public void exit(Person person) {
        this.occupants.remove(person);
    }
}
