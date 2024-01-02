package views;

import java.io.IOException;

import controllers.AllTrackViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

public class AllTrackView extends VBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public AllTrackView() {

        FXMLLoader loader = fxmlutil.getLoader("AllTrackView.fxml");
        AllTrackViewController controller = new AllTrackViewController();

        loader.setController(controller);

        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
