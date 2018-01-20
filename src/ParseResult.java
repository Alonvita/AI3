import java.util.List;

/**
 * ParseResult Class.
 */
public class ParseResult {
    //---------- LOCAL CLASS VARIABLES ----------
    private int clusters;
    private List<Point> points;
    private ClusteringAlgorithm algorithm;

    //---------- INITIALIZER ----------
    public ParseResult(List<Point> points, ClusteringAlgorithm algorithm, int clusters) {
        this.points = points;
        this.algorithm = algorithm;
        this.clusters = clusters;
    }
add
    //---------- GETTERS ----------

    /**
     * ClusteringAlgorithm getAlgorithm().
     *
     * @return the algorithm this ParseResult holds.
     */
    public ClusteringAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * getClusters().
     *
     * @return the number of clusters required.
     */
    public int getClusters() {
        return clusters;
    }

    /**
     * getPoints().
     *
     * @return the list of points this ParseResult holds as List<Point>.
     */
    public List<Point> getPoints() {
        return points;
    }
}
