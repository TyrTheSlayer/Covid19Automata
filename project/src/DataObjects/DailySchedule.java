/**
 * Written by Wesley Camphouse
 *
 * A daily schedule, to help people figure out where to go
 */

package DataObjects;
import Grid.Building;
import java.util.ArrayList;

public class DailySchedule {
    //Static list of valid schedules
    private static ArrayList<DailySchedule> schedules = new ArrayList<>();

    //Schedule parts
    private String name;
    private ArrayList<TimeCard> timeCards;

    //Constructors
    /**
     * Makes a new daily constructor with the given name and timecards
     *
     * @param name The name
     * @param timeCards The time cards
     */
    private DailySchedule(String name, ArrayList<TimeCard> timeCards) {
        this.name = name;
        this.timeCards = timeCards;
    }

    //Methods
    /**
     * Get the target of the schedule at the given time
     *
     * @param time The time, as a portion of the way through the day (between 0 and 1)
     * @return The target building if there is one, and null otherwise
     */
    public Building getTarget(double time) {
        //Search for a fitting timecard
        for(TimeCard i : this.timeCards) {
            if(i.inRange(time))
                return i.getTarget(); //Immediately return if found
        }

        //Return null if we don't find a fitting timecard
        return null;
    }

    /**
     * Get the schedule with the given name
     *
     * @param name The name to look for. Is case sensitive.
     * @return The matching schedule, or null if none were found
     */
    public static DailySchedule getSchedule(String name) {
        //Look for the schedule in the list
        for(DailySchedule i : schedules) {
            if(i.name.equals(name))
                return i; //Return if found
        }

        //Otherwise, return null
        return null;
    }

    /**
     * Make a new schedule with the given name and timecard list
     *
     * @param name The name
     * @param timeCards The timecards
     * @return The schedule that was just made
     */
    public static DailySchedule makeSchedule(String name, ArrayList<TimeCard> timeCards) {
        //Make the schedule, add it to the list
        DailySchedule schedule = new DailySchedule(name, timeCards);
        schedules.add(schedule);

        //Return the schedule
        return schedule;
    }
}
