package DataObjects;
import Grid.Building;
import java.util.ArrayList;

/**
 * Written by Wesley Camphouse
 *
 * A daily schedule, to help people figure out where to go
 */

public class DailySchedule {
    //Static list of valid schedules
    private static ArrayList<DailySchedule> schedules = new ArrayList<>(); //General
    private static ArrayList<DailySchedule> workSchedules = new ArrayList<>(); //Work Schedules
    private static ArrayList<DailySchedule> errandSchedules = new ArrayList<>(); //Errand schedules

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

    //Getters
    /**
     * Gets the name of the schedule
     *
     * @return The name of the schedule
     */
    public String getName() {
        return this.name;
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
     * Get the arraylist of schedules
     *
     * @return The general list of schedules
     */
    public static ArrayList<DailySchedule> getSchedules() {
        return schedules;
    }

    /**
     * Get the arraylist of work schedules
     *
     * @return The list of work schedules
     */
    public static ArrayList<DailySchedule> getWorkSchedules() {
        return workSchedules;
    }

    /**
     * Get the arraylist of errand schedules
     *
     * @return The list of errand schedules
     */
    public static ArrayList<DailySchedule> getErrandSchedules() {
        return errandSchedules;
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

    /**
     * Make a simple work schedule, using the given building as a workplace
     *
     * @param workplace The workplace
     * @return The schedule
     */
    public static DailySchedule makeWorkSchedule(Building workplace) {
        ArrayList<TimeCard> obligations = new ArrayList<>();

        //Add the home, the workplace, then the home again
        obligations.add(new TimeCard(workplace, 0.1, 0.9));

        //Add it to the workplace schedules
        DailySchedule schedule = makeSchedule("BasicWorkSchedule", obligations);
        workSchedules.add(schedule);

        return schedule;
    }

    /**
     * Make a simple errand schedule, using the array of buildings as the targets
     *
     * @param targets The targets
     * @return The schedule
     */
    public static DailySchedule makeErrandSchedule(ArrayList<Building> targets) {
        ArrayList<TimeCard> obligations = new ArrayList<>();

        //Iterate through, making time cards for each building
        double l = 0.8 / targets.size();
        for(int i = 0; i < targets.size(); i++) {
            obligations.add(new TimeCard(targets.get(i), (l * i) + 0.1, (l * (i + 1)) + 0.1));
        }

        //Add it to the errand schedules
        DailySchedule schedule = makeSchedule("BasicErrandSchedule", obligations);
        errandSchedules.add(schedule);

        return schedule;
    }
}
