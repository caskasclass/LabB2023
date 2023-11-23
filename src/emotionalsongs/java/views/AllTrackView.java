package views;

import java.io.IOException;

import controllers.allTrackViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

public class AllTrackView extends VBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public AllTrackView() {

        FXMLLoader loader = fxmlutil.getLoader("AllTrackView.fxml");
        allTrackViewController controller = new allTrackViewController();

        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
