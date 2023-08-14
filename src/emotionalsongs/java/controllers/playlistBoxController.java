package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class playlistBoxController {

    public int imgnumber = 0;

    @FXML
    private VBox vboxroot;

    @FXML
    private Label playlistName;

    @FXML
    private Label playlistOwner;

    @FXML
    private VBox container_for_ragion;

    @FXML
    private Region playlistBox_image;


    @FXML
    void initialize() {

        playlistName.setText("unknown name");
        playlistOwner.setText("unknown owner");
        double padding = vboxroot.getPadding().getLeft() * 2;
        container_for_ragion.minHeightProperty().bind(vboxroot.widthProperty().subtract((padding)));

        playlistBox_image.setStyle("-fx-background-image:url('/imgs/playlist_img/img"+imgnumber+".png');");

    }
}
