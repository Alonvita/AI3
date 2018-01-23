import java.util.List;

/**
 * AverageLink implements Distance<List<Point>>.
 */
public class AverageLink implements Distance<List<Point>> {

    /**
     * distanceFrom(List<Point> src, List<Point> dst).
     *
     * @param src List<Point> -- a source list to calculate distanceFrom from
     * @param dst List<Point> -- a destination list to calculate distanceFrom to
     * @return the average distanceFrom between the src list to dst list
     */
    @Override
    public Double calcDistance(List<Point> src, List<Point> dst) {
        // Local Variables
        Double sum = 0.0;
        int numberOfPointsChecked = 0;

        // for each point in source
        for (Point p1 : src) {
            // for each point in dest
            for (Point p2 : dst) {
                // add to sum and update points checked for AVG calculation
                numberOfPointsChecked += 1;
                sum += p1.distanceFrom(p2);
            }
        }

        return sum / numberOfPointsChecked;
    }
}
