/**
 * @author Wesley Camphouse
 *
 * The class that represents a single factor influencing infection
 */

package custom_classes;

public class Factor {
    private double severityGet;
    private double severityGive;

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
