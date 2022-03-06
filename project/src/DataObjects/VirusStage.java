/**
 * @author Wesley Camphouse
 *
 * The enumeration for the stage a virus is at
 */

package DataObjects;

public enum VirusStage {
    INCUBATING, CONTAGIOUS, SYMPTOMATIC, FATAL, RECOVERED;

    /**
     * Gives the string representation of the stage
     *
     * @return The string representation of the virus stage
     */
    public String toString() {
        switch (this) {
            case INCUBATING:
                return "Incubating";
            case CONTAGIOUS:
                return "Contagious";
            case SYMPTOMATIC:
                return "Symptomatic";
            case FATAL:
                return "Fatal";
            case RECOVERED:
                return "Recovered";
            default:
                return "";
        }
    }
}
