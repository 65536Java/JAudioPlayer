import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class std {
    File ou = new File("Log");
    FileWriter fou = new FileWriter(ou);
    public static PrintWriter out;

    public std() throws IOException {
    }
}
