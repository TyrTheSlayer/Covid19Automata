/**
 * @author Wesley Camphouse, Aedan Wells, Samuel Nix
 *
 * The class that represents a single factor influencing infection
 */

package Simulator;

public class Factor {
    private double severityGet;
    private double severityGive;
    private double age;
    private boolean vaccinated = false;
    private double wfr; // Should be bounded between 0 and 1, 0 is unruly, 1 is following all rules always
    private double vaccination_effectivity;

    //Constructors
    /**
     * Makes a new factor with the given severities
     *
     * @param severityGet How much this factor affects inward infection chance
     * @param severityGive How much this factor affects outward infection chance
     */
    public Factor(double severityGet, double severityGive, double age, boolean vaccinated, double wfr, double vaccination_effectivity) {
        this.severityGet = severityGet;
        this.severityGive = severityGive;
        this.age = age;
        this.vaccinated = vaccinated;
        this.wfr = wfr;
        this.vaccination_effectivity = vaccination_effectivity;
    }

    /**
     * A default constructor, randomizes a Person's factors
     */
    public Factor() {
        this.severityGet = 1;
        this.severityGive = 1;
        this.age = getPoisson(1.75); //lamda of 1.75 achieves an age range of around 0-80
        this.vaccinated = false;
        this.wfr = 0.5; // Eventually add some randomness here
        this.vaccination_effectivity = 0.5; // Figure out how to pull this from SimSettings
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
     * @returns age of person
     */
    public double getAge(){
        return this.age;
    }


    /**
     * A really rough approximation of the gamma function
     * @param z An double
     * @return The gamma(z)
     */
    private double gamma(double z) {
        int upperLim = 100;
        int subDiv = 10;
        double result = 0;
        for (int k = 1; k < upperLim * subDiv; k++) {
            result += Math.pow(Math.E, -((double)k/subDiv)) * Math.pow((double)k/subDiv, z - 1);
        }
        return result;
    }

    /**
     * The age multiplier for this person's age. Should look somewhat like a negative poisson distribution
     * @param age The age of the individual
     * @return The multiplication factor of reception
     */
    private double getAgeMultiplier(int age) {
        double lambda = 3;
        double baseRate = Math.pow(lambda, age)/(gamma((double) 80/10.0 + 1.0)) * Math.pow(Math.E, -lambda);
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
