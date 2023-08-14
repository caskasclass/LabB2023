package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;


public class HomeView extends StackPane {
    
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public HomeView() {
        FXMLLoader loader = fxmlutil.getLoader("homeView.fxml");
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }
}
