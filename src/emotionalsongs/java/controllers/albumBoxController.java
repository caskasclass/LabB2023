package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class albumBoxController {
    
    public int imgnumber = 0;

    @FXML
    private HBox rootHbox;

    @FXML
    private Label albumName;

    @FXML
    private Label artistNames;

    @FXML
    private VBox regionContainer;

    @FXML
    private Region imgregion;

    @FXML
    void initialize() {
        albumName.setText("Album Name");
        artistNames.setText("Maybe some artsits ");
        imgregion.setStyle("-fx-background-image:url('/imgs/playlist_img/img"+imgnumber+".png');");
    }

    

}
