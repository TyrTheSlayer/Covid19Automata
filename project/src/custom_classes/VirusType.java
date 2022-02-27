/**
 * @author Wesley Camphouse
 * @author Jonathan Carsten
 *
 * The object representing a type of virus
 */

package custom_classes;

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
     * Make a new virus type with the given timer bounds
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
    public VirusType() {
        this.minContagiousTime = minContagiousTime;
        this.maxContagiousTime = maxContagiousTime;
        this.minSymptomaticTime = minSymptomaticTime;
        this.maxSymptomaticTime = maxSymptomaticTime;
        this.minRecoveryTime = minRecoveryTime;
        this.maxRecoveryTime = maxRecoveryTime;
        this.minDeathTime = minDeathTime;
        this.maxDeathTime = maxDeathTime;
    }

    //Methods
    /**
     * Generates a new instance of this type of virus for the person provided
     *
     * @param person The person
     * @return The new virus
     */
    public Virus genVirus(Person person) {
        return null;
    }

    public int getMinContagiousTime() {
        return minContagiousTime;
    }

    public void setMinContagiousTime(int minContagiousTime) {
        this.minContagiousTime = minContagiousTime;
    }

    public int getMaxContagiousTime() {
        return maxContagiousTime;
    }

    public void setMaxContagiousTime(int maxContagiousTime) {
        this.maxContagiousTime = maxContagiousTime;
    }

    public int getMinSymptomaticTime() {
        return minSymptomaticTime;
    }

    public void setMinSymptomaticTime(int minSymptomaticTime) {
        this.minSymptomaticTime = minSymptomaticTime;
    }

    public int getMaxSymptomaticTime() {
        return maxSymptomaticTime;
    }

    public void setMaxSymptomaticTime(int maxSymptomaticTime) {
        this.maxSymptomaticTime = maxSymptomaticTime;
    }

    public int getMinRecoveryTime() {
        return minRecoveryTime;
    }

    public void setMinRecoveryTime(int minRecoveryTime) {
        this.minRecoveryTime = minRecoveryTime;
    }

    public int getMaxRecoveryTime() {
        return maxRecoveryTime;
    }

    public void setMaxRecoveryTime(int maxRecoveryTime) {
        this.maxRecoveryTime = maxRecoveryTime;
    }

    public int getMinDeathTime() {
        return minDeathTime;
    }

    public void setMinDeathTime(int minDeathTime) {
        this.minDeathTime = minDeathTime;
    }

    public int getMaxDeathTime() {
        return maxDeathTime;
    }

    public void setMaxDeathTime(int maxDeathTime) {
        this.maxDeathTime = maxDeathTime;
    }
}
