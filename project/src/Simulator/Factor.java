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



    //Constructors
    /**
     * Makes a new factor with the given severities
     *
     * @param severityGet How much this factor affects inward infection chance
     * @param severityGive How much this factor affects outward infection chance
     */
    public Factor(double severityGet, double severityGive, double age) {
        this.severityGet = severityGet;
        this.severityGive = severityGive;
        this.age = age;
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
