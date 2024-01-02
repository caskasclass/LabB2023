package util;

import java.io.IOException;

public class StyleManager {

    public String loadStyle(String cssFile) throws IOException {
        return (getClass().getResource("../css/" + cssFile).toExternalForm());

    }
}
