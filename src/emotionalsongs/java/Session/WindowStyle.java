package Session;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class WindowStyle {
    private  static Color headerColor;
    private static  final Color shader = Color.rgb(18, 18, 18);
    private static final Color initialMainColor  =Color.rgb(62, 32, 146, 0.6);
    private static SimpleObjectProperty<Color> mianColor = new SimpleObjectProperty<Color>(initialMainColor);

    public static void setHeaderColor(Color c){
        headerColor = c;
    }
    public static Color getHeaderColor(){
        return headerColor;
    }
    
    
    public static void setColor(Color c) {
        mianColor.set(c);
        //System.out.println("Color setted : "+mianColor.toString());
    }
    // in realtà non serve,lo uso solo per debug
    public static SimpleObjectProperty<Color> getColor() {
        return mianColor;
    }
    public static void  ResetColor(){
        mianColor.set(initialMainColor);
    }

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
