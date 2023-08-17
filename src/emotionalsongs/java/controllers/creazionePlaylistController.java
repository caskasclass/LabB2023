package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import util.TableViewManager;
import WindowsLoader.ImagesWindow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import util.ColorsManager;
import views.HomeView;
import views.CreatePlaylistView;
import javafx.stage.Stage;
import javafx.scene.effect.BoxBlur;

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
        playlistTracks.setVisible(false);
        //tableContainer.getChildren().add(0,playlistTracks);
        TableViewManager findTracks = new TableViewManager();
        findTracks.setMinHeight(300);
        findTracks.setVisible(false);
        cerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                findTracks.setVisible(false); // Hide TableView when the TextField is empty
            } else {
                findTracks.setVisible(true); // Show TableView when there is text in the TextField
            }
        });
        tableContainer.getChildren().add(findTracks);

    }

    public void openImages(MouseEvent e){
        Stage parent = (Stage) createButton.getScene().getWindow();
        ImagesWindow window = new ImagesWindow();
        window.initOwner(parent);
        BoxBlur blur = new BoxBlur(10, 10, 3);
        parent.getScene().getRoot().setEffect(blur);
        Platform.runLater(() -> {
            parent.getScene().getRoot().setDisable(true);
        });
        window.setOnHidden(event -> {
            parent.getScene().getRoot().setEffect(null);
            parent.getScene().getRoot().setDisable(false);
        });
        window.show();
    }

    
}
