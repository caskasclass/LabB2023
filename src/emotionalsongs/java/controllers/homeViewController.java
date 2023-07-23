package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;
import util.FXMLLoaders;

public class homeViewController {

    @FXML
    private HBox primaryShader;

    @FXML HBox playlistBoxContainer;

    @FXML
    private FlowPane albumBoxContainer;

    @FXML
    private TableView<?> tableViewTopClicked;
    @FXML
    private HBox othersPlaylistBoxContainer;

    private FXMLLoaders loader = new FXMLLoaders();

    @FXML
    private void initialize() {

        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        BackgroundFill backgroundFill = new BackgroundFill(
            ColorsManager.setGradient(Color.rgb(62, 32, 146, 0.6), Color.rgb(18, 18, 18)),
            cornerRadii,
            null
        );
        Background background = new Background(backgroundFill);
        primaryShader.setBackground(background);


        //aggiungo i box per le playlist , per il test ne bastano 4 
        for (int i = 0; i < 4; i++ ){
            playlistBoxContainer.getChildren().add((VBox) loader.loadFXML("playlistBox.fxml"));
        }


        //aggiungo i box per gli album , per il test ne bastano 4
        for (int i = 0; i < 6; i++ ){
            albumBoxContainer.getChildren().add((HBox) loader.loadFXML("albumBox.fxml"));
        }


        //aggiungo i box per le playlist , per il test ne bastano 4 
        for (int i = 0; i < 4; i++ ){
            othersPlaylistBoxContainer.getChildren().add((VBox) loader.loadFXML("playlistBox.fxml"));
        }




    }

}
