/**
 * Contiene le classi necessarie a implementare e caricare
 * le views dell'applicazione.
  * @package views
 * @see package.emotionalsongs.java
 */
package views;

import java.io.IOException;

import controllers.canzoneViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import jars.*;
import util.FXMLLoaders;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *view canzone corrente
 */
public class CanzoneView extends VBox{
    /**oggetto loading view fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();

    /**Costruttore view canzone corrente c
     * @param c
     */
    public CanzoneView(Track c){

        FXMLLoader loader = fxmlutil.getLoader("CanzoneView.fxml");
        canzoneViewController controller= new canzoneViewController();
        controller.setCanzone(c);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
