import java.util.List;

/**
 * SingleLink Class implements Distance<List<Point>>.
 */
public class SingleLink implements Distance<List<Point>> {
    /**
     * distanceFrom(List<Point> src, List<Point> dst).
     *
     * @param src List<Point> -- a source list to calculate distanceFrom from
     * @param dst List<Point> -- a destination list to calculate distanceFrom to
     * @return the minimal distanceFrom between src list to dst list
     */
    @Override
    public Double calcDistance(List<Point> src, List<Point> dst) {
        // Local Variables
        Double shortestDistance = Double.MAX_VALUE;
        Double currentDistance;

        // for each point in source
        for (Point p1:src) {
            // for each point in destination
            for (Point p2:dst) {
                // calc distance
                currentDistance = p1.distanceFrom(p2);

                // current < shortest -> update
                if(currentDistance < shortestDistance ){
                    shortestDistance = currentDistance;
                }

            }
        }

        return shortestDistance;
    }
}
