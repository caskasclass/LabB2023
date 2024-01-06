/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
package views;

import java.io.IOException;

import controllers.playlistViewController;
import jars.Playlist;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *view playlist corrente
 */
public class PlaylistView extends StackPane{
    /**oggetto loading view fxml */    
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**Costruttore view
     * @param p playlist selezionata
     */
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
