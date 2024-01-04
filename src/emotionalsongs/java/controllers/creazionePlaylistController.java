package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.effect.BoxBlur;
import util.TableViewManager;
import views.HomeView;
import WindowsLoader.ImagesWindow;
import jars.Playlist;
import jars.Track;
import jars.TrackDetails;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Models.AllTrackModule;
import Models.HomeModule;
import Models.PlaylistModule;
import Session.ClientSession;
import Session.Globals;
import Threads.ResizeHandler;
import javafx.stage.Stage;

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

    static URL img;
    private TableViewManager playlistTracks = new TableViewManager(false, false);
    private TableViewManager findTracks = new TableViewManager(false, true);

    @FXML
    void initialize() {

        
        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements(width);
        });
        try {
            setTopTracks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initializeElements(double width) {
        owner.setText(ClientSession.client.getUserid());
        playlistTracks.prefWidthProperty().bind(container.widthProperty());
        playlistTracks.setMinHeight(300);
        playlistTracks.setVisible(false);
        tableContainer.getChildren().add(0, playlistTracks);

        findTracks.setMinHeight(300);
        findTracks.setVisible(false);


        cerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                findTracks.setVisible(false); // Hide TableView when the TextField is empty
            } else {
                findTracks.setVisible(true); // Show TableView when there is text in the TextField
            }
        });
        try {
            cerca.setOnKeyPressed(event -> {
                if (event.getCode() != KeyCode.ENTER) {
                    setResultsTitle(cerca.getText());
                } else {
                    setResultsArtist(cerca.getText());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        tableContainer.getChildren().add(findTracks);
    }

    public void openImages(MouseEvent e) {
        Stage parent = (Stage) createButton.getScene().getWindow();
        ImagesWindow window = new ImagesWindow(playlistImage);
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

    public void registraPlaylist(MouseEvent e) throws NotBoundException, RemoteException{
        Playlist p = new Playlist(playlistName.getText(), ClientSession.client.getUserid());
        ArrayList<Track> ar = new ArrayList<Track>(playlistTracks.getItems());
        ArrayList<String> track_ids = new ArrayList<String>();
        for(Track t: ar){
            track_ids.add(t.getTrack_id());
        }
        String image = playlistImage.getImage().getUrl();
        p.setTrackList(track_ids);
        p.setImage(image);
        PlaylistModule pm = new PlaylistModule();
        pm.createPlaylist(p);
        Globals.getRootFrame().setContent(new HomeView(ResizeHandler.getCenterWidth()));
         
    }


    private void setTopTracks() throws RemoteException {
        findTracks.setPrefHeight(980);
        HomeModule homeModule = new HomeModule();
        ArrayList<TrackDetails> topT = homeModule.getToptracks();

        ObservableList<Track> data = FXCollections.observableArrayList();
        for (TrackDetails track : topT) {
            data.add(track.track);
        }

        findTracks.setItems(data);
    }

    private void setResultsTitle(String s) {
        try {
            findTracks.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksName(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                findTracks.setItems(data);
            } else {
                this.setTopTracks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResultsArtist(String s) {
        try {
            findTracks.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksAutor(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                findTracks.setItems(data);
            } else {
                this.setTopTracks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
