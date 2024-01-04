package controllers;

import Session.WindowStyle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;

public class playlistBoxController {

    private boolean focusEnabler;

    public String image = null;
    public String title = null;
    public String user = null;


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

        playlistName.setText(title);
        playlistOwner.setText(user);
        double padding = vboxroot.getPadding().getLeft() * 2;
        container_for_ragion.minHeightProperty().bind(vboxroot.widthProperty().subtract((padding)));
        playlistBox_image.setStyle("-fx-background-image:url("+ image + ");");

    }

    public void setFocusaEnabler(Boolean tf) {
        focusEnabler = tf;
    }

    public void captureColor(MouseEvent e) {
        if (focusEnabler) {
            Color captured = ColorsManager.getDominantColor(playlistBox_image.getBackground().getImages().get(0).getImage());
            WindowStyle.setColor(captured);
        }
    }
}
