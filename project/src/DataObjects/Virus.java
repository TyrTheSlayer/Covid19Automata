package DataObjects;

/**
 * @author Wesley Camphouse
 *
 * The class used to represent a given instance of a virus
 */
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

    /*
    // Need to work on a much better way to randomize virus instances... - Brenton
    public Virus(VirusType type) {
        Random rand = new Random();
        this.type = type;
        this.timeToContagious = (int) (rand.nextGaussian() * (type.getMaxContagiousTime() - type.getMinContagiousTime()) + ((type.getMinContagiousTime() + type.getMaxContagiousTime())/2));
        this.timeToSymptomatic = (int) (rand.nextGaussian() * (type.getMaxSymptomaticTime() - type.getMinSymptomaticTime()) + ((type.getMinSymptomaticTime()+type.getMaxSymptomaticTime())/2));
        this.timeToRecovery = (int) (rand.nextGaussian() * (type.getMinRecoveryTime() - type.getMaxRecoveryTime()) + ((type.getMinRecoveryTime()+type.getMaxRecoveryTime())/2));
        this.timeToDeath = (int) (rand.nextGaussian() * (type.getMinDeathTime() - type.getMaxDeathTime()) + ((type.getMinDeathTime())+type.getMaxDeathTime())/2);
        this.stage = VirusStage.INCUBATING;
    }
    */

    //Getters
    /**
     * Gets the stage of the virus
     *
     * @return The stage of the virus
     */
    public VirusStage getStage() {
        return stage;
    }

    /**
     * Gets the type of the virus
     *
     * @return The type of the virus
     */
    public VirusType getType() {
        return type;
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
