/**
 * @author Wesley Camphouse
 *
 * The class that represents a single factor influencing infection
 */

package custom_classes;

public class Factor {
    private double severityGet;
    private double severityGive;

    //Constructors
    /**
     * Makes a new factor with the given severities
     *
     * @param severityGet How much this factor affects inward infection chance
     * @param severityGive How much this factor affects outward infection chance
     */
    public Factor(double severityGet, double severityGive) {
        this.severityGet = severityGet;
        this.severityGive = severityGive;
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
}
