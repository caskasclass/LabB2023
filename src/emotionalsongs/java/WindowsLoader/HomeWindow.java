/**
 * Contiene le classi necessarie a implementare le finestre pop-up e non dell'applicazione
  * @package WindowsLoader
 * @see package.emotionalsongs.java
 */
package WindowsLoader;

import Session.WindowAppearance;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.FXMLLoaders;
/**
 *Classe creata per estendere la classe Stage di JavaFX e rappresenta la finestra principale dell'applicazione
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class HomeWindow extends Stage {
    /**dimesioni finestra */
    private static final double MIN_WINDOW_WIDTH = 800.0;
    /**dimensioni finestra */
    private static final double MIN_WINDOW_HEIGHT = 600.0;
    /**oggetto per loading del file fxml */
    FXMLLoaders loader = new FXMLLoaders();

    
/*Costruttore della classe HomeWindow
*costruttore inizializza una finestra con un titolo, con dimensioni minime, con una posizione iniziale e un'icona
*/
    public HomeWindow(){
            BorderPane mainContaier = (BorderPane) loader.loadFXML("HomeWindow.fxml");
            Scene scene = new Scene(mainContaier, WindowAppearance.getWindowWidth(), WindowAppearance.getWindowHeight());
            setScene(scene);
            setTitle("EmotionalSongs");
            setMinHeight(MIN_WINDOW_HEIGHT);
            setMinWidth(MIN_WINDOW_WIDTH);
            setX( WindowAppearance.getWindowX());
            setY(WindowAppearance.getWindowY());
            getIcons().add(new Image("/imgs/icon.png"));
    }
}
