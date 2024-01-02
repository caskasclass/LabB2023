package views;


import java.io.IOException;

import Session.Globals;
import controllers.homeWindowController;
import controllers.playlistBoxController;
import jars.Playlist;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

public class PlaylistBox extends VBox {
    FXMLLoaders fxmlutil = new FXMLLoaders();
    // model da istanziare
    public final static int MinWidth = 145;
    public final static int MaxWidth = 230;

    homeWindowController homeWindowController = new homeWindowController();

    public PlaylistBox(Playlist p, boolean focus) {
        FXMLLoader loader = fxmlutil.getLoader("playlistBox.fxml");
        playlistBoxController controller = new playlistBoxController();
        controller.image = p.getImage();
        controller.title = p.getTitle();
        controller.user = p.getUser();
        controller.setFocusaEnabler(focus);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMaxWidth(MaxWidth);
        setMinWidth(MinWidth);
        
        VBox.setVgrow(this, Priority.ALWAYS);
       
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) { // doppio clic
                    PlaylistView view = new PlaylistView(p);
                    Globals.getRootFrame().setContent(view);                  
        // Add content to the new stage
                }
            }
        });
    }

    

}
