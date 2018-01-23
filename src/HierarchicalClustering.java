import java.util.ArrayList;
import java.util.List;

/**
 * HierarchicalClustering Class.
 */
public class HierarchicalClustering {
    //---------- LOCAL CLASS VARIABLES ----------
    private int maxClusters;
    private int clusteringIndicator;
    private List<Point> points;
    private List<List<Point>> clusters;
    private Distance<List<Point>> distances;

    //---------- INITIALIZATION ----------

    /**
     * HierarchicalClustering().
     */
    public HierarchicalClustering() {
        points = new ArrayList<>();
        clusters = new ArrayList<>();
        distances = null;
        maxClusters = 1;
    }

    /**
     * HierarchicalClustering(ParseResult parseResult).
     *
     * @param parseResult ParseResult -- a ParseResult created by FileParser from a given file.
     */
    public HierarchicalClustering(ParseResult parseResult) {
        points = parseResult.getPoints();
        distances = parseResult.getDist();
        clusters = parseResult.getClusters();
        maxClusters = parseResult.getMaxClusters();
    }

    /**
     * cluster().
     */
    public void cluster() {
        Double maxDist, currentDist;
        int i, j;

        // cluster until we have reached the max number of clusters
        while (maxClusters < clusters.size()) {
            maxDist = Double.MAX_VALUE;
            int sourceIndex = -1;
            int destIndex = -1;
            int numOfClusters = clusters.size();

            // for each cluster
            for (i = 0; i < numOfClusters - 1; i++) {
                // check all other clusters
                for (j = i + 1; j < numOfClusters; j++) {
                    // calculate current distanceFrom
                    currentDist = distances.calcDistance(clusters.get(i), clusters.get(j));

                    // case found new minimal distanceFrom -> update distanceFrom and source indexes
                    if (currentDist < maxDist) {
                        maxDist = currentDist;
                        sourceIndex = i;
                        destIndex = j;
                    }
                }

            }

            // a destination was found -> update clusters and remove cluster checked since it was just updated
            if (destIndex >= 0) {
                clusters.get(sourceIndex).addAll(clusters.get(destIndex));
                clusters.remove(destIndex);
            }

            // update the number of clusters
            clusteringIndicator = clusters.size();
        }
    }


    /**
     * toString().
     *
     * @return a String of points representing the closest Points by order.
     */
    @Override
    public String toString() {
        //Local Variables
        StringBuilder stringBuilder = new StringBuilder();
        for (Point p : points) {
            for (int i = 0; i < clusteringIndicator; i++) {
                if (clusters.get(i).contains(p)) {
                    stringBuilder.append(i + 1).append("\n");
                    break;
                }

            }
        }

        return stringBuilder.toString();
    }

}
