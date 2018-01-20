import java.util.Map;

/**
 * Point Class.
 */
public class Point {
    //---------- LOCAL CLASS VARIABLES ----------
    private int x;
    private int y;

    //---------- INITIALIZER ----------

    /**
     * Point(int x, int y).
     *
     * @param x int -- a x.
     * @param y int -- a y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //---------- GETTERS ----------

    /**
     * getX().
     *
     * @return the x for this position.
     */
    public int getX() {
        return this.x;
    }

    /**
     * getY().
     *
     * @return the y for this position.
     */
    public int getY() {
        return this.y;
    }

    //---------- UTILITY FUNCTIONS ----------
    /**
     * toString().
     */
    @Override
    public String toString() {
        return x + "," + y;
    }

    /**
     * hashCode().
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * equals(Object obj).
     *
     * @param obj Object -- another object.
     * @return true of objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point)obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * distanceFrom(Point other).
     *
     * @param other Point -- the point to check distanceFrom from
     * @return the distanceFrom between both points
     */
    public double distanceFrom(Point other) {
        int x2 = other.getX();
        int y2 = other.getY();

        return (Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2)));
    }
}