package Threads;


import Session.WindowAppearance;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ResizeHandler extends Thread {

    private double MaxWidth = WindowAppearance.getWindowWidth() * 0.25; // non toccare

    double registeredWidth;
    BorderPane rootPane;
    VBox rootMenu;

    public ResizeHandler(BorderPane rootPane,VBox rootMenu){
        this.rootPane = rootPane;
        this.rootMenu = rootMenu;
    }

    public void run(){
    rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            animateMenuWidth(newWidth.doubleValue());
        });
    }

    private void animateMenuWidth(double newWidth) {
        double menuWidth = Math.min(newWidth * 0.23, MaxWidth); // Calcolo della nuova larghezza
        rootMenu.setPrefWidth(menuWidth);
    }
      
    
}
