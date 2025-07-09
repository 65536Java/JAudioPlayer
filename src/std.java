import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class std {
    private static File ou = new File("Log.log");
    private static FileWriter fou;

    static {
        try {
            fou = new FileWriter(ou);
        } catch (IOException e) {

        }
    }

    public static PrintWriter out = new PrintWriter(fou);

    public std() throws IOException {
    }
}
