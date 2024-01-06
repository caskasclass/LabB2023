/**
 * Contiene le classi necessarie a
 * gestire funzionalità generiche di una sessione in app.
  * @package Session
 * @see package.emotionalsongs.java
 */
package Session;

import javafx.stage.Screen;
/**
 *Classe creata per gestire le dimensioni e la posizione di una finestra in base a determinati parametri
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class WindowAppearance {
    /**elemento dimensioni*/
    private static final double WIDTH_PERCENTAGE = 0.85;
    /**elemento dimensioni*/
    private static final double HEIGHT_PERCENTAGE = 0.90;
    /**elemento dimensioni*/
    private static double screenWidth;
    /**elemento dimensioni*/
    private static double screenHeight;
    /**elemento dimensioni*/
    private static double windowWidth;
    /**elemento dimensioni*/
    private static double windowHeight;
    /**elemento posizione*/
    private static double windowX;
    /**elemento posizione*/
    private static double windowY;


/**Metodo per calcolare le dimensioni e la posizione della finestra in base alle dimensioni dello schermo e alle costanti di percentuali*/
    public static void calculateWindowDimensions() {
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        windowWidth = screenWidth * WIDTH_PERCENTAGE;
        windowHeight = screenHeight * HEIGHT_PERCENTAGE;

        windowX = (screenWidth - windowWidth) / 2;
        windowY = (screenHeight - windowHeight) / 2;
    }


    
/** Metodo che restituisce le dimensioni della finestra
 * @return lunghezza 
*/
    public static double getWindowWidth() {
        return windowWidth;
    }
/** Metodo che restituisce le dimensioni della finestra
 * @return altezza 
*/
    public static double getWindowHeight() {
        return windowHeight;
    }
  /** Metodo che restituisce le posizioni della finestra
 * @return X 
*/
    public static double getWindowX() {
        return windowX;
    }
   /** Metodo che restituisce le posizioni della finestra
 * @return Y 
*/
    public static double getWindowY() {
        return windowY;
    }

}
