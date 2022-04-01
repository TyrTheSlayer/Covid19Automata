package Simulator;

public class DataOut {

    private class Array {
        private int arr[];
        private int size = 0;

        public Array(int len) { arr = new int[len]; }

        public void print() {
            for (int i = 0; i < size; i++)
                System.out.println(arr[i] + " ");
        }

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
    }
    private Array infected;
    private Array susceptible;
    private Array dead;
    private Array recovered;
    private Array vaccinated;

    public DataOut() {
        susceptible = new Array(1);
        infected = new Array(1);
        recovered = new Array(1);
        dead = new Array(1);
        vaccinated = new Array(1);
    }

    public DataOut(int i) {
        susceptible = new Array(i);
        infected = new Array(i);
        recovered = new Array(i);
        dead = new Array(i);
        vaccinated = new Array(i);
    }

    public void addRecord(int s, int i, int r, int d, int v) {
        susceptible.add(s);
        infected.add(i);
        recovered.add(r);
        dead.add(d);
        vaccinated.add(v);
    }

    public void writeOut() {
        // insert code to write to YAML
    }

}
