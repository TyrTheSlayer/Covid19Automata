/**
 * @author Wesley Camphouse, Aedan Wells
 *
 * The class that represents a building
 */

package Grid;

import DataObjects.Person;
import Simulator.BehaviorAgent;
import Simulator.Intent;

import java.util.ArrayList;
import java.util.Random;

public class Building {
    private int capacity;
    //OpeningTime is when people can enter or exit a building. The tick time % 60, remainder will be time
    private int openingTime;
    private int closingTime;
    private boolean maskMandate;
    private boolean vaccMandate;
    ArrayList<Person> occupants;
    Tile[] entrances;
    Tile[] exits;
    ArrayList<Tile> space;
    private BehaviorAgent ba;

    //Constructors
    /**
     * Makes a building with the given sides, entrances, and exits
     *
     * @param entrances The entrances to the building
     * @param exits The exits to the building
     * @param openingTime The time the building opens
     * @param closingTime The time the building closes
     * @param maskMandate Whether or not the building has a mask mandate
     * @param vaccMandate Whether or not the building has a mask mandate
     * @param capacity The maxiumum number of people that can fit into the buidling
     * @param spaces The tiles that the building occupies
     */
    public Building(Tile[] entrances, Tile[] exits, int openingTime, int closingTime, boolean maskMandate,
                    boolean vaccMandate, int capacity, ArrayList<Tile> spaces, BehaviorAgent ba) {
        //Assign the attributes
        this.entrances = entrances;
        this.exits = exits;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.maskMandate = maskMandate;
        this.vaccMandate = vaccMandate;
        this.capacity = capacity;
        this.space = spaces;
        this.occupants = new ArrayList<>();
        this.ba = ba;

        //Update the entrances on the tile side
        for(Tile i : this.entrances) {
            i.setEntranceTo(this);
        }

        //Block all of the tiles that are in the building, taking occupants
        for(Tile i : this.space) {
            if (i.getOccupant() != null) {
                //Give the person a bogus position
                i.getOccupant().setPosition(-2, -2);
                this.occupants.add(i.getOccupant());
                ba.genIntent(i.getOccupant());
                i.clearOccupant();
            }

            i.setAccessible(false);
        }
    }

    /**
     * Makes a building with the given sides, entrances, and exits, that never closes
     *
     * @param entrances The entrances to the building
     * @param exits The exits to the building
     * @param maskMandate Whether or not the building has a mask mandate
     * @param vaccMandate Whether or not the building has a mask mandate
     * @param capacity The maxiumum number of people that can fit into the buidling
     * @param spaces The tiles that the building occupies
     */
    public Building(Tile[] entrances, Tile[] exits, boolean maskMandate,
                    boolean vaccMandate, int capacity, ArrayList<Tile> spaces, BehaviorAgent ba) {
        //Assign the attributes
        this.entrances = entrances;
        this.exits = exits;
        this.openingTime = 0;
        this.closingTime = 999999999;
        this.maskMandate = maskMandate;
        this.vaccMandate = vaccMandate;
        this.capacity = capacity;
        this.space = spaces;
        this.occupants = new ArrayList<>();
        this.ba = ba;

        //Update the entrances on the tile side
        for(Tile i : this.entrances) {
            i.setEntranceTo(this);
        }

        //Block all of the tiles that are in the building, taking occupants
        for(Tile i : this.space) {
            if (i.getOccupant() != null) {
                //Give the person a bogus position
                i.getOccupant().setPosition(-2, -2);
                this.occupants.add(i.getOccupant());
                ba.genIntent(i.getOccupant());
                i.clearOccupant();
            }

            i.setAccessible(false);
        }
    }


    //Setters

    /**
     * Sets the behavior agent for the building. A copy of the BA in grid,
     * used so buildings and the BA can pass people to one another
     * @param ba The BA to use
     */
    public void setBA(BehaviorAgent ba) {
        this.ba = ba;
    }



    //Getters

    public ArrayList<Person> getOccupants() {
        return occupants;
    }


    //Methods
    /**
     * Allows a person to enter the building
     *
     * @param person The person looking to enter
     * @param time The time in the simulation
     * @return True if the entrance was successful, false otherwise
     */
    public boolean enter(Person person, int time) {
        //Auto return if the building is at capacity
        if(this.occupants.size() >= this.capacity)
            return false;

        //Auto return if the building is closed
        if(time < this.openingTime || time > this.closingTime)
            return false;

        //Clear the tile the person is standing on (it should be an entrance)
        for(Tile i : this.entrances) {
            if(i.getX() == person.getX() && i.getY() == person.getY()) {
                //Give the person a bogus position
                person.setPosition(-2, -2);
                ba.genIntent(person);
                i.clearOccupant();

                //Add them to the building
                this.occupants.add(person);
            }
        }


        return true;
    }

    /**
     * Allows a person to enter the building
     *
     * @param person The person looking to enter
     * @return True if the entrance was successful, false otherwise
     */
    public boolean enter(Person person) {
        //Auto return if the building is at capacity
        if(this.occupants.size() >= this.capacity)
            return false;
        int time = ba.getTime();
        //Auto return if the building is closed
        if(time < this.openingTime || time > this.closingTime)
            return false;

        //Clear the tile the person is standing on (it should be an entrance)
        for(Tile i : this.entrances) {
            if(i.getX() == person.getX() && i.getY() == person.getY()) {
                //Give the person a bogus position
                person.setPosition(-2, -2);
                i.clearOccupant();

                //Add them to the building
                this.occupants.add(person);
            }
        }


        return true;
    }


    /**
     * Allows a person to exit the building
     *
     * @param person The person looking to leave
     * @return True if they exited, false otherwise
     */
    public boolean exit(Person person) {
        //Find a valid exit, auto return if we can't
        Tile exit = null;
        for(Tile i : this.exits) {
            if(i.isAccessible()) {
                exit = i;
                break;
            } else {
                ba.roam(i.getOccupant());
            }
        }
        if(exit == null) {
            ba.hold(person);
            return false;
        }

        //Add ourselves to the tile
        exit.setOccupant(person);
        //Copy it's position
        person.setPosition(exit.getX(), exit.getY());
//        ba.genIntent(person);
        //Exit the building arraylist
        this.occupants.remove(person);

        return true;
    }

    /**
     * Returns a random entrance tile, used for pathing
     *
     * @return A tile in this.entrances randomly
     */
    public Tile getRandEntrance() {
        Random r = new Random();
        return this.entrances[r.nextInt() % entrances.length];
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

                    //Try to infect the person if the infector didn't miss
                    if(index < this.occupants.size()) {
                        this.occupants.get(index).infect(i);
                    }
                }
            }
            if(i.getTarget(ba.getTime(), ba.ticksPerDay) != this)
                exit(i);
        }
    }

    /**
     * Generates building from the given array of types
     *
     * @param types The types to generate
     * @param tiles The tiles to use
     * @return An arraylist of buildings
     */
    public static ArrayList<Building> generateBuildings(BuildingType[] types, Tile[][] tiles, BehaviorAgent ba) {
        int x = 3;
        int y = 3;
        int maxHeight = 0; //The max recorded height of a building in the current row
        boolean failFlag = false;
        ArrayList<Building> buildings = new ArrayList<>();

        //Loop through the types
        for(int i = 0; i < types.length; i++) {
            ArrayList<Tile> needed = types[i].allocateTiles(tiles, x, y);

            //Check for an error
            if(needed == null) {
                //Multiple consecutive fails, something is actually wrong
                if(failFlag) {
                    System.out.println("Building generation failed");
                    System.exit(-1);
                }

                //First fail, we probably just need to move down
                else {
                    y += maxHeight + 3;
                    maxHeight = 0;
                    x = 3;
                    failFlag = true;
                    continue;
                }
            }

            //No error, actually make the building
            Tile[] entrance = new Tile[1];
            entrance[0] = tiles[x + (types[i].getW()/2) - 1][y + types[i].getH()];
            Tile[] exit = new Tile[1];
            exit[0] = tiles[x + (types[i].getW()/2) + 1][y + types[i].getH()];
            buildings.add(new Building(entrance, exit, false, false, 999, needed, ba));
            x += types[i].getW() + 3;
            maxHeight = Math.max(maxHeight, types[i].getH());
        }

        return buildings;
    }
}
