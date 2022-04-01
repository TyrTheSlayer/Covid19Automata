/**
 * @author Wesley Camphouse, Aedan Wells
 *
 * The class that represents a building
 */

package Grid;

import DataObjects.Person;

import java.util.ArrayList;
import java.util.Random;

public class Building {
    private int capacity;
    private double size;
    //OpeningTime is when people can enter or exit a building. The tick time % 60, remainder will be time
    private int openingTime;
    private int closingTime;
    private boolean maskMandate;
    private boolean vaccMandate;
    ArrayList<Person> occupants;
    Tile[] entrances;
    Tile[] exits;
    ArrayList<Tile> space;

    //Constructors
    /**
     * Makes a building with the given sides, entrances, and exits
     *
     * @param size The size of the building
     * @param entrances The entrances to the building
     * @param exits The exits to the building
     */
    public Building(double size, Tile[] entrances, Tile[] exits, int openingTime, int closingTime, boolean maskMandate,
                    boolean vaccMandate, int capacity, ArrayList<Tile> spaces) {
        //Assign the attributes
        this.size = size;
        this.entrances = entrances;
        this.exits = exits;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.maskMandate = maskMandate;
        this.vaccMandate = vaccMandate;
        this.capacity = capacity;
        this.space = spaces;

        //Update the entrances on the tile side
        for(Tile i : this.entrances) {
            i.setEntranceTo(this);
        }

        //Block all of the tiles that are in the building
        for(Tile i : this.space)
            i.setAccessible(false);
    }

    //Methods
    /**
     * Allows a person to enter the building
     *
     * @param person The person looking to enter
     */
    public void enter(Person person) {
        person.setPosition(-1, -1);
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

    /**
     * Infects the people in the building
     */
    public void tickInfect() {
        //Loop through the occupants
        for(Person i : this.occupants) {
            //Check if they cough
            if(i.cough()) {
                Random rand = new Random();

                //They get 8 chances to infect
                for(int j = 0; j < 8; j++) {
                    int index = rand.nextInt(this.capacity);

                    if(index < this.occupants.size()) {
                        this.occupants.get(index).infect(i); //Otherwise, try to infect them
                    }
                }
            }
        }
    }
}
