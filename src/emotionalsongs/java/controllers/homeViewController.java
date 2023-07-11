package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class homeViewController {

    @FXML
    Pane viewPane;

    public homeViewController(){}

    @FXML
    private void initialize(){
        System.out.println("Controller funzionante.");
        Label test = new Label("Home View ");
        test.setStyle("-fx-background-color: white;");
        Platform.runLater(()->{
            viewPane.setStyle("-fx-background-color: pink;");
            viewPane.getChildren().add(test);
        });
    }


    //metodi per gestire gli eventi.
    
}
