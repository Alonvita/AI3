import java.util.List;

/**
 * ParseResult Class.
 */
public class ParseResult {
    //---------- LOCAL CLASS VARIABLES ----------
    private int clusters;
    private List<Point> points;
    private Algorithm algorithm;

    //---------- INITIALIZER ----------
    public ParseResult(List<Point> points, Algorithm algorithm, int clusters) {
        this.points = points;
        this.algorithm = algorithm;
        this.clusters = clusters;
    }

    //---------- GETTERS ----------

    /**
     * ClusteringAlgorithm getAlgorithm().
     *
     * @return the algorithm this ParseResult holds.
     */
    public Algorithm getAlgorithm() {
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
