package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class playlistBoxController {

    @FXML
    private  Label playlistName;

    @FXML 
    private Label playlistOwner;
    
    @FXML
    private ImageView imageView;

    @FXML
    void initialize() {
        String urlImg  = getClass().getResource("/imgs/playlist_img/img2.png").toExternalForm();
        Image img = new Image(urlImg);
        imageView.setImage(img);
        applyRoundedCornerClip(imageView, 8);
        playlistName.setText("unknown name");
        playlistOwner.setText("unknown owner");
        System.out.println("PlaylistBoxController initialized");

    }
    private void applyRoundedCornerClip(ImageView imageView, double cornerRadius) {
        double width = imageView.getFitWidth();
        double height = imageView.getFitHeight();

        Rectangle clip = new Rectangle(width, height);

        // Clip only the top-left and bottom-left corners
        clip.setArcWidth(cornerRadius);
        clip.setArcHeight(cornerRadius);

        imageView.setClip(clip);
    }
}
