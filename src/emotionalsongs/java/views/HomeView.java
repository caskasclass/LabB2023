package views;

import java.io.IOException;

import controllers.homeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;


public class HomeView extends StackPane {
    
    FXMLLoaders fxmlutil = new FXMLLoaders();

    public HomeView(double initialWidth) {
        FXMLLoader loader = fxmlutil.getLoader("homeView.fxml");
        homeViewController controller = new homeViewController(initialWidth);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }
}
