/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
package views;

import java.io.IOException;

import controllers.albumBoxController;
import jars.AlbumPreview;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import util.FXMLLoaders;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *view album
 */
public class AlbumView extends HBox {
    /**oggetto loading view fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**grafica*/
    public static final int MAX_HEIGHT = 80;
    /**grafica*/
    public static final int MIN_WIDTH = 270;
    /**Costruttore view
     * @param albumPreview
    */
    public AlbumView(AlbumPreview albumPreview) {
        FXMLLoader loader = fxmlutil.getLoader("albumBox.fxml");
        albumBoxController controller = new albumBoxController(albumPreview);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
