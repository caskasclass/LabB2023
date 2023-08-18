package Threads;

import Session.WindowAppearance;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ResizeHandler extends Thread {

    public static  double MaxWidth = WindowAppearance.getWindowWidth() * 0.25; // non toccare
    public static double currentCenterRegisteredWidth = (WindowAppearance.getWindowWidth() - MaxWidth);
    private BorderPane rootPane;
    private VBox rootMenu;


    public ResizeHandler(BorderPane rootPane, VBox rootMenu) {
        this.setDaemon(true);
        this.rootPane = rootPane;
        this.rootMenu = rootMenu;
    }

    public static synchronized double getCenterWidth(){
        return currentCenterRegisteredWidth;
    }
    public void run() {
        rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            Platform.runLater(() -> {
                animateMenuWidth(newWidth.doubleValue());

            });
        });

    }
    private void animateMenuWidth(double newWidth) {
        double menuWidth = Math.min(newWidth * 0.23, MaxWidth); // Calcolo della nuova larghezza
        rootMenu.setPrefWidth(menuWidth);
        if((newWidth - menuWidth)<450){
            System.out.println("ah ah ah");
        }
        currentCenterRegisteredWidth =newWidth - menuWidth ;
    }

}
