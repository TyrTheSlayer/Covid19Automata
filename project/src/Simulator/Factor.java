package Simulator;

import java.util.Random;

/**
 * @author Wesley Camphouse, Aedan Wells, Samuel Nix
 *
 * The class that represents a single factor influencing infection
 */
public class Factor {
    private double severityGet;
    private double severityGive;
    private double age;
    private boolean vaccinated = false;
    private double vaccination_effectivity;
    private boolean willMask = false;

    //Constructors
    /**
     * Makes a new factor with the given severities
     *
     * @param severityGet How much this factor affects inward infection chance
     * @param severityGive How much this factor affects outward infection chance
     * @param age The age of the person
     * @param vaccinated Whether or not the person is vaccinated
     * @param vaccination_effectivity How effective the vaccine is
     */
    public Factor(double severityGet, double severityGive, double age, boolean vaccinated, double vaccination_effectivity) {
        this.severityGet = severityGet;
        this.severityGive = severityGive;
        this.age = age;
        this.vaccinated = vaccinated;
        this.vaccination_effectivity = vaccination_effectivity;
        this.willMask = new Random().nextBoolean();
    }

    /**
     * A default constructor, randomizes a Person's factors
     */
    public Factor() {
        this.severityGet = 1;
        this.severityGive = 1;
        this.age = getPoisson(1.75); //lamda of 1.75 achieves an age range of around 0-80
        this.vaccinated = false;
        this.vaccination_effectivity = 0.5; // Figure out how to pull this from SimSettings
        this.willMask = new Random().nextBoolean();
    }

    /**
     * A method to vaccinate individuals
     */
    public void vaccinate() {
        this.vaccinated = true;
        this.severityGet *= vaccination_effectivity;
        this.severityGive *= vaccination_effectivity;
    }

    /**
     * Returns whether this factor includes vaccination
     * @return Whether or not this is vaccinated
     */
    public boolean isVaccinated() {
        return this.vaccinated;
    }

    public boolean willMask() {
        return this.willMask;
    }

    //Methods
    /**
     * Applies the effect this factor has on the current chance to get infected
     *
     * @param oldChance The old chance of infection
     * @return The new chance of infection
     */
    public double applyFactorGet(double oldChance) {
        return oldChance * this.severityGet * this.getAgeMultiplier((int)this.age);
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
     * @return age of person
     */
    public double getAge(){
        return this.age;
    }


    public void setAge(double age) {
        this.age = age;
    }

    static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }
    static double gamma(double x) { return Math.exp(logGamma(x)); }

    /**
     * The age multiplier for this person's age. Should look somewhat like a negative poisson distribution
     * @param age The age of the individual
     * @return The multiplication factor of reception
     */
    private double getAgeMultiplier(int age) {
        double lambda = 3;
        double baseRate = (Math.pow(lambda, (double)age/10.0)/(gamma((double) age/10.0 + 1.0))) * Math.pow(Math.E, -lambda);
        return 1.0 - (4.0 * baseRate);

    }

    /**
     * Finds a random number along a poisson distribution curve (not perfect but should work)
     * @param lambda the factor
     * @return an age for a particular person
     */
    public static int getPoisson(double lambda) {
        double L = Math.exp(-lambda);
        double p = 4.0;
        int k = 0;

        do {
            k++;
            p *= Math.random();
        } while (p > L);

        return (int) (((k - 1) * 10) + (Math.random() * (11)));
    }
}
