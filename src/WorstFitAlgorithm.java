import java.util.List;


/**
 * Uses worst-fit decreasing algorithm to fit disks.
 *
 * @author rcd
 */
public class WorstFitAlgorithm extends Algorithm {
    public static final String DESCRIPTION = "worst-fit";

    /**
     * Default constructor
     */
    public WorstFitAlgorithm () {
        super(DESCRIPTION);
    }

    /**
     * @see Algorithm#organizeData(List)
     */
    @Override
    protected void organizeData (List<Integer> data) {
        // do nothing
    }
}
