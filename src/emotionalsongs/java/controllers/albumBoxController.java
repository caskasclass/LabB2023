package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class albumBoxController {

    @FXML
    private Label albumName;

    @FXML
    private Label artistNames;

    @FXML
    private ImageView imageview;

    @FXML
    void initialize() {
        System.out.println("albumBoxController initialized");
        String urlImg = getClass().getResource("/imgs/playlist_img/img6.png").toExternalForm();
        Image img = new Image(urlImg);
        imageview.setImage(img);
        applyRoundedCornerClip(imageview, 8);
        albumName.setText("unknown name");
        artistNames.setText("unknown artists");
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
