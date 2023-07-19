package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;


public class homeViewController {


    @FXML
    private HBox homeView_gradient;


    @FXML
    private void initialize(){
        /*Stop[] stops = new Stop[] { new Stop(0, Color.rgb(0, 0, 0, 0.6)), new Stop(1, Color.rgb(18,18,18))};
        LinearGradient grad = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);  
        homeView_gradient.setBackground(Background.fill(grad));*/
    }


    //metodi per gestire gli eventi.
    
}
