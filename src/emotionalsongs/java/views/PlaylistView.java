package views;

import java.io.IOException;

import controllers.playlistViewController;
import jars.Playlist;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;

public class PlaylistView extends StackPane{
    FXMLLoaders fxmlutil = new FXMLLoaders();
    
    public PlaylistView(Playlist p){

        FXMLLoader loader = fxmlutil.getLoader("playlistView.fxml");
        playlistViewController controller= new playlistViewController();
        controller.title = p.getTitle();
        controller.user = p.getUser();
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
