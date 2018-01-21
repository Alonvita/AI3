import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * HierarchicalClustering Class.
 */
public class HierarchicalClustering {
    private int maxClusters;
    private Algorithm algorithm;
    private List<Point> listPoint;
    private List<Pair<Cluster, Double>> clusters;
    private List<List<Double>> sortedListOfListsByDistances;

    /**
     * HierarchicalClustering(List<Point> pointList).
     *
     * @param pointList List<Point> -- a list of points
     */
    public HierarchicalClustering(List<Point> pointList, Algorithm algorithm, int maxClusters) {
        this.algorithm = algorithm;
        this.maxClusters = maxClusters;
        this.clusters = new ArrayList<>();
        this.listPoint = pointList;

        sortPointsListByDistances(pointList);
    }

    /**
     * cluster().
     *
     * @return a List<Pair<Point, Integer>> of points with the cluster their belong to.
     */
    public List<Pair<Point, Integer>> cluster() {
        // Local Variables
        List<Pair<Point, Integer>> solution = new ArrayList<>(); // will be used as return value

        // get the min of mins
        Pair<Integer, Integer> minOfMins = getMinimalDistanceIndex(this.sortedListOfListsByDistances);

        

        return solution;
    }

    /**
     * sortPointsListByDistances().
     */
    private void sortPointsListByDistances(List<Point> pointList) {
        // Local Variables
        List<List<Double>> distancesAsListOfLists = new ArrayList<>();
        int startFrom = 1;

        for (Point p1 : pointList) {
            // create a new list for this point
            List<Double> distancesForCurrent = new ArrayList<>();

            // start second loop from the index after current do avoid double values
            for (Point p2 : pointList.subList(startFrom, pointList.size())) {
                // add distance to List for current p1
                distancesForCurrent.add(p1.distanceFrom(p2));
            }

            // add current distances to the List of Lists
            distancesAsListOfLists.add(distancesForCurrent);

            // reinitialize minDistance and advance startFrom
            startFrom++;
        }

        // sort the list of lists
        for(List<Double> list : distancesAsListOfLists) {
            sortListOfDoubles(list);
        }

        // update local variable with the sorted list of lists
        this.sortedListOfListsByDistances = distancesAsListOfLists;
    }

    /**
     * sortListOfDoubles(List<List<Double>> listOfLists).
     *
     * @param list List<Double> -- a list of Doubles
     */
    private void sortListOfDoubles(List<Double> list) {
        // got the list of lists of distances, now sort it
        list.sort(Comparator.comparing(Double::new));
    }

    /**
     * getMinimalDistanceIndex(List<List<Double>> listOfLists).
     *
     * @param listOfLists List<List<Double>> -- a list of lists of Doubles.
     * @return the minimal distance in the list of lists as Pair<Integer, Integer> representing two points
     *          in this.pointsList at indexes x and y; or the distance between them from the list of lists.
     * <p>
     * assuming given a List of List of Doubles that is already sorted.
     */
    private Pair<Integer, Integer> getMinimalDistanceIndex(List<List<Double>> listOfLists) {
        // Local Variables
        Integer minX = null;
        Integer minY = null;
        Double minOfMins = Double.MAX_VALUE;
        Integer i = 0;
        Integer j = 0;

        for (List<Double> list : listOfLists) {
            // inner loop variables
            Double currentMin = Double.MAX_VALUE;
            Integer currentMinX = null;
            Integer currentMinY = null;

            for (Double d : list) {
                if (d < currentMin) {
                    // smaller or equal -> update min and get index
                    currentMin = d;
                    currentMinX = i;
                    currentMinY = j;
                }
                ++j;
            }

            if (currentMin < minOfMins) {
                minOfMins = currentMin;
                minX = currentMinX;
                minY = currentMinY;
            }

            ++i;
            j = 0;
        }

        // return the minimal of minimals index
        return new Pair<>(minX, minY);
    }
}
