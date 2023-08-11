package views;

import java.io.IOException;

import controllers.albumBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import util.FXMLLoaders;

public class AlbumView extends HBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public static final int MAX_HEIGHT = 80;
    public static final int MAX_WIDTH = 270;

    public AlbumView(int n) {
        FXMLLoader loader = fxmlutil.getLoader("albumBox.fxml");
        albumBoxController controller = new albumBoxController();
        controller.imgnumber=n;
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


       

    }
}
