package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Models.AllTrackModule;
import Models.HomeModule;
import Models.PlaylistModule;
import Session.ClientSession;
import Session.Globals;
import Threads.ResizeHandler;
import WindowsLoader.ImagesWindow;
import jars.*;
import util.TableViewManager;
import views.HomeView;
import views.PlaylistView;

public class playlistViewController{

    private Playlist p= null;
    public String title = null;
    public String user = null;
    private String image= null;
    private ArrayList<String> p_strings = new ArrayList<String>();
    private ArrayList<String> addString = new ArrayList<String>();
    private ArrayList<Track> p_tracks = new ArrayList<Track>();
    private ArrayList<Track> addTracks = new ArrayList<Track>();

    private TableViewManager mainTable = new TableViewManager(true, false);
    private TableViewManager addTable = new TableViewManager(false, true);
    private TableViewManager editTable = new TableViewManager(false, false);


    @FXML
    private TextField cerca;

    @FXML
    private HBox buttonCont;

    @FXML
    private VBox container;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    @FXML
    private TextField playlistName;

    @FXML
    private VBox editcontainter;

    @FXML
    private VBox tableContainer;

    @FXML
    void initialize() {
        setPlaylist();
        playlistImage.setImage(new Image(image));
        editcontainter.setVisible(false);
        Platform.runLater(() -> {
            try {
                initializeElements();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
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
            
        });
    }

    private void initializeElements() throws RemoteException{

        if(ClientSession.client.getUserid() == null || !(ClientSession.client.getUserid().equals(user))){
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
        else{
            editButton.setVisible(true);
            deleteButton.setVisible(true);

        }
       
        playlistImage.setDisable(true);
        playlistName.setText(title);
        owner.setText(user);
      



        mainTable.setItems(FXCollections.observableArrayList(p_tracks));
        editTable.setItems(FXCollections.observableArrayList(p_tracks));
        addTable.setItems(FXCollections.observableArrayList(addTracks));

        mainTable.setMinHeight(400);
        addTable.setMinHeight(400);
        addTable.setVisible(false);
        editTable.setMinHeight(400);

        tableContainer.getChildren().add(0, mainTable);
        
        

        cerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                addTable.setVisible(false); // Hide TableView when the TextField is empty
            } else {
                addTable.setVisible(true); // Show TableView when there is text in the TextField
            }
        });

        editButton.setOnMouseClicked(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                editButton.setDisable(true);
                editcontainter.setVisible(true);
                tableContainer.getChildren().remove(0);
                tableContainer.getChildren().add(0, editTable);
                playlistImage.setDisable(false);
                Button button = new Button("Done");
                button.getStyleClass().add("button_cp");
                buttonCont.getChildren().add(1, button);

                button.setOnMouseClicked(param -> {
                    editcontainter.setVisible(false);
                    buttonCont.getChildren().remove(button);
                    editButton.setDisable(false);
                    tableContainer.getChildren().remove(0);
                    tableContainer.getChildren().add(0, mainTable);
                    tableContainer.getChildren().remove(addTable);
                    playlistImage.setDisable(true);
                    try {
                        newTracks(editTable);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Globals.getRootFrame().setContent(new PlaylistView(p));
                }

                );

                tableContainer.getChildren().add(2, addTable);
            }
        });

        deleteButton.setOnMouseClicked(event -> {
            editcontainter.setVisible(false);
            try {
                PlaylistModule pm = new PlaylistModule();
                pm.deletePlaylist(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Globals.getRootFrame().setContent(new HomeView(ResizeHandler.getCenterWidth()));
        });
    }

    private void setPlaylist(){
        try {
                //modificare getPlaylist
                PlaylistModule pm = new PlaylistModule();
                p = pm.getP(title, user);
                image = p.getImage();
                p_strings = p.getTrackList();
                p_tracks = pm.getAllTrack(p_strings, 0, p_strings.size());
                addString = pm.getAllIds();
                addTracks = pm.getAllTrack(p_strings, 0, 20);
                
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
    }

    public void openImages(MouseEvent e) {
        Stage parent = (Stage) editButton.getScene().getWindow();
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

    private void newTracks(TableViewManager editTable) throws RemoteException, NotBoundException{
        ArrayList<Track> ar = new ArrayList<Track>(editTable.getItems());
        ArrayList<String> track_ids = new ArrayList<String>();
        p.setTrackList(track_ids);
        for(Track t: ar){
                track_ids.add(t.getTrack_id());
            }
        ArrayList<String> new_tracks = new ArrayList<String>();
        for(String track: track_ids){
            if(!(p_strings.contains(track))){
                new_tracks.add(track);
            }
        }
        PlaylistModule pm = new PlaylistModule();
        pm.createPlaylist(new Playlist(title,new_tracks,image,user));
        for(String track: p_strings){
            if(!(track_ids.contains(track))){
                pm.deleteTrack(p, track);
            }
        }
        
    }

    private void setTopTracks() throws RemoteException {
        addTable.setPrefHeight(980);
        HomeModule homeModule = new HomeModule();
        ArrayList<TrackDetails> topT = homeModule.getToptracks();

        ObservableList<Track> data = FXCollections.observableArrayList();
        for (TrackDetails track : topT) {
            data.add(track.track);
        }

        addTable.setItems(data);
    }

    private void setResultsTitle(String s) {
        try {
            addTable.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksName(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                addTable.setItems(data);
            } else {
                this.setTopTracks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResultsArtist(String s) {
        try {
            addTable.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksAutor(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                addTable.setItems(data);
            } else {
                this.setTopTracks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
