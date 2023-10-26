package views;

import java.io.IOException;

import controllers.playlistViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;

public class PlaylistView extends StackPane{
    FXMLLoaders fxmlutil = new FXMLLoaders();
    
    public PlaylistView(){

        FXMLLoader loader = fxmlutil.getLoader("playlistView.fxml");
        playlistViewController controller= new playlistViewController();
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
