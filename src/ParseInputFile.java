import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ParseInputFile Class.
 */
public class ParseInputFile {
    /**
     * ParseInputFile(String filename) throws Exception.
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

        // initialize the algorithm and clusters (two first rows of the file)
        Algorithm algorithm = parseAlgorithm(lines.get(0));
        int clusters = Integer.parseInt(lines.get(1));

        // remove two first rows since they were already used
        lines = lines.subList(2, lines.size());

        // Initialize the list of points
        List<Point> points = new ArrayList<>();

        // Parse the list of points
        for(String s : lines) {
            int x;
            int y;

            // x
            char c = s.charAt(0);
            x = c - '0';

            // y
            y = Integer.parseInt(s.substring(2));

            points.add(new Point(x, y));
        }

        return new ParseResult(points, algorithm, clusters);
    }

    /**
     * Algorithm parseAlgorithm(String s).
     *
     * @param s String -- a string representing an algorithm name.
     * @return an Enum representation of the Algorithm.
     */
    private static Algorithm parseAlgorithm(String s) {
        if(s.equals(Algorithm.SINGLE_LINK.toString())) {
            return Algorithm.SINGLE_LINK;
        }

        return Algorithm.AVERAGE_LINK;
    }
}