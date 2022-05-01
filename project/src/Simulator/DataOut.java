package Simulator;

import DataObjects.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public int recordLength() {
        return this.susceptible.size;
    }

    /**
     * Writes the data to a csv file for postsim processing
     */
    public void writeOut(ArrayList<Person> people){
        String CSV = "susceptible,infected,recovered,dead,vaccinated\r\n";

        peopleCSV(people);

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

    /**
     * Builds the CSV for people attributes
     *
     * @param people array list of people the attributes we want recorded
     */
    public void peopleCSV(ArrayList<Person> people){
        //declare bins for people
        Person[] bin010 = new Person[people.size() + 1];
        Person[] bin1120 = new Person[people.size() + 1];
        Person[] bin2130 = new Person[people.size() + 1];
        Person[] bin3140 = new Person[people.size() + 1];
        Person[] bin4150 = new Person[people.size() + 1];
        Person[] bin5160 = new Person[people.size() + 1];
        Person[] bin6170 = new Person[people.size() + 1];
        Person[] bin7180 = new Person[people.size() + 1];
        Person[] bin8190 = new Person[people.size() + 1];
        Person[] bin91100 = new Person[people.size() + 1];
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0, i7 = 0, i8 = 0, i9 = 0, i10 = 0;

        //Go through the people list and bin them on age
        for(Person person : people){
            if(person.getFactors().getAge() <= 10.0){
                bin010[i1] = person;
                i1++;
            }else if(person.getFactors().getAge() > 10.0 && person.getFactors().getAge() <= 20.0){
                bin1120[i2] = person;
                i2++;
            }else if(person.getFactors().getAge() > 20.0 && person.getFactors().getAge() <= 30.0){
                bin2130[i3] = person;
                i3++;
            }else if(person.getFactors().getAge() > 30.0 && person.getFactors().getAge() <= 40.0){
                bin3140[i4] = person;
                i4++;
            }else if(person.getFactors().getAge() > 40.0 && person.getFactors().getAge() <= 50.0){
                bin4150[i5] = person;
                i5++;
            }else if(person.getFactors().getAge() > 50.0 && person.getFactors().getAge() <= 60.0){
                bin5160[i6] = person;
                i6++;
            }else if(person.getFactors().getAge() > 60.0 && person.getFactors().getAge() <= 70.0){
                bin6170[i7] = person;
                i7++;
            }else if(person.getFactors().getAge() > 70.0 && person.getFactors().getAge() <= 80.0){
                bin7180[i8] = person;
                i8++;
            }else if(person.getFactors().getAge() > 80.0 && person.getFactors().getAge() <= 90.0){
                bin8190[i9] = person;
                i9++;
            }else if(person.getFactors().getAge() > 90.0 && person.getFactors().getAge() <= 100.0){
                bin91100[i10] = person;
                i10++;
            }
        }
        //Build CSV
        String CSV = "";
        if(bin010[0] != null){
            CSV = CSV + "---010\r\nage,status,vaccinated\r\n";
            CSV = writePeople(bin010, CSV);
        }
        if(bin1120[0] != null){
            CSV = CSV + "---1120\r\nage,status,vaccinated\n";
            CSV = writePeople(bin1120, CSV);
        }
        if(bin2130[0] != null){
            CSV = CSV + "---2130\r\nage,status,vaccinated\n";
            CSV = writePeople(bin2130, CSV);
        }
        if(bin3140[0] != null){
            CSV = CSV + "---3140\r\nage,status,vaccinated\n";
            CSV = writePeople(bin3140, CSV);
        }
        if(bin4150[0] != null){
            CSV = CSV + "---4150\r\nage,status,vaccinated\n";
            CSV = writePeople(bin3140, CSV);
        }
        if(bin5160[0] != null){
            CSV = CSV + "---5160\r\nage,status,vaccinated\n";
            CSV = writePeople(bin5160, CSV);
        }
        if(bin6170[0] != null){
            CSV = CSV + "---6170\r\nage,status,vaccinated\n";
            CSV = writePeople(bin6170, CSV);
        }
        if(bin7180[0] != null){
            CSV = CSV + "---7180\r\nage,status,vaccinated\n";
            CSV = writePeople(bin7180, CSV);
        }
        if(bin8190[0] != null){
            CSV = CSV + "---8190\r\nage,status,vaccinated\n";
            CSV = writePeople(bin8190, CSV);
        }
        if(bin91100[0] != null){
            CSV = CSV + "---91100\r\nage,status,vaccinated\n";
            CSV = writePeople(bin91100, CSV);
        }

        try { // Try to write
            String pathname = "./postsim/people.csv";
            File outFile = new File(pathname);
            outFile.createNewFile();
            FileWriter writer = new FileWriter(pathname);
            writer.write(CSV);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Writes all info for each bin
     *
     * @param person person array bin being collected for printing
     * @param CSV string that will become the csv
     * @return csv, the main csv plus additional info
     */
    public String writePeople(Person[] person, String CSV){
        int i;
        for(i = 0; person[i] != null; i++){
            CSV = CSV + person[i].getFactors().getAge() + "," + person[i].getStatus().toString() + "," + person[i].getFactors().isVaccinated() + "\r\n";
        }
        return CSV;
    }

}
