package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

public class PlaylistBox extends VBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();
    // model da istanziare
    public final static int MinWidth = 145;
    public final static int MaxWidth = 230;

    public PlaylistBox() {
        FXMLLoader loader = fxmlutil.getLoader("playlistBox.fxml");
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMaxWidth(MaxWidth);
        setMinWidth(MinWidth);
        
        VBox.setVgrow(this, Priority.ALWAYS);
       

    }

}
