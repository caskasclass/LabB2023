package controllers;

import Session.WindowAppearance;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tmp.Canzone;
import util.ColorsManager;
import util.TableViewManager;
import views.AlbumView;
import views.PlaylistBox;



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
    private HBox table1;

    @FXML
    private HBox table2;

    private void initialize() {
       

        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements(width);
        });
    }

    private void initializeElements(double width){
       
        TableViewManager playlistTracks = new TableViewManager();
        playlistTracks.setMinHeight(300);
        table1.getChildren().add(playlistTracks);
        TableViewManager findTracks = new TableViewManager();
        findTracks.setMinHeight(300);
        table1.getChildren().add(findTracks);
    
       
    }
}
