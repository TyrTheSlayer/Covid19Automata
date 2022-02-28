/**
 * @author Jonathan Carsten
 *
 * Data Class that stores settings for simulation
 * Also assigns defaults for settings
 * The idea is that the UI could declare a SimSettings object and then set values as the user adjusts settings
 */

package custom_classes;

import java.util.Arrays;

public class SimSettings {
    private int population;
    private double initialInfected;
    private double vaxRate;
    private double maskRate;
    private double socialDistRate;
    private boolean isTestRequired;
    private boolean isQuaranRequired;
    private boolean treatmentExists;
    private int numBuildings;
    private double beta;
    private String[] variants;
    private int simDuration;
    private int ticksPerDay;

    // VirusType specific attributes
    private VirusType virus;
    private int minContagiousTime;
    private int maxContagiousTime;
    private int minSymptomaticTime;
    private int maxSymptomaticTime;
    private int minRecoveryTime ;
    private int maxRecoveryTime ;
    private int minDeathTime;
    private int maxDeathTime;

    /**
     * Inits default values for settings. These values are tentative and prone to change.
     * Since there are many attributes, I thought it best to init a SimSettings object with
     * defaults first and then have users set the values later.
     */
    public SimSettings(){
        this.population = 1500;
        this.initialInfected = .05;
        this.vaxRate = .65;
        this.maskRate = .44;
        this.socialDistRate = .40; // haven't determined default yet
        this.isTestRequired = true;
        this.isQuaranRequired = false;
        this.treatmentExists = false;
        this.numBuildings = 0;
        this.beta = .016; // beta per sympomatic contact, pulled from covasim
        this.variants = new String[] {"Alpha", "", ""};
        this.simDuration = 150; // user defines duration in terms of days
        this.ticksPerDay = 60; // tentative, for now say 60 ticks = 1 day, (60 fps -> 1 sec = 1 day, idk)

        // VirusType defaults
        this.virus = new VirusType();

    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getVaxRate() {
        return vaxRate;
    }

    public void setVaxRate(double vaxRate) {
        this.vaxRate = vaxRate;
    }

    public double getMaskRate() {
        return maskRate;
    }

    public void setMaskRate(double maskRate) {
        this.maskRate = maskRate;
    }

    public double getSocialDistRate() {
        return socialDistRate;
    }

    public void setSocialDistRate(double socialDistRate) {
        this.socialDistRate = socialDistRate;
    }

    public boolean isTestRequired() {
        return isTestRequired;
    }

    public void setTestRequired(boolean testRequired) {
        isTestRequired = testRequired;
    }

    public boolean isQuaranRequired() {
        return isQuaranRequired;
    }

    public void setQuaranRequired(boolean quaranRequired) {
        isQuaranRequired = quaranRequired;
    }

    public int getNumBuildings() {
        return numBuildings;
    }

    public void setNumBuildings(int numBuildings) {
        this.numBuildings = numBuildings;
    }

    public boolean isTreatmentExists() {
        return treatmentExists;
    }

    public void setTreatmentExists(boolean treatmentExists) {
        this.treatmentExists = treatmentExists;
    }

    public double getInitialInfected() {
        return initialInfected;
    }

    public void setInitialInfected(double initialInfected) {
        this.initialInfected = initialInfected;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public String[] getVariants() {
        return variants;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }

    public int getSimDuration() {
        return simDuration;
    }

    public void setSimDuration(int simDuration) {
        this.simDuration = simDuration;
    }

    public int getTicksPerDay() {
        return ticksPerDay;
    }

    public void setTicksPerDay(int ticksPerDay) {
        this.ticksPerDay = ticksPerDay;
    }

    public VirusType getVirus() {
        return virus;
    }

    public void setMinContagiousTime(int minContagiousTime) {
        this.virus.setMinContagiousTime(minContagiousTime);
    }

    public void setMaxContagiousTime(int maxContagiousTime) {
        this.virus.setMaxContagiousTime(maxContagiousTime);
    }

    public void setMinSymptomaticTime(int minSymptomaticTime) {
        this.virus.setMinSymptomaticTime(minSymptomaticTime);
    }

    public void setMaxSymptomaticTime(int maxSymptomaticTime) {
        this.virus.setMaxSymptomaticTime(maxSymptomaticTime);
    }

    public void setMinRecoveryTime(int minRecoveryTime) {
        this.virus.setMinRecoveryTime(minRecoveryTime);
    }

    public void setMaxRecoveryTime(int maxRecoveryTime) {
        this.virus.setMaxRecoveryTime(maxRecoveryTime);
    }

    public void setMinDeathTime(int minDeathTime) {
        this.virus.setMinDeathTime(minDeathTime);
    }

    public void setMaxDeathTime(int maxDeathTime) {
        this.virus.setMaxDeathTime(maxDeathTime);
    }

    @Override
    public String toString() {
        return "SimSettings{" +
                "population=" + population +
                ", initialInfected=" + initialInfected +
                ", vaxRate=" + vaxRate +
                ", maskRate=" + maskRate +
                ", socialDistRate=" + socialDistRate +
                ", isTestRequired=" + isTestRequired +
                ", isQuaranRequired=" + isQuaranRequired +
                ", treatmentExists=" + treatmentExists +
                ", numBuildings=" + numBuildings +
                ", beta=" + beta +
                ", variants=" + Arrays.toString(variants) +
                ", simDuration=" + simDuration +
                ", ticksPerDay=" + ticksPerDay +
                '}';
    }
}
