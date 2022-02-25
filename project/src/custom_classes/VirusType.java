/**
 * @author Wesley Camphouse
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

    /**
     * Generates a new instance of this type of virus for the person provided
     *
     * @param person The person
     * @return The new virus
     */
    public Virus genVirus(Person person) {
        return null;
    }
}
