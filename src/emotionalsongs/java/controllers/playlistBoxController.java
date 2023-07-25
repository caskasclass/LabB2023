package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class playlistBoxController {

    @FXML
    private VBox vboxroot;

    @FXML
    private Label playlistName;

    @FXML
    private Label playlistOwner;

    @FXML
    private VBox imagePane;

    ImageView imageview = new ImageView();

    @FXML
    void initialize() {
        playlistName.setText("unknown name");
        playlistOwner.setText("unknown owner");

        double space = vboxroot.getPadding().getLeft() * 2;
        String urlImg = getClass().getResource("/imgs/playlist_img/img2.png").toExternalForm();
        Image img = new Image(urlImg);
        imageview.setPreserveRatio(true);
        imageview.setImage(img);
        imagePane.getChildren().add(imageview);
        Platform.runLater(() -> {
            double width = vboxroot.getWidth();
            imageview.setFitHeight(width - space);
            imageview.setFitWidth(width - space);
            applyRoundedCornerClip(imageview, 8);
            vboxroot.widthProperty().addListener((obs, oldWidth, newWidth) -> fitHandler(newWidth.doubleValue()));
        });
    }

    private void fitHandler(double width) {
        // System.out.println("new width : "+width);
        double space = (vboxroot.getPadding().getLeft()) * 2;
        double newWidthFit = width - space;
        imageview.setFitWidth(newWidthFit);

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
