/**
 * Contiene le classi necessarie a implementare le finestre pop-up e non dell'applicazione
  * @package WindowsLoader
 * @see package.emotionalsongs.java
 */
package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import util.FXMLLoaders;
import util.StyleManager;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe creata per estendere la classe Stage di JavaFX e rappresenta una finestra di caricamento
 */
public class LoadingWindow extends Stage {
    /**oggetto per loading del file fxml */
    FXMLLoaders loader = new FXMLLoaders();
    /**oggetto per stile del file fxml */
    StyleManager styleManager  = new StyleManager();

    
/**Costruttore: inizializza lo stile della finestra e crea una scena con il layaut caricato
*/
    public LoadingWindow() {
            StackPane mainContaier = (StackPane) loader.loadFXML("loadingWindow.fxml");
            initStyle(StageStyle.UNDECORATED);
            initStyle(StageStyle.TRANSPARENT);
            setResizable(false);
            setScene(new Scene(mainContaier));

            setOnShown(event ->{
                //contenuto completamente caricato
                //questa soluzione fixa il frame bianco all'avvio dell'applicazione.
                show();
            });
            
    }
}
