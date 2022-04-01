/**
 * Written by Wesley Camphouse
 *
 * Simple data storage for a scheduled obligation a person might have
 */

package DataObjects;
import Grid.Building;

public class TimeCard {
    private Building target;

    //Both of these should be between 0 and 1
    private double startTime;
    private double endTime;

    //Constructors
    /**
     * Makes a new timecard with the given location, starttime, and endtime
     * @param target The building
     * @param startTime The starting time
     * @param endTime The ending time
     */
    public TimeCard(Building target, double startTime, double endTime) {
        //Error check the start and end times
        //They need swapped
        if(startTime > endTime) {
            double tmp = startTime;
            startTime = endTime;
            endTime = tmp;
        }
        //They're out of bounds
        if(startTime < 0 || endTime > 1) {
            System.out.println("Bad start/end time on a timecard");
            System.exit(1);
        }

        this.target = target;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //Getters
    /**
     * Get the target
     *
     * @return The target
     */
    public Building getTarget() {
        return target;
    }
    /**
     * Get the start time
     *
     * @return The start time
     */
    public double getStartTime() {
        return startTime;
    }
    /**
     * Get the end time
     *
     * @return The end time
     */
    public double getEndTime() {
        return endTime;
    }

    //Methods
    /**
     * Tells whether or not a given time is in range of this timecard
     *
     * @param time The time
     * @return True if it's in range, false otherwise
     */
    public boolean inRange(double time) {
        return time > this.startTime && time < this.endTime;
    }
}
