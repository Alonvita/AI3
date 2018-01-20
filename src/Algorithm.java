/**
 * Algorithm Class.
 */
public enum Algorithm {
    //---------- ENUM DEFINITIONS ----------
    SINGLE_LINK("single link"),
    AVERAGE_LINK("average link");

    //---------- LOCAL CLASS VARIABLES ----------
    private String cell;

    //---------- INITIALIZER ----------

    /**
     * Algorithm(String cell).
     *
     * @param cell string -- a string representing this cell.
     */
    Algorithm(String cell) {
        this.cell = cell;
    }

    /**
     * toString().
     *
     * @return the string representing the cell type.
     */
    @Override
    public String toString() {
        return cell;
    }
}
