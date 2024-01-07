/**
 * Contiene le classi necessarie a
 * gestire funzionalità generiche di una sessione in app.
  * @package Session
 * @see package.emotionalsongs.java
 */
package Session;

import javafx.scene.control.ScrollPane;
/**
 *Classe di variabili globali creata per accedere al  rootFrame>  dell'applicazione
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class Globals {
    /**rootPane FXML*/
    private static ScrollPane rootFrame;


/** Metodo pubblico che restituisce il valore corrente di rootFrame
*@return  rootFrame  
*/
    public static ScrollPane getRootFrame(){
        return rootFrame;
    }



/**Metodo pubblico che imposta il valore di rootFrame con il valore fornito come argomento
*@param  scrollpane  variabile di tipo ScrollPane
*/
    public static void setRootFrame(ScrollPane scrollpane){
        rootFrame = scrollpane;
    }



}
