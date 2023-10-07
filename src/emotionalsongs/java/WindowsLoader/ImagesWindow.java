package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

import controllers.imagesWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import util.FXMLLoaders;
import util.StyleManager;

public class ImagesWindow extends Stage {
    FXMLLoaders fxmlutil = new FXMLLoaders();
    StyleManager styleManager = new StyleManager();

    public ImagesWindow(ImageView img) {
        FXMLLoader loader = fxmlutil.getLoader("imagesWindow.fxml");
        imagesWindowController controller = new imagesWindowController(img);
        loader.setController(controller);
        try {

            Pane root = (Pane) loader.load();
            initStyle(StageStyle.UNDECORATED);
            initStyle(StageStyle.TRANSPARENT);
            setResizable(false);
            setScene(new Scene(root, Color.TRANSPARENT));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOnShown(event -> {

            show();
        });

    }
}
