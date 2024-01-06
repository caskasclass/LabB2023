/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Models.AllTrackModule;
import Models.HomeModule;
import Models.PlaylistModule;
import Session.ClientSession;
import Session.Globals;
import Threads.ResizeHandler;
import jars.*;
import util.BackgroundTransition;
import util.ColorsManager;
import util.TableViewManager;
import views.HomeView;
import views.PlaylistView;
/**
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
    * 
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0

    *classe creata e utilizzata per la view della playlist corrente
    */
public class playlistViewController {

    
    /**elemento FXML */
    @FXML
    private TextField cerca;
    /**elemento FXML */
    @FXML
    private HBox buttonCont;
    /**elemento FXML */
    @FXML
    private VBox container;
    /**elemento FXML */
    @FXML
    private Button deleteButton;
    /**elemento FXML */
    @FXML
    private Button editButton;
    /**elemento FXML */
    @FXML
    private Label owner;
    /**elemento FXML */
    @FXML
    private ImageView playlistImage;
    /**elemento FXML */
    @FXML
    private HBox gradientBackground;
    /**elemento FXML */
    @FXML
    private TextField playlistName;
    /**elemento FXML */
    @FXML
    private VBox editcontainter;
    /**elemento FXML */
    @FXML
    private VBox tableContainer;
    /**oggetto playlist */
    private Playlist p = null;
    /**elemento playlist */
    public String title = null;
    /**elemento playlist */
    public String user = null;
    /**elemento playlist */
    private String image = null;
    /**lista per gestione tabelle */
    private ArrayList<String> p_strings = new ArrayList<String>();
    /**lista per gestione tabelle */
    private ArrayList<String> addString = new ArrayList<String>();
    /**lista per gestione tabelle */
    private ArrayList<Track> p_tracks = new ArrayList<Track>();
    /**lista per gestione tabelle */
    private ArrayList<Track> addTracks = new ArrayList<Track>();
    /**tabella playlist corrente */
    private TableViewManager mainTable = new TableViewManager(true, false);
    /**tabella cerca nuove canzoni */
    private TableViewManager addTable = new TableViewManager(false, true);
    /**tabella editing playlist */
    private TableViewManager editTable = new TableViewManager(false, false);
    /**elemento task asincroni */
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    /**elemento delay ricerca */
    Timer timer = new Timer();
    /**elemento delay ricerca */
    final int DELAY_TIME = 2000;

    /**Inizializza file FXML */
    @FXML
    void initialize() {

        playlistImage.setDisable(true);
        playlistName.setEditable(false);
        playlistName.setStyle("-fx-background-color: transparent");
        setPlaylist();
        Color color = ColorsManager.getDominantColor(new Image(p.getImage()));
        gradientBackground.setBackground(BackgroundTransition.gettLinearGradient(color));
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(30, 30, 30), 12, 0.16, 0, 0);
        shadow.setWidth(45);
        shadow.setHeight(45);
        playlistImage.setEffect(shadow);
        playlistImage.setImage(new Image(image));
        Color tmpColor  =  color.deriveColor(0, 1, 1, 0);
        BackgroundTransition.setHeaderGraphics(tmpColor);
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
                timer.cancel(); // Annulla il timer precedente all'inizio di un nuovo evento di pressione del
                                // tasto
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Codice da eseguire dopo il ritardo di inattività (2 secondi)
                        setResultsTitleAsync(cerca.getText());
                    }

                }, DELAY_TIME);
            } else {
                timer.cancel(); // Annulla il timer se viene premuto il tasto "ENTER"
                setResultsArtistAsync(cerca.getText());
            }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
    /**Inizializza elementi view */
    private void initializeElements() throws RemoteException {

        if (ClientSession.client.getUserid() == null || !(ClientSession.client.getUserid().equals(user))) {
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        } else {
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
    /**setta playlist corrente */
    private void setPlaylist() {
        try {
            // modificare getPlaylist
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
    /**metodo per salvare le nuove canzoni da aggiungere alla playlist
     * @param editTable
    */
    private void newTracks(TableViewManager editTable) throws RemoteException, NotBoundException {
        ArrayList<Track> ar = new ArrayList<Track>(editTable.getItems());
        ArrayList<String> track_ids = new ArrayList<String>();
        p.setTrackList(track_ids);
        for (Track t : ar) {
            track_ids.add(t.getTrack_id());
        }
        ArrayList<String> new_tracks = new ArrayList<String>();
        for (String track : track_ids) {
            if (!(p_strings.contains(track))) {
                new_tracks.add(track);
            }
        }
        PlaylistModule pm = new PlaylistModule();
        pm.createPlaylist(new Playlist(title, new_tracks, image, user));
        for (String track : p_strings) {
            if (!(track_ids.contains(track))) {
                pm.deleteTrack(p, track);
            }
        }

    }

    /**setta top canzoni asincrono*/
    private void setTopTracksAsync() {
        executorService.submit(() -> {
            try {
                setTopTracks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**cerca per titolo asincrono
     * @param s stringa ricerca
    */
    private void setResultsTitleAsync(String s) {
        executorService.submit(() -> {
            try {
                setResultsTitle(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**cerca per artista e anno asincrono
     * @param s stringa ricerca
    */
    private void setResultsArtistAsync(String s) {
        executorService.submit(() -> {
            try {
                setResultsArtist(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**setta top canzoni */
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
    /**cerca per titolo
     * @param s stringa ricerca
    */
    private void setResultsTitle(String s) {
        try {
            addTable.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksName(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                addTable.setItems(data);
            } else {
                this.setTopTracksAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**cerca per artista e anno
     * @param s stringa ricerca
    */
    private void setResultsArtist(String s) {
        try {
            addTable.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksAutor(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                addTable.setItems(data);
            } else {
                this.setTopTracksAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
