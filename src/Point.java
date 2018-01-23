/**
 * class of positions
 */
public class Point {
    //---------- LOCAL VARIABLES ----------
    private double row;
    private double col;

    /**
     * Point(String str).
     *
     * @param str String -- a string. Assuming x,y (x and y as doubles)
     */
    public Point(String str) {
        if (str != null) {
            String[] point = str.split(",");
            if (point.length == 2) {
                row = Double.parseDouble(point[0]);
                col = Double.parseDouble(point[1]);
                return;
            }
        }
        throw new NumberFormatException();
    }


    /**
     * Point(double row, double col).
     *
     * @param row double -- row
     * @param col double -- col
     */
    public Point(double row, double col) {
        this.row = row;
        this.col = col;
    }

    /**
     * getRow().
     *
     * @return this Point's row.
     */
    public double getRow() {
        return row;
    }

    /**
     * getCol().
     *
     * @return this row's col
     */
    public double getCol() {
        return col;
    }


    /**
     * distanceFrom(Point other).
     *
     * @param other Point -- point to calculate distanceFrom from.
     * @return the distanceFrom between this and other as Double.
     */
    public Double distanceFrom(Point other) {
        // Local Variables
        Double x = Math.pow(this.row - other.getRow(), 2);
        Double y = Math.pow(this.col - other.getCol(), 2);

        return Math.sqrt(x + y);
    }

    /**
     * equals(Object obj).
     *
     * @param other Object -- Object to check equality to.
     * @return true if the objects are equal, or false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        try {
            if (other instanceof Point)
                return equals((Point) other);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * equals(Point other).
     *
     * @param other Point -- Point to check equality to.
     * @return true if the objects are equal, or false otherwise.
     */
    public boolean equals(Point other) {
        return other != null && other.row == this.row && other.col == this.col;
    }

    /**
     * toString().
     *
     * @return a string representation of this Point.
     */
    @Override
    public String toString() {
        return Double.toString(this.row) + ',' + Double.toString(this.col);
    }
}