import java.util.Scanner;

import static java.lang.System.exit;

/**
 * java_ex3 Class -- Main.
 */
public class java_ex3 {
    public static void main(String[] args) throws Exception{
        // Local main variables
        Scanner fp = new Scanner(System.in);
        System.out.println("Please enter input.txt file path:");
        String path = fp.next();
        ParseResult parseResult;

        try {
            parseResult = ParseInputFile.ParseFile(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting...");
            exit(-1);
        }


    }
}
