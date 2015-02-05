import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Runs a number of algorithms that try to fit files onto disks.
 */
public class Bins {
    // all possible algorithms to compare --- add new instances here!
    private static Algorithm algortihmsToCompare[] = { 
        new WorstFitAlgorithm(),
        new WorstFitDecreasingAlgorithm()
    };


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

    /**
     * The main program.
     */
    public static void main (String args[]) {
        Bins b = new Bins();
        try {
            List<Integer> data = b.readData(new Scanner(new File(args[0])));
            System.out.println("total size = " + b.getTotal(data) / 1000000.0 + "GB");
            for (Algorithm al : algortihmsToCompare) {
                al.fitDisksAndPrint(data);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Could not open file: " + args[0]);
        }
    }
}
