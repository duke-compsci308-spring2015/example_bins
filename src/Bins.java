import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Runs a number of algorithms that try to fit files onto disks.
 */
public class Bins {
    public static final String WORST_FIT = "worst-fit";
    public static final String WORST_FIT_DECREASING = "worst-fit decreasing";

    /**
     * Reads list of integer data from the given input.
     * 
     * @param input tied to an input source that contains space separated numbers
     * @return list of the numbers in the order they were read
     */
    public List<Integer> readData (Scanner input) {
        List<Integer> results = new ArrayList<>();
        while (input.hasNext()) {
            results.add(input.nextInt());
        }
        return results;
    }

    /**
     * Returns sum of all values in given list.
     */
    public int getTotal (Collection<Integer> data) {
        int total = 0;
        for (int d : data) {
            total += d;
        }
        return total;
    }

    // add files to the collection of Disks
    private Collection<Disk> addFiles (List<Integer> data) {
        PriorityQueue<Disk> pq = new PriorityQueue<>();
        pq.add(new Disk(0));
        int diskId = 1;
        for (Integer size : data) {
            Disk d = pq.peek();
            if (d != null && d.freeSpace() >= size) {
                pq.poll();
                d.add(size);
                pq.add(d);
            }
            else {
                Disk d2 = new Disk(diskId);
                diskId++;
                d2.add(size);
                pq.add(d2);
            }
        }
        return pq;
    }

    // print contents of given pq and a header, description
    private void printResults (String description, Collection<Disk> disks) {
        System.out.println();
        System.out.println("\n" + description + " method");
        System.out.println("number of disks used: " + disks.size());
        for (Disk d : disks) {
            System.out.println(d);
        }
    }

    // try a worst fit algorithm and also show the results
    private void runAlgorithm (List<Integer> data, String description) {
        List<Integer> copy = new ArrayList<>(data);
        if (description.equals(WORST_FIT_DECREASING)) {
            Collections.sort(copy, Collections.reverseOrder());
        }
        Collection<Disk> disks = addFiles(data);
        printResults(description, disks);
    }

    /**
     * The main program.
     */
    public static void main (String args[]) {
        Bins b = new Bins();
        try {
            Scanner input = new Scanner(new File(args[0]));
            List<Integer> data = b.readData(input);
            System.out.println("total size = " + b.getTotal(data) / 1000000.0 + "GB");
            b.runAlgorithm(data, WORST_FIT);
            b.runAlgorithm(data, WORST_FIT_DECREASING);
        }
        catch (FileNotFoundException e) {
            System.err.println("Could not open file: " + args[0]);
        }
    }
}
