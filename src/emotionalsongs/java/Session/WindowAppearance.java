package Session;

import javafx.stage.Screen;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe creata per gestire le dimensioni e la posizione di una finestra in base a determinati parametri
 */
public class WindowAppearance {

    private static final double WIDTH_PERCENTAGE = 0.85;
    private static final double HEIGHT_PERCENTAGE = 0.90;

    private static double screenWidth;
    private static double screenHeight;
    private static double windowWidth;
    private static double windowHeight;
    private static double windowX;
    private static double windowY;


/*Metodo per calcolare le dimensioni e la posizione della finestra in base alle dimensioni dello schermo e alle costanti di percentuali*/
    public static void calculateWindowDimensions() {
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        windowWidth = screenWidth * WIDTH_PERCENTAGE;
        windowHeight = screenHeight * HEIGHT_PERCENTAGE;

        windowX = (screenWidth - windowWidth) / 2;
        windowY = (screenHeight - windowHeight) / 2;
    }


    
/** Metodi pubblici Getter che restituiscono le dimensioni e a posizione della finestra
*/
    public static double getWindowWidth() {
        return windowWidth;
    }

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static double getWindowX() {
        return windowX;
    }

    public static double getWindowY() {
        return windowY;
    }

}
