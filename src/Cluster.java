import java.util.ArrayList;
import java.util.List;

/**
 * Cluster Class.
 */
public class Cluster {
    //----------- LOCAL CLASS VARIABLES ----------
    double indicator;
    Algorithm algorithm;
    List<Point> pointList;

    //----------- INITIALIZER ----------

    /**
     * Cluster(double indicator, Algorithm algorithm).
     *
     * @param indicator duble -- hold the value defined by the algorithm given to determine whether
     *                            a given point should be a part of this Cluster or not.
     * @param algorithm Algorithm -- Single Link or Average Link
     *
     * Explanation:
     * indicator will hold the value defined by the algorithm given to know whether
     * a given point should be a part of this Cluster or not. Every point added to the cluster
     * will update the indicator.
     */
    public Cluster(double indicator, Algorithm algorithm, Point p) {
        this.indicator = indicator;
        this.algorithm = algorithm;
        this.pointList = new ArrayList<>();

        this.pointList.add(p);
    }

    /**
     * Cluster(double indicator, Algorithm algorithm, List<Point> points).
     *
     * @param indicator duble -- hold the value defined by the algorithm given to determine whether
     *                            a given point should be a part of this Cluster or not.
     * @param algorithm Algorithm -- Single Link or Average Link
     * @param points List<Point> -- a given list of points
     */
    public Cluster(double indicator, Algorithm algorithm, List<Point> points) {
        this.indicator = indicator;
        this.algorithm = algorithm;
        this.pointList = points;
    }

    //----------- GETTERS ----------

    /**
     * getPointList().
     *
     * @return the list of points inside this cluster as List<Point>.
     */
    public List<Point> getPointList() {
        return pointList;
    }

    /**
     * getIndicator().
     *
     * @return the indicator of this cluster.
     */
    public double getIndicator() {
        return indicator;
    }

    /**
     * combineClusters(Cluster other).
     *
     * @param other Cluster -- a cluster to combine with this one.
     */
    public Cluster combineClusters(Cluster other) {
        List<Point> combinedPoints = new ArrayList<>();
        double newIndicator;

        // add list points
        combinedPoints.addAll(this.pointList);
        combinedPoints.addAll(other.getPointList());

        // update indicator
        if (this.algorithm == Algorithm.SINGLE_LINK) {
            if(this.indicator < other.getIndicator()) {
                // update indicator
                newIndicator = other.getIndicator();

                return new Cluster(newIndicator, this.algorithm, combinedPoints);
            }
        }

        // otherwise, update indicator to be the average of both indicators.
        newIndicator = (this.indicator + other.getIndicator()) / 2;

        return new Cluster(newIndicator, this.algorithm, combinedPoints);
    }

    //----------- PUBLIC FUNCTIONS----------

    /**
     * add(Point p).
     *
     * @param point Points -- a point to add
     */
    public void add(Point point) {
        // Local Variables
        double sum = 0;

        // add the point
        this.pointList.add(point);

        // update indicator
        for (Point p : this.pointList) {
            // get the distance between two points
            double distance = point.distanceFrom(p);

            if (algorithm == Algorithm.SINGLE_LINK) {
                if (distance < indicator) {
                    indicator = distance;
                }
            }

            // otherwise the algorithm is AVERAGE_LINK, assuming only 2 algorithm types exist in the program
            sum += distance;
        }

        if (sum != 0) {
            double average = sum / pointList.size();
            indicator = indicator < average ? indicator : average;
        }
    }

    /**
     * addIfBelongs(Point point).
     *
     * @param point Point -- a point to add to this Cluster.
     * @return true if the point was added to the cluster, or false otherwise.
     */
    public boolean addIfBelongs(Point point) {
        // Local Variables
        double sum = 0;

        for (Point p : this.pointList) {
            // get the distance between two points
            double distance = point.distanceFrom(p);

            if (algorithm == Algorithm.SINGLE_LINK) {
                if (distance < indicator) {
                    indicator = distance;
                    this.pointList.add(point);
                    return true;
                }
            }

            // otherwise the algorithm is AVERAGE_LINK, assuming only 2 algorithm types exist in the program
            sum += distance;
        }

        if (sum != 0) {
            double average = sum / pointList.size();
            indicator = indicator < average ? indicator : average;
            this.pointList.add(point);
            return true;
        }

        // point was NOT added -- return false
        return false;
    }
}