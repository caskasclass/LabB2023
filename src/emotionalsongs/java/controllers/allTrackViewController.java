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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.input.KeyCode;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *Classe creata e utilizzata per la view della repository canzoni
 */
public class AllTrackViewController {
    /**elemento FXML */
    @FXML
    private VBox contentContainer;
    /**elemento FXML */
    @FXML
    private VBox tableViewContainer;
    /**elemento FXML */
    @FXML
    private Label leftLabel;
    /**elemento FXML */
    @FXML
    private TextField searchBar;
    /**elemento FXML */
    @FXML
    private Pane MainFrame;
    /**elemento FXML */
    @FXML
    private HBox playlistBoxContainer;
    /**elemento FXML */
    @FXML
    private Background primaryShader;
    /**elemento task asincroni */
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    /**elemento delay ricerca */
    Timer timer = new Timer();
    /**elemento delay ricerca */
    final int DELAY_TIME = 2000;
    /**Inizializza file FXML */
    @FXML
    private void initialize() {
        WindowStyle.ResetColor();
        primaryShader = new Background(new BackgroundFill(Color.BLUE, null, null));
        MainFrame.setBackground(WindowStyle.setInitialBackground());

        setTopTracksAsync();
        cercaBranoMusicale();

    }
    /**Metodo per la ricerca dei brani musicali */
    private void cercaBranoMusicale(){
        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.ENTER) {
                timer.cancel(); // Annulla il timer precedente all'inizio di un nuovo evento di pressione del
                                // tasto
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Codice da eseguire dopo il ritardo di inattività (2 secondi)
                        setResultsTitleAsync(searchBar.getText());
                    }

                }, DELAY_TIME);
            } else {
                timer.cancel(); // Annulla il timer se viene premuto il tasto "ENTER"
                setResultsArtistAsync(searchBar.getText());
            }
        });
    }

    /**setta le top tracks asincrono */
    private void setTopTracksAsync() {
        executorService.submit(() -> {
            try {
                setTopTracks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**setta risultato ricerca asincrono
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

    /**setta risultato ricerca asincrono 
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

    /**cerca le top tracks */
    private void setTopTracks() throws RemoteException {
        TableViewManager topTracks = new TableViewManager(true, false);
        topTracks.setPrefHeight(980);
        HomeModule homeModule = new HomeModule();
        ArrayList<TrackDetails> topT = homeModule.getToptracks();

        ObservableList<Track> data = FXCollections.observableArrayList();
        for (TrackDetails track : topT) {
            data.add(track.track);
        }

        Platform.runLater(() -> {
            topTracks.setItems(data);
            tableViewContainer.getChildren().clear();
            tableViewContainer.getChildren().add(topTracks);
            VBox.setVgrow(topTracks, Priority.ALWAYS);
        });
    }

    /**ricerca per titolo 
     * @param s stringa ricerca
    */
    private void setResultsTitle(String s) {
        try {
            TableViewManager res = new TableViewManager(true, false);
            res.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksName(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                Platform.runLater(() -> {
                    res.setItems(data);
                    tableViewContainer.getChildren().clear();
                    tableViewContainer.getChildren().add(res);
                });
            } else {
                setTopTracksAsync();
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
            TableViewManager res = new TableViewManager(true, false);
            res.setPrefHeight(980);
            AllTrackModule at = new AllTrackModule();
            ArrayList<Track> tres = at.searchTracksAutor(s);
            if (tres != null) {
                ObservableList<Track> data = FXCollections.observableArrayList(tres);
                Platform.runLater(() -> {
                    res.setItems(data);
                    tableViewContainer.getChildren().clear();
                    tableViewContainer.getChildren().add(res);
                });
            } else {
                setTopTracksAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
