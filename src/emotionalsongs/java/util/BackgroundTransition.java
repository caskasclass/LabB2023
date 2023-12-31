/**
 * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import javafx.util.Duration;

import javafx.animation.Transition;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
/**
*Classe creata estendendo la classe Transition
 *funzionalità di gestione delle transizioni di sfondo di un oggetto di tipo HBox (un contenitore orizzontale di JavaFX) da un colore a un altro durante un intervallo di tempo specificato
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 
 */
public class BackgroundTransition extends Transition {
    public static  Color hbox_header;
    private static final Color shader = Color.rgb(18, 18, 18);
    private final  HBox hBox;
    private final  Color initialBackground;
    private final  Color targetBackground;


 /**metodo statico che imposta il colore di sfondo
  * @param header colore header
 */
    public static void setHeaderGraphics(Color header){
        hbox_header= header;
    }


 
/** costruttore della classe: viene fornita
 * @param hbox HBox
 * @param duration della transizione
 * @param oldColor colore iniziale
 * @param newColor colore finale*/
    public BackgroundTransition(HBox hbox,Duration duration,Color oldColor, Color newColor){
        hbox_header = newColor;
        this.hBox = hbox;
        this.initialBackground = oldColor;
        this.targetBackground = newColor;
        setCycleDuration(duration);
    }


 
 /**metodo che viene chiamato durante l'animazione
 *Calcola il colore di sfondo interpolato in base al valore di frac e imposta il nuovo sfondo della HBox
*@param frac parametro di tipo double
*/   
    @Override
    protected void interpolate(double frac){
        Background transition = getGradientBackground(frac);
        hBox.setBackground(transition);
        
    }


 
 /**metodo statico che restituisce uno sfondo con una sfumatura lineare da color a un colore predefinito
*@param color parametro di tipo Color
*@return oggetto background
*/   
    public static Background gettLinearGradient(Color color){
        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(0, color),
                new Stop(1, shader)
        });
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, cornerRadii, null);
        return (new Background(backgroundFill));
        
    }



 /**metodo privato che restituisce uno sfondo con una sfumatura lineare da initialBackground a targetBackground in base al valore di frac
*@param frac parametro di tipo double
*@return oggetto background
*/  
    private  Background getGradientBackground(double frac) {
        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        Stop[] stops = new Stop[] {
                new Stop(0, initialBackground.interpolate(targetBackground,frac)),
                new Stop(1, shader)
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, cornerRadii, null);
        return (new Background(backgroundFill));
    }

}
