import java.util.List;

public class ParseResult {
    //---------- LOCAL CLASS VARIABLES ----------
    private List<Point> points;
    private List<List<Point>> clusters;
    private Distance<List<Point>> dist;
    private int maxClusters;
    private int numberOfClusters;

    /**
     * ParseResult(List<Point> points, List<List<Point>> clusters, Distance<List<Point>> dist, int maxClusters).
     *
     * @param points List<Point> -- a list of points
     * @param clusters List<List<Point>> -- a list of clusters
     * @param dist Distance<List<Point>> -- the list of distances defined by algorithm
     * @param maxClusters int -- the max number of clusters
     * @param numberOfClusters int -- a given number of clusters
     */
    public ParseResult(List<Point> points, List<List<Point>> clusters,
                       Distance<List<Point>> dist, int maxClusters, int numberOfClusters) {
        this.points = points;
        this.clusters = clusters;
        this.dist = dist;
        this.maxClusters = maxClusters;
        this.numberOfClusters = numberOfClusters;
    }

    /**
     * getPoints().
     *
     * @return the list of points in the ParseResult.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * getDist().
     *
     * @return the list of distanced in the PraseResult.
     */
    public Distance<List<Point>> getDist() {
        return dist;
    }

    /**
     * getMaxClusters().
     *
     * @return the max number of clusters held by the ParseResult.
     */
    public int getMaxClusters() {
        return maxClusters;
    }

    /**
     * getClusters().
     *
     * @return the list of clusters held by the ParseResult.
     */
    public List<List<Point>> getClusters() {
        return clusters;
    }

    /**
     * getNumberOfClusters().
     *
     * @return the number of clusters.
     */
    public int getNumberOfClusters() {
        return numberOfClusters;
    }
}
