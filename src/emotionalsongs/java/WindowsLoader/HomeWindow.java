package WindowsLoader;

import Session.WindowAppearance;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.FXMLLoaders;

public class HomeWindow extends Stage {

    private static final double MIN_WINDOW_WIDTH = 800.0;
    private static final double MIN_WINDOW_HEIGHT = 600.0;

    FXMLLoaders loader = new FXMLLoaders();
    
    public HomeWindow(){
            BorderPane mainContaier = (BorderPane) loader.loadFXML("HomeWindow.fxml");
            //mainContaier.getStylesheets().add(styleManager.loadStyle("main.css"));
            Scene scene = new Scene(mainContaier, WindowAppearance.getWindowWidth(), WindowAppearance.getWindowHeight());
            setScene(scene);
            setTitle("EmotionalSongs");
            setMinHeight(MIN_WINDOW_HEIGHT);
            setMinWidth(MIN_WINDOW_WIDTH);
            setX( WindowAppearance.getWindowX());
            setY(WindowAppearance.getWindowY());
    }
}
