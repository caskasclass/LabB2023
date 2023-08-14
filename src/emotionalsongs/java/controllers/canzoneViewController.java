package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class canzoneViewController {


    
    @FXML
    private VBox rootCanzoneview;
    @FXML
    private Label nomeCanzone;


    @FXML
    void initialized(){
        nomeCanzone.setText("fireworks");
        Label l1 = new Label("fireworks");
        rootCanzoneview.getChildren().add(l1);
    }
}
