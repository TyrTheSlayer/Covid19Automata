/**
 * @author Wesley Camphouse, Aedan Wells, Jonathan Carsten
 *
 * The object representing a type of virus
 */

package custom_classes;

import java.util.Random;

public class VirusType {
    private int minContagiousTime;
    private int maxContagiousTime;
    private int minSymptomaticTime;
    private int maxSymptomaticTime;
    private int minRecoveryTime;
    private int maxRecoveryTime;
    private int minDeathTime;
    private int maxDeathTime;

    //Constructors
    /**
     * Make a new virus type with the given timer bounds in terms of ticks
     *
     * @param minContagiousTime The minimum time until the virus is contagious
     * @param maxContagiousTime The maximum time until the virus is contagious
     * @param minSymptomaticTime The minimum time until the virus causes symptoms
     * @param maxSymptomaticTime The maximum time until the virus causes symptoms
     * @param minRecoveryTime The minimum time it takes to recover from the virus
     * @param maxRecoveryTime The maximum time it takes to recover from the virus
     * @param minDeathTime The minimum time it takes to die from the virus
     * @param maxDeathTime The maximum time it takes to die from the virus
     */
    public VirusType(int minContagiousTime, int maxContagiousTime, int minSymptomaticTime, int maxSymptomaticTime,
                     int minRecoveryTime, int maxRecoveryTime, int minDeathTime, int maxDeathTime) {
        this.minContagiousTime = minContagiousTime;
        this.maxContagiousTime = maxContagiousTime;
        this.minSymptomaticTime = minSymptomaticTime;
        this.maxSymptomaticTime = maxSymptomaticTime;
        this.minRecoveryTime = minRecoveryTime;
        this.maxRecoveryTime = maxRecoveryTime;
        this.minDeathTime = minDeathTime;
        this.maxDeathTime = maxDeathTime;
    }

    /**
     * Make a virus type with the default values
     */
    public VirusType() {
        this.minContagiousTime = 60;
        this.maxContagiousTime = 300;
        this.minSymptomaticTime = 780;
        this.maxSymptomaticTime = 2280;
        this.minRecoveryTime = 840;
        this.maxRecoveryTime = 2520;
        this.minDeathTime = 1110;
        this.maxDeathTime = 3000;
    }

    //Methods
    /**
     * Generates a new instance of this type of virus for the person provided
     * Very placeholder for now
     *
     * @param person The person
     * @return The new virus
     */

    public Virus genVirus(Person person) {
        //Randomly generate all of the timers
        Random rand = new Random();
        int cTime = rand.nextInt(this.maxContagiousTime - this.minContagiousTime + 1) + this.minContagiousTime;
        int sTime = rand.nextInt(this.maxSymptomaticTime - this.minSymptomaticTime + 1) + this.minSymptomaticTime;
        int rTime = rand.nextInt(this.maxRecoveryTime - this.minRecoveryTime + 1) + this.minRecoveryTime;
        int dTime = rand.nextInt(this.maxDeathTime - this.minDeathTime + 1) + this.minDeathTime;

        //Assign them to the new virus
        return new Virus(this, cTime, sTime, rTime, dTime);
    }

    public int getMinContagiousTime() {
        return minContagiousTime;
    }

    public int getMaxContagiousTime() {
        return maxContagiousTime;
    }

    public int getMinSymptomaticTime() {
        return minSymptomaticTime;
    }

    public int getMaxSymptomaticTime() {
        return maxSymptomaticTime;
    }

    public int getMinRecoveryTime() {
        return minRecoveryTime;
    }

    public int getMaxRecoveryTime() {
        return maxRecoveryTime;
    }

    public int getMinDeathTime() {
        return minDeathTime;
    }

    public int getMaxDeathTime() {
        return maxDeathTime;
    }

    public void setMinContagiousTime(int minContagiousTime) {
        this.minContagiousTime = minContagiousTime;
    }

    public void setMaxContagiousTime(int maxContagiousTime) {
        this.maxContagiousTime = maxContagiousTime;
    }

    public void setMinSymptomaticTime(int minSymptomaticTime) {
        this.minSymptomaticTime = minSymptomaticTime;
    }

    public void setMaxSymptomaticTime(int maxSymptomaticTime) {
        this.maxSymptomaticTime = maxSymptomaticTime;
    }

    public void setMinRecoveryTime(int minRecoveryTime) {
        this.minRecoveryTime = minRecoveryTime;
    }

    public void setMaxRecoveryTime(int maxRecoveryTime) {
        this.maxRecoveryTime = maxRecoveryTime;
    }

    public void setMinDeathTime(int minDeathTime) {
        this.minDeathTime = minDeathTime;
    }

    public void setMaxDeathTime(int maxDeathTime) {
        this.maxDeathTime = maxDeathTime;
    }
}
