/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
package views;

import java.io.IOException;

import controllers.explorePlaylistController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;

/**
 *view esplora playlist
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class ExplorePlaylistView extends StackPane {
    /**oggetto loading view fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**Costruttore view */
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
