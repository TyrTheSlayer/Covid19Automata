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

    //Constructors
    /**
     * Makes a new virus with the given type and timers
     *
     * @param type The type of virus
     * @param timeToContagious How long after infection the person becomes contagious
     * @param timeToSymptomatic How long after contagion the person will start show symptoms
     * @param timeToRecovery How long after infection the person takes to recover
     * @param timeToDeath How long after symptoms the person will take to die
     */
    public Virus(VirusType type, int timeToContagious, int timeToSymptomatic, int timeToRecovery, int timeToDeath) {
        this.type = type;
        this.timeToContagious = timeToContagious;
        this.timeToSymptomatic = timeToSymptomatic;
        this.timeToRecovery = timeToRecovery;
        this.timeToDeath = timeToDeath;
        this.stage = VirusStage.INCUBATING;
    }

    //Getters
    /**
     * Gets the stage of the virus
     *
     * @return The stage of the virus
     */
    public VirusStage getStage() {
        return stage;
    }

    //Methods
    /**
     * Ticks all of the timers of the virus
     */
    public void tick() {
        //Determine what other timers need to tick, update states as they do
        switch (this.stage) {
            case INCUBATING: //The virus is incubating
                this.timeToContagious--;
                if(this.timeToContagious <= 0)
                    this.stage = VirusStage.CONTAGIOUS;
                break;
            case CONTAGIOUS: //The virus is contagious
                this.timeToSymptomatic--;
                if(this.timeToSymptomatic <= 0)
                    this.stage = VirusStage.SYMPTOMATIC;
                break;
            case SYMPTOMATIC: //The virus is causing symptoms
                this.timeToDeath--;
                if(this.timeToDeath <= 0)
                    this.stage = VirusStage.FATAL;
                break;
        }

        //Always tick down the recovery timer
        this.timeToRecovery--;
        if(this.timeToRecovery <= 0 && this.stage != VirusStage.FATAL)
            this.stage = VirusStage.RECOVERED;
    }
}
