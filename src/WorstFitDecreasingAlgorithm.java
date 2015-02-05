import java.util.Collections;
import java.util.List;


/**
 * Uses worst-fit decreasing algorithm to fit disks.
 *
 * @author rcd
 */
public class WorstFitDecreasingAlgorithm extends Algorithm {
    public static final String DESCRIPTION = "worst-fit decreasing";

    /**
     * Default constructor
     */
    public WorstFitDecreasingAlgorithm () {
        super(DESCRIPTION);
    }

    /**
     * @see Algorithm#organizeData(List)
     */
    @Override
    protected void organizeData (List<Integer> data) {
        Collections.sort(data, Collections.reverseOrder());
    }
}
