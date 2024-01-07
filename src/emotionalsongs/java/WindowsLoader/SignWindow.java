/**
 * Contiene le classi necessarie a implementare le finestre pop-up e non dell'applicazione
  * @package WindowsLoader
 * @see package.emotionalsongs.java
 */
package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import controllers.homeWindowController;
import controllers.signWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import util.FXMLLoaders;
import util.StyleManager;
/**
 *Classe creata per estendere la classe Stage di JavaFX e rappresenta una finestra per la registrazione o l'accesso 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class SignWindow extends Stage{
    /**oggetto per loading del file fxml */
    FXMLLoaders fxmlutil = new FXMLLoaders();
    /**oggetto per stile  del file fxml */
    StyleManager styleManager  = new StyleManager();

    
/**Costruttore della classe SignWindow che accetta un controller come riferimento
 * @param ref
*/
    public SignWindow(homeWindowController ref) {
             FXMLLoader loader =  fxmlutil.getLoader("signWindow.fxml");
             signWindowController controller = new signWindowController(ref);
             loader.setController(controller);
             try {
                Pane mainContainer = (Pane) loader.load();
                initStyle(StageStyle.UNDECORATED);
                initStyle(StageStyle.TRANSPARENT);
                setResizable(false);
                setScene(new Scene(mainContainer,Color.TRANSPARENT));

             } catch (Exception e) {
                e.printStackTrace();// TODO: handle exception
             }
            
            setOnShown(event ->{
                //contenuto completamente caricato
                //questa soluzione fixa il frame bianco all'avvio dell'applicazione.
                show();
            });
            
    }

}
