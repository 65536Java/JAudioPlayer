import enums.Languages;
import tools.Lang;

import java.io.IOException;

public class Main {
    public static Lang lang = new Lang();
    public static Languages language = Languages.EN;
    public static std std;

    static {
        try {
            std = new std();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Win();
    }
}