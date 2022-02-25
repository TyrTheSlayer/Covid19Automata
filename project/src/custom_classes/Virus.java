/**
 * @author Wesley Camphouse
 *
 * The class used to represent a given instance of a virus
 */

package custom_classes;

public class Virus {
    private VirusType type;
    private int timeToContagious;
    private int timeToSymptomatic;
    private int timeToRecovery;
    private int timeToDeath;
    private VirusStage stage;

    //Getters
    /**
     * Gets the stage of the virus
     *
     * @return The stage of the virus
     */
    public VirusStage getStage() {
        return stage;
    }
}
