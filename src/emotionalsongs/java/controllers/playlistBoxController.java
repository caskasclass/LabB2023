package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class playlistBoxController {

    @FXML
    private VBox vboxroot;

    @FXML
    private Label playlistName;

    @FXML
    private Label playlistOwner;

    @FXML 
    private HBox container_for_ragion;

    @FXML
    private Region playlistBox_image;

    ImageView imageview = new ImageView();

    @FXML
    void initialize() {

        playlistName.setText("unknown name");
        playlistOwner.setText("unknown owner");

        //container_for_ragion.prefHeightProperty().bind(container_for_ragion.widthProperty());
        playlistBox_image.setStyle("-fx-background-image: url('/imgs/playlist_img/img2.png');");

    }

}
