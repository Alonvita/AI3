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
     *                  a given point should be a part of this Cluster or not.
     * @param algorithm Algorithm -- Single Link or Average Link
     *                  <p>
     *                  Explanation:
     *                  indicator will hold the value defined by the algorithm given to know whether
     *                  a given point should be a part of this Cluster or not. Every point added to the cluster
     *                  will update the indicator.
     */
    public Cluster(double indicator, Algorithm algorithm) {
        this.indicator = indicator;
        this.algorithm = algorithm;
        this.pointList = new ArrayList<>();
    }

    /**
     * Cluster(double indicator, Algorithm algorithm, List<Point> points).
     *
     * @param indicator duble -- hold the value defined by the algorithm given to determine whether
     *                  a given point should be a part of this Cluster or not.
     * @param algorithm Algorithm -- Single Link or Average Link
     * @param points    List<Point> -- a given list of points
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

    //----------- PUBLIC FUNCTIONS----------

    /**
     * add(Point p).
     *
     * @param point Points -- a point to add
     */
    public void add(Point point) {
        // Local Variables
        double sum = 0;

        if (this.pointList.size() == 0) {
            this.pointList.add(point);
            return;
        }

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

        // add the point
        this.pointList.add(point);
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
            } else {
                // otherwise the algorithm is AVERAGE_LINK, assuming only 2 algorithm types exist in the program
                sum += distance;
            }
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

    /**
     * calcChangePotential(Point p).
     *
     * @param p Point -- a point
     * @return the distance between the point and the indicator.
     */
    public double calcChangePotential(Point p) {
        // Local Variable
        double sum = 0;
        double minChange = Double.MAX_VALUE;

        for (Point point : pointList) {
            // get the distance between two points
            double distance = point.distanceFrom(p);

            if (algorithm == Algorithm.SINGLE_LINK) {
                if (distance < minChange) {
                    minChange = distance;
                }
            } else {
                sum += distance;
            }
        }

        if (sum != 0) {
            // calc the change in indicator for adding this point
            minChange = indicator - (sum / (this.pointList.size() + 1));
        }

        return minChange;
    }
}