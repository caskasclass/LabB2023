package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jars.Track;
import jars.TrackDetails;
import util.TableViewManager;
import Models.AllTrackModule;
import Models.HomeModule;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import Session.WindowStyle;
import javafx.scene.layout.BackgroundFill;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class AllTrackViewController {

    @FXML
    private VBox contentContainer;

    @FXML
    private VBox tableViewContainer;

    @FXML
    private Label leftLabel;

    @FXML
    private TextField searchBar;

    @FXML
    private Pane MainFrame;

    @FXML
    private HBox playlistBoxContainer;

    @FXML
    private Background primaryShader;

    @FXML
    private void initialize() {
        WindowStyle.ResetColor();
        primaryShader = new Background(new BackgroundFill(Color.BLUE, null, null));
        MainFrame.setBackground(WindowStyle.setInitialBackground());

        try {
            setTopTracks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            searchBar.setOnKeyPressed(event -> {
                if (event.getCode() != KeyCode.ENTER) {
                    setResultsTitle(searchBar.getText());
                } else {
                    setResultsArtist(searchBar.getText());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setTopTracks() throws RemoteException {
        TableViewManager topTracks = new TableViewManager(true, false);
        topTracks.setPrefHeight(980);
        HomeModule homeModule = new HomeModule();
        ArrayList<TrackDetails> topT = homeModule.getToptracks();

        ObservableList<Track> data = FXCollections.observableArrayList();
        for (TrackDetails track : topT) {
            data.add(track.track);
        }

        topTracks.setItems(data);
        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(topTracks);
        VBox.setVgrow(topTracks, Priority.ALWAYS);
    }

    private void setResultsTitle(String s) {
        try {
            TableViewManager res = new TableViewManager(true, false);
            res.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksName(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                res.setItems(data);
                tableViewContainer.getChildren().clear();
                tableViewContainer.getChildren().add(res);
            } else {
                this.setTopTracks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResultsArtist(String s) {
        try {
            TableViewManager res = new TableViewManager(true, false);
            res.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksAutor(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                res.setItems(data);
                tableViewContainer.getChildren().clear();
                tableViewContainer.getChildren().add(res);
            } else {
                this.setTopTracks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
