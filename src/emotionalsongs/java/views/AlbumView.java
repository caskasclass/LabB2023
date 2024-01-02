package views;

import java.io.IOException;

import controllers.albumBoxController;
import jars.AlbumPreview;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import util.FXMLLoaders;

public class AlbumView extends HBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public static final int MAX_HEIGHT = 80;
    public static final int MIN_WIDTH = 270;

    public AlbumView(AlbumPreview albumPreview) {
        FXMLLoader loader = fxmlutil.getLoader("albumBox.fxml");
        albumBoxController controller = new albumBoxController(albumPreview);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
