package views;

import java.io.IOException;

import controllers.explorePlaylistController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;

public class ExplorePlaylistView extends StackPane {
    FXMLLoaders fxmlutil = new FXMLLoaders();
    
    public ExplorePlaylistView(){

        FXMLLoader loader = fxmlutil.getLoader("explorePlaylistView.fxml");
        explorePlaylistController controller= new explorePlaylistController();
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
