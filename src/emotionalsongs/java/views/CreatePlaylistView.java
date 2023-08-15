package views;

import java.io.IOException;

import controllers.creazionePlaylistController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

public class CreatePlaylistView extends VBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();
    
    public CreatePlaylistView(){

        FXMLLoader loader = fxmlutil.getLoader("creazionePlaylist.fxml");
        creazionePlaylistController controller= new creazionePlaylistController();
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
