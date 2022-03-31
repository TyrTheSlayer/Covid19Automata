/**
 * @author Wesley Camphouse, Aedan Wells
 *
 * The class that represents a single factor influencing infection
 */

package Simulator;

public class Factor {
    private double severityGet;
    private double severityGive;
    private double age;
    private boolean vaccinated = false;
    private double wfr; // Should be bounded between 0 and 1, 0 is unruly, 1 is following all rules always
    private double vaccination_effectivity;



    //Constructors
    /**
     * Makes a new factor with the given severities
     *
     * @param severityGet How much this factor affects inward infection chance
     * @param severityGive How much this factor affects outward infection chance
     */
    public Factor(double severityGet, double severityGive, double age, boolean vaccinated, double wfr, double vaccination_effectivity) {
        this.severityGet = severityGet;
        this.severityGive = severityGive;
        this.age = age;
        this.vaccinated = vaccinated;
        this.wfr = wfr;
        this.vaccination_effectivity = vaccination_effectivity;
    }

    public Factor() {
        this.severityGet = 1;
        this.severityGive = 1;
        this.age = 0; // Left for poisson distribution
        this.vaccinated = false;
        this.wfr = 0.5; // Eventually add some randomness here
        this.vaccination_effectivity = 0.5; // Figure out how to pull this from SimSettings
    }

    public Factor vaccinate() {
        this.vaccinated = true;
        this.severityGet *= vaccination_effectivity;
        this.severityGive *= vaccination_effectivity;
        return this;
    }

    //Methods
    /**
     * Applies the effect this factor has on the current chance to get infected
     *
     * @param oldChance The old chance of infection
     * @return The new chance of infection
     */
    public double applyFactorGet(double oldChance) {
        return oldChance * this.severityGet;
    }

    /**
     * Applies the effect this factor has on the current chance to give infection
     *
     * @param oldChance The old chance of infection
     * @return The new chance of infection
     */
    public double applyFactorGive(double oldChance) {
        return oldChance * this.severityGive;
    }

    /**
     * gets age
     *
     * @returns age of person
     */
    public double getAge(){
        return this.age;
    }

}
