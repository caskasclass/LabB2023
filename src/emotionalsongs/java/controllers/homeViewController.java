package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;
import views.PlaylistBox;

public class homeViewController {

    @FXML
    private HBox primaryShader;

    @FXML
    private VBox contentContainer;

    @FXML
    private HBox playlistBoxContainer;

    @FXML
    private FlowPane albumBoxContainer;

    @FXML
    private TableView<?> tableViewTopClicked;

    @FXML
    private HBox othersPlaylistBoxContainer;

    @FXML
    private void initialize() {

        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        BackgroundFill backgroundFill = new BackgroundFill(
                ColorsManager.setGradient(Color.rgb(62, 32, 146, 0.6), Color.rgb(18, 18, 18)),
                cornerRadii,
                null);
        Background background = new Background(backgroundFill);
        primaryShader.setBackground(background);



        for (int i = 0; i < 4; i++) {
            PlaylistBox playlist = new PlaylistBox();
            HBox.setHgrow(playlist, Priority.ALWAYS);
            playlistBoxContainer.getChildren().add(playlist);
           
        }
        for (int i = 0; i < 4; i++) {
            PlaylistBox playlist = new PlaylistBox();
            HBox.setHgrow(playlist, Priority.ALWAYS);
            othersPlaylistBoxContainer.getChildren().add(playlist);
        }

    }

    public static void setPlaylist(double w){

    }
}
