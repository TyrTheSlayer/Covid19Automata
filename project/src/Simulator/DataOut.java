package Simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataOut {

    /**
     * An inner class for dynamically sized arrays, for data collection/storage
     */
    private class Array {
        private int arr[];
        private int size = 0;

        /**
         * Inits an array of a given length
         * @param len The length of the array
         */
        public Array(int len) { arr = new int[len]; }

        /**
         * A print method, to print the array out for debugging
         */
        public void print() {
            for (int i = 0; i < size; i++)
                System.out.println(arr[i] + " ");
        }

        /**
         * A method to dynamically add elements to the array
         * @param a The value to add at the end of the array
         */
        public void add(int a) {
            if (arr.length == size) {
                int newArr[] = new int[2 * size];
                for (int i = 0; i < size; i++) {
                    newArr[i] = arr[i];
                }
                arr = newArr;
            }
            arr[size++] = a;
        }

        /**
         * A method to get a value at some index
         * @param i The index to read
         * @return The val at a[i]
         */
        public int get(int i) {
            if (i >= 0 && i < size) return arr[i];
            return -1;
        }
    }
    private Array infected;
    private Array susceptible;
    private Array dead;
    private Array recovered;
    private Array vaccinated;

    /**
     * Default constructor, inits all data to be arrays of len 1
     */
    public DataOut() {
        susceptible = new Array(1);
        infected = new Array(1);
        recovered = new Array(1);
        dead = new Array(1);
        vaccinated = new Array(1);
    }

    /**
     * Constructor to init data arrays to have length i
     * @param i The length of all data arrays
     */
    public DataOut(int i) {
        susceptible = new Array(i);
        infected = new Array(i);
        recovered = new Array(i);
        dead = new Array(i);
        vaccinated = new Array(i);
    }

    /**
     * A method to add records for susceptible, infected, recovered, dead, and vaxxed individuals
     * @param s Susceptible count
     * @param i Infected count
     * @param r Recovered count
     * @param d Dead count
     * @param v Vaccinated count
     */
    public void addRecord(int s, int i, int r, int d, int v) {
        susceptible.add(s);
        infected.add(i);
        recovered.add(r);
        dead.add(d);
        vaccinated.add(v);
    }

    /**
     * Writes the data to a csv file for postsim processing
     */
    public void writeOut() {
        String CSV = "susceptible,infected,recovered,dead,vaccinated\r\n";

        for(int i = 0; i < susceptible.size; i++) { // Iterate over string and append
            CSV += susceptible.get(i) + ",";
            CSV += infected.get(i) + ",";
            CSV += recovered.get(i) + ",";
            CSV += dead.get(i) + ",";
            CSV += vaccinated.get(i) + "\r\n";
        }
        try { // Try to write
            String pathname = "./postsim/simulation.csv";
            File outFile = new File(pathname);
            outFile.createNewFile();
            FileWriter writer = new FileWriter(pathname);
            writer.write(CSV);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
