package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import tmp.Canzone;

public class canzoneViewController {



    Canzone c = null;

    @FXML
    private VBox rootCanzoneview;
    @FXML
    private Label nomeCanzone;
    @FXML
    private Label nomeAlbum;
    @FXML
    private FlowPane containerEvaluation;




    @FXML
    private void initialize(){

        createEvalBoxes();

    }

    public void setCanzone(Canzone canzone){
        c=canzone;
    }

    public void createEvalBoxes(){
        String[] emotions = {"Amazement","Solemnity","Tenderness","Nostalgia","Calmness","Power","Joy","Tension","Sadness"};
        for (String string : emotions) {

            VBox boxEmozione = new VBox();
            boxEmozione.setAlignment(Pos.CENTER);
            boxEmozione.setSpacing(5);
            boxEmozione.setPrefHeight(100);
            Label tipoEmozione = new Label(string);
            ChoiceBox<Integer> choiceBox = new ChoiceBox<Integer>(FXCollections.observableArrayList(1, 2, 3, 4, 5));
            choiceBox.getStyleClass().add("choiceBox");
            choiceBox.setPrefWidth(100);
            boxEmozione.getChildren().addAll(tipoEmozione, choiceBox);
            containerEvaluation.getChildren().add(boxEmozione);
        }
    }

}
