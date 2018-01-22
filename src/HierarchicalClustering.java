import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * HierarchicalClustering Class.
 */
public class HierarchicalClustering {
    private int maxClusters;
    private int currentClusters;
    private Algorithm algorithm;
    private List<Point> listPoint;
    private List<Point> copiedList;
    private List<Pair<Cluster, Double>> clusters;
    private List<Pair<Point, Integer>> solution;


    /**
     * HierarchicalClustering(List<Point> pointList).
     *
     * @param pointList List<Point> -- a list of points
     */
    public HierarchicalClustering(List<Point> pointList, Algorithm algorithm, int maxClusters) {
        currentClusters = 0;
        this.algorithm = algorithm;
        this.maxClusters = maxClusters;
        this.clusters = new ArrayList<>();
        this.listPoint = pointList;
        this.solution = new ArrayList<>(); // will be used as return value

        copiedList = new ArrayList<>();
        copiedList.addAll(listPoint);
    }

    /**
     * cluster().
     *
     * @return a List<Pair<Point, Integer>> of points with the cluster their belong to.
     */
    public List<Integer> cluster() {

        while (!listPoint.isEmpty()) {
            Point current = listPoint.get(0);

            // initialize first cluster
            if (currentClusters == 0) {
                // create the first cluster
                initializeClusterFor(current);

                // remember solution for the point
                solution.add(new Pair<>(new Point(current), currentClusters));

            } else { // first cluster already exist
                // check if current point belongs to any of the clusters
                for (Pair<Cluster, Double> pair : this.clusters) {
                    Cluster c = pair.getKey();

                    // add to Cluster if possible
                    if (c.addIfBelongs(current)) {
                        solution.add(new Pair<>(new Point(current), clusters.indexOf(pair) + 1));
                        break;
                    } else if (this.currentClusters < this.maxClusters) { // space available for another cluster
                        initializeClusterFor(current);

                        // remember solution for the point
                        solution.add(new Pair<>(new Point(current), currentClusters));
                        break;
                    } else {
                        // space is full, add to the best cluster available
                        int addedTo = addToBestCluster(current);

                        solution.add(new Pair<>(new Point(current), addedTo));
                        break;
                    }
                }
            }
            // remove point from list
            listPoint.remove(current);
        }

        return solutionAsListOfIntegers();
    }

    /**
     * getClosestTo(Point p).
     *
     * @param p Point -- a point.
     * @return the index of closest point to this one from the listPoint
     */
    private int getIndexOfClosestTo(Point p) {
        // Local Variables
        Double closest = Double.MAX_VALUE;
        int closestIndex = 0;
        int i = 0;

        for (Point p1 : listPoint) {
            Double currentDistance = p.distanceFrom(p1);

            if (currentDistance != 0) {
                if (currentDistance < closest) {
                    closest = currentDistance;
                    closestIndex = i;
                }
            }

            ++i;
        }

        return closestIndex;
    }

    /**
     * initializeClusterFor(Point point).
     *
     * @param point Point -- a point.
     */
    private void initializeClusterFor(Point point) {
        // cluster added
        currentClusters++;

        // Local Variables
        int closest = getIndexOfClosestTo(point);
        Point closestP = listPoint.get(closest);
        Double closestPDistance = point.distanceFrom(closestP);

        Cluster cluster = new Cluster(closestPDistance, algorithm);

        Point newP = new Point(point);

        cluster.add(newP);
        cluster.add(closestP);

        // add the cluster to clusters list
        this.clusters.add(new Pair<>(cluster, cluster.getIndicator()));

        // add to solutions and remove point found from the ArrayList
        this.solution.add(new Pair<>(closestP, currentClusters));
        listPoint.remove(closestP);
    }

    /**
     * addToBestCluster(Point p).
     *
     * @param p Point -- a point.
     */
    private int addToBestCluster(Point p) {
        // Local Variables
        double minimalDistFromIndicator = Double.MAX_VALUE;
        int clusterIndex = 0;
        int i = 0;

        for (Pair<Cluster, Double> pair : this.clusters) {
            Cluster c = pair.getKey();

            double dist = c.calcChangePotential(p);

            if (dist < minimalDistFromIndicator) {
                minimalDistFromIndicator = dist;
                clusterIndex = i + 1;
            }
            ++i;
        }

        return clusterIndex;
    }

    /**
     * solutionAsListOfIntegers().
     *
     * @return the solution as List<Integer>.
     */
    private List<Integer> solutionAsListOfIntegers() {
        List<Integer> solutionAsListOfIntegers = new ArrayList<>();

        for (Point p : copiedList) {
            for (Pair<Point, Integer> pair : solution) {
                if (p.equals(pair.getKey())) {
                    solutionAsListOfIntegers.add(pair.getValue());
                    break;
                }
            }
        }

        return solutionAsListOfIntegers;
    }
}
