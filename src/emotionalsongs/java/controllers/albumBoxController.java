package controllers;

import jars.AlbumPreview;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class albumBoxController {


    private AlbumPreview albumPreview; 

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


    public albumBoxController(AlbumPreview albumPreview) {
        this.albumPreview = albumPreview;
    }
    
    @FXML
    void initialize() {
        albumName.setText(albumPreview.getAlbumName()); 
        artistNames.setText(albumPreview.getAlbumArtists());
        imgregion.setStyle("-fx-background-image:url('"+albumPreview.getAlbumImg0()+"');");
    }

    
}
