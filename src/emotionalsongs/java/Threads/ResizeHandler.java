/**
 * Contiene una classe che si occupa del resizing della finestra
 * utilizzando la classe java Thread
 * @package Threads
 * @see package.emotionalsongs.java
 */
package Threads;

import Session.WindowAppearance;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe creata per gestire, grazie al thread ResizeHandler, il ridimensionamento della finestra di menu
 *il thread ascolta i cambiamenti nella larghezza della finestra e, quando rileva una modifica, esegue l'animazione aggiornando la larghezza del menu in base alla nuova larghezza della finestra
 */
public class ResizeHandler extends Thread {
    /**elemento grafica */
    public static  double MaxWidth = WindowAppearance.getWindowWidth() * 0.25;
    /**elemento grafica */
    public static double currentCenterRegisteredWidth = (WindowAppearance.getWindowWidth() - MaxWidth);
    /**elemento grafica */
    private BorderPane rootPane;
    /**elemento grafica */
    private VBox rootMenu;


/**Costruttore che accetta il BorderPane principale e la VBox del menu*/
    public ResizeHandler(BorderPane rootPane, VBox rootMenu) {
        this.setDaemon(true);
        this.rootPane = rootPane;
        this.rootMenu = rootMenu;
    }



/** Metodo synchronized per ottenere la larghezza corrente del centro
*@return  currentCenterRegisteredWidth  istanza che riproduce la larghezza corrente del centro
*/
    public static synchronized double getCenterWidth(){
        return currentCenterRegisteredWidth;
    }



/** Metodo che gestisce l'animazione del ridimensionamento*/
    public void run() {
        rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            Platform.runLater(() -> {
                animateMenuWidth(newWidth.doubleValue());

            });
        });

    }



/**Metodo per animare la larghezza del menu in base alla nuova larghezza della finestra
*@param  newWidth  variabile di tipo double
*/
    private void animateMenuWidth(double newWidth) {
        double menuWidth = Math.min(newWidth * 0.23, MaxWidth); // Calcolo della nuova larghezza
        rootMenu.setPrefWidth(menuWidth);
        if((newWidth - menuWidth)<450){
            System.out.println("ah ah ah");
        }
        currentCenterRegisteredWidth =newWidth - menuWidth ;
    }

}
