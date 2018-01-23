import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileParser Class.
 */
public class FileParser {
    /**
     * parseFile().
     *
     * @return a ParseResult holding the variables parsed from the file.
     */
    public ParseResult parseFile() {
        // Local Variables
        int maxClusters;
        Distance<List<Point>> distances;
        List<Point> points = new ArrayList<>();
        List<List<Point>> clusters = new ArrayList<>();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader("input.txt"))) {
            // parse the algorithm (assuming first line)
            if ((distances = parseAlgorithm(bufferReader.readLine())) != null) {
                String line;

                if ((line = bufferReader.readLine()) != null) {
                    try {
                        // parse the max number of clusters (assumes 2nd line)
                        if ((maxClusters = Integer.parseInt(line)) <= 0) {
                            System.out.println("Error negative number");
                            return null;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error cant read number");
                        return null;
                    }
                    int i;

                    // parse the list of points given
                    for (i = 0; (line = bufferReader.readLine()) != null; ++i) {
                        try {
                            Point p = new Point(line);
                            points.add(p);
                            clusters.add(new ArrayList<>(Arrays.asList(p)));
                        } catch (NumberFormatException e) {
                            System.out.println("Error cant read pos");
                            return null;
                        }
                    }

                    if (i > 0) {
                        return new ParseResult(points, clusters, distances, maxClusters, i);
                    }
                }

            }
            return null;
        } catch (java.io.IOException e) {
            System.out.println("Error cant read file.");
            return null;

        }
    }

    /**
     * parseAlgorithm(String s).
     *
     * @param str String -- a string representing an algorithm name;
     * @return a new object of the algorithm, implementing a Distance<List<Point>>
     */
    private static Distance<List<Point>> parseAlgorithm(String str) {
        if (str != null) {
            if (str.equals("single link"))
                return new SingleLink();
            if (str.equals("average link"))
                return new AverageLink();
        }
        return null;
    }

}
