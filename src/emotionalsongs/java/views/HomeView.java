/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
package views;

import java.io.IOException;

import controllers.homeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import util.FXMLLoaders;

/**
 *view home
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class HomeView extends StackPane {
    /**oggetto loading view fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**Costruttore view
     * @param initialWidth parametro iniziale dimensioni home
     */
    public HomeView(double initialWidth) {
        FXMLLoader loader = fxmlutil.getLoader("homeView.fxml");
        homeViewController controller = new homeViewController(initialWidth);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }
}
