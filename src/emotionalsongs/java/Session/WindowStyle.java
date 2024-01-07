/**
 * Contiene le classi necessarie a
 * gestire funzionalità generiche di una sessione in app.
  * @package Session
 * @see package.emotionalsongs.java
 */
package Session;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
/**
*Classe creata per gestire lo stile della finestra, colore dell'intestazione, colore principale e il background iniziale
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 
 */
public class WindowStyle {
    /**elemento grafica*/
    private  static Color headerColor;
    /**elemento grafica*/
    private static  final Color shader = Color.rgb(18, 18, 18);
    /**elemento grafica*/
    private static final Color initialMainColor  =Color.rgb(62, 32, 146, 0.6);
    /**elemento grafica*/
    private static SimpleObjectProperty<Color> mianColor = new SimpleObjectProperty<Color>(initialMainColor);


/**Metodo setter per impostare il colore dell'intestazione*/
    public static void setHeaderColor(Color c){
        headerColor = c;
    }

    

/**Metodo getter per ottenere il colore dell'intestazione*/
    public static Color getHeaderColor(){
        return headerColor;
    }

    
    
/**Metodo setter per impostare il colore principale*/ 
    public static void setColor(Color c) {
        mianColor.set(c);
        //System.out.println("Color setted : "+mianColor.toString());
    }

    

/**Metodo getter per ottenere il colore principale
*in realtà non serve, usato solo per debug
*/ 
    public static SimpleObjectProperty<Color> getColor() {
        return mianColor;
    }



/**Metodo per ripristinare il colore principale a quello iniziale*/     
    public static void  ResetColor(){
        mianColor.set(initialMainColor);
    }



/**Metodo per impostare lo sfondo iniziale della finestra
*@return oggetto di tipo Background contenente uno sfondo lineare basato sui colori principali e di sfondi specificati
*/ 
    public static  Background setInitialBackground() {
        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        Stop[] stops = new Stop[] {
                new Stop(0, mianColor.get()),
                new Stop(1, shader)
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, cornerRadii, null);
        return (new Background(backgroundFill));
    }
   

}
