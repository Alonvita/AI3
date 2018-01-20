import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ParseFile Class.
 */
public class ParseFile {
    //---------- INITIALIZER ----------

    /**
     * ParseFile(String filename) throws Exception.
     *
     * @param filename String -- a string.
     * @return the parsed result for this solution.
     * @throws Exception -- illegal number of lines in file.
     */
    public static ParseResult ParseFile(String filename) throws Exception {
        // Local variables
        List<String> lines = Files.readAllLines(Paths.get(filename));

        if (lines.size() < 1) {
            throw new Exception("Illegal file format received");
        }

        // initialize a new Cells matrix
        ClusteringAlgorithm algorithm = parseAlgorithm(lines.get(0));
        int clusters = Integer.parseInt(lines.get(1));

        // Create the list of points
        List<Point> points = new ArrayList<>();

        for(String s : lines) {
            int x;
            int y;

            // x
            char c = s.charAt(0);
            x = c - '0';

            // y
            y = c - '0';

            points.add(new Point(x, y));
        }

        return new ParseResult(points, algorithm, clusters);
    }

    /**
     * ClusteringAlgorithm parseAlgorithm(String s).
     *
     * @param s String -- a string representing an algorithm name.
     * @return a new ClusteringAlgorithm represented by the given string.
     */
    private static ClusteringAlgorithm parseAlgorithm(String s) {
        if(s.equals("single link")) {
            return new SingleLink();
        }

        return new AverageLink();
    }
}