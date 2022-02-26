/**
 * @author Wesley Camphouse
 *
 * The class that represents a building
 */

package custom_classes;

import Grid2.Tile;

import java.util.ArrayList;

public class Building {
    private double size;
    ArrayList<Person> occupants;
    Tile[] entrances;
    Tile[] exits;

    //Constructors
    /**
     * Makes a building with the given sides, entrances, and exits
     *
     * @param size The size of the building
     * @param entrances The entrances to the building
     * @param exits The exits to the building
     */
    public Building(double size, Tile[] entrances, Tile[] exits) {
        //Assign the attributes
        this.size = size;
        this.entrances = entrances;
        this.exits = exits;

        //Update the entrances on the tile side
        for(Tile i : this.entrances) {
            i.setEntranceTo(this);
        }
    }

    //Methods
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
