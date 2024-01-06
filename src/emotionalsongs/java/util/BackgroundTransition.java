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

public class BackgroundTransition extends Transition {
    public static  Color hbox_header;
    private static final Color shader = Color.rgb(18, 18, 18);
    private final  HBox hBox;
    private final  Color initialBackground;
    private final  Color targetBackground;

    public static void setHeaderGraphics(Color header){
        hbox_header= header;
    }

    public BackgroundTransition(HBox hbox,Duration duration,Color oldColor, Color newColor){
        hbox_header = newColor;
        this.hBox = hbox;
        this.initialBackground = oldColor;
        this.targetBackground = newColor;
        setCycleDuration(duration);
    }

    @Override
    protected void interpolate(double frac){
        Background transition = getGradientBackground(frac);
        hBox.setBackground(transition);
        
    }

    public static Background gettLinearGradient(Color color){
        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(0, color),
                new Stop(1, shader)
        });
        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, cornerRadii, null);
        return (new Background(backgroundFill));
        
    }
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
