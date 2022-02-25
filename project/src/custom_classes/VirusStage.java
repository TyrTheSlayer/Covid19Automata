/**
 * @author Wesley Camphouse
 *
 * The enumeration for the stage a virus is at
 */

package custom_classes;

public enum VirusStage {
    INCUBATING, CONTAGIOUS, SYMPTOMATIC;

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
            default:
                return "";
        }
    }
}
