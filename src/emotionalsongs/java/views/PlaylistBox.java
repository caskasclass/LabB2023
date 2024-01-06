/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
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

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *view riquadro playlist
 */
public class PlaylistBox extends VBox {
    /**oggetto loading view fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**grafica*/    
    public final static int MinWidth = 145;
    /**grafica*/
    public final static int MaxWidth = 230;
    /**reference del controller*/
    homeWindowController homeWindowController = new homeWindowController();
    /**Costruttore riquadro playlist
     * @param p playlist da selezionare con doppio click
     * @param focus grafica
    */
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
