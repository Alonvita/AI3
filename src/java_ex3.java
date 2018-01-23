import java.io.*;

/**
 * java_ex3 Class -- Main.
 */
public class java_ex3 {
    /**
     * main(String[] args).
     */
    public static void main(String[] args) {
        // parse file and create the result
        FileParser fileParser = new FileParser();
        ParseResult parseResult = fileParser.parseFile();

        // create the HierarchicalClustering
        HierarchicalClustering hC = new HierarchicalClustering(parseResult);

        hC.cluster();
        String result = hC.toString();
        if (result == null) {
            System.out.println("ERROR");
            return;
        }

        // create a writer
        Writer writer;
        try {
            //write result
            writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream("output.txt"),
                                    "utf-8")
                    );

            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}