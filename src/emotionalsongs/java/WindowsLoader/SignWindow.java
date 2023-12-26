package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

import controllers.homeWindowController;
import controllers.signWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import util.FXMLLoaders;
import util.StyleManager;

public class SignWindow extends Stage {

    FXMLLoaders fxmlutil = new FXMLLoaders();
    StyleManager styleManager = new StyleManager();

    public SignWindow(homeWindowController ref) {
        FXMLLoader loader = fxmlutil.getLoader("signWindow.fxml");
        signWindowController controller = new signWindowController(ref);
        loader.setController(controller);
        try {
            Pane mainContaier = (Pane) loader.load();
            initStyle(StageStyle.UNDECORATED);
            initStyle(StageStyle.TRANSPARENT);
            setResizable(false);
            setScene(new Scene(mainContaier, Color.TRANSPARENT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOnShown(event -> {
            show();
        });

    }

}
