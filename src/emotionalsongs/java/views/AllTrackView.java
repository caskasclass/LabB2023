package views;

import java.io.IOException;
import java.util.ArrayList;

import controllers.allTrackViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pkg.Track;
import util.FXMLLoaders;

public class AllTrackView extends StackPane{
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public AllTrackView(ArrayList<Track> trackList) {

        FXMLLoader loader = fxmlutil.getLoader("AllTrackView.fxml");
        allTrackViewController controller = new allTrackViewController(trackList);

        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
