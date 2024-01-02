package Session;

import javafx.scene.control.ScrollPane;

public class Globals {
    private static ScrollPane rootFrame;

    public static ScrollPane getRootFrame(){
        return rootFrame;
    }

    public static void setRootFrame(ScrollPane scrollpane){
        rootFrame = scrollpane;
    }
}
