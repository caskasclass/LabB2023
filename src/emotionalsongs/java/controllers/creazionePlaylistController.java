package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import util.TableViewManager;

public class creazionePlaylistController {

    @FXML
    private TextField cerca;

    @FXML
    private VBox container;

    @FXML
    private Button createButton;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    @FXML
    private TextField playlistName;

    @FXML
    private VBox tableContainer;

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements(width);
        });
    }

    private void initializeElements(double width) {
        TableViewManager playlistTracks = new TableViewManager();
        playlistTracks.setMinHeight(300);
        tableContainer.getChildren().add(0,playlistTracks);
        TableViewManager findTracks = new TableViewManager();
        findTracks.setMinHeight(300);
        tableContainer.getChildren().add(2,findTracks);

    }
}
