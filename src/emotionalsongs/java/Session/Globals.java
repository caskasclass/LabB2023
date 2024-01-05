package Session;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe creata per  fornire metodi getter e setter per interagire con la variabile statica <code>rootFrame</code> da altre parti del codice
 */
public class Globals {

    private static ScrollPane rootFrame;


/** Metodo pubblico che restituisce il valore corrente di rootFrame
*@return <code>rootFrame</code> 
*/
    public static ScrollPane getRootFrame(){
        return rootFrame;
    }



/**Metodo pubblico che imposta il valore di rootFrame con il valore fornito come argomento
*@param <code>scrollpane</code> variabile di tipo ScrollPane
*/
    public static void setRootFrame(ScrollPane scrollpane){
        rootFrame = scrollpane;
    }



}
