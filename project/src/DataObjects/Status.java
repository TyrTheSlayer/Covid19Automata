package DataObjects;

/**
 * @author Aedan Wells
 *
 * The status enum for people
 */

public enum Status {
    ALIVE, INFECTED, DEAD, RECOVERED;

    /**
     * Gives the status of a person
     *
     * @return The status of the virus stage
     *
     */
    public Status getStatus(){
        return this;
    }
    /**
     * Gives the string representation of the stage
     *
     * @return The string representation of the virus stage
     */
    public String toString() {
        switch (this) {
            case ALIVE:
                return "Alive";
            case INFECTED:
                return "Infected";
            case DEAD:
                return "Dead";
            case RECOVERED:
                return "Recovered";
            default:
                return "";
        }
    }
}
