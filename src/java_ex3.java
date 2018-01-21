import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.System.exit;

/**
 * java_ex3 Class -- Main.
 */
public class java_ex3 {
    public static void main(String[] args) {
        // Local main variables
        String path = "input.txt";
        ParseResult parseResult;

        try {
            parseResult = ParseInputFile.ParseFile(path);
            HierarchicalClustering hierarchicalClustering =
                    new HierarchicalClustering(
                            parseResult.getPoints(),
                            parseResult.getAlgorithm(),
                            parseResult.getClusters()
                    );
            List<Integer> clusteredList = hierarchicalClustering.cluster();

            StringBuilder stringBuilder = new StringBuilder();
            for(Integer i : clusteredList) {
                stringBuilder.append(i);
                stringBuilder.append('\n');

                Files.write(Paths.get("output.txt"), stringBuilder.toString().getBytes());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting...");
            exit(-1);
        }
    }
}
