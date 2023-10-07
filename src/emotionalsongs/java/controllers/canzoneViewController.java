package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
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
    private void initialize(){
        System.out.println("\n\n\n ciao \n\n\n");
        nomeCanzone.setText(c.getTrackName());
        nomeAlbum.setText(c.getTrackAlbum());
        Label label= new Label("ciao");
        rootCanzoneview.getChildren().add(label);


    }

    public void setCanzone(Canzone canzone){
        c=canzone;
    }


}
