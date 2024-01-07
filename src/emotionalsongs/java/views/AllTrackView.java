/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
package views;

import java.io.IOException;

import controllers.AllTrackViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

/**
 *view repository canzoni
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class AllTrackView extends VBox {
    /**oggetto loading view fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**Costruttore View */
    public AllTrackView() {

        FXMLLoader loader = fxmlutil.getLoader("AllTrackView.fxml");
        AllTrackViewController controller = new AllTrackViewController();

        loader.setController(controller);

        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
