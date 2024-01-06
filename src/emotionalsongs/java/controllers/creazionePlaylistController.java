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
import javafx.stage.Stage;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *controller per la view di creazione della playlist
 */
public class creazionePlaylistController {
    /**elemento FXML */
    @FXML
    private TextField cerca;
    /**elemento FXML */
    @FXML
    private VBox container;
    /**elemento FXML */
    @FXML
    private Button createButton;
    /**elemento FXML */
    @FXML
    private Label owner;
    /**elemento FXML */
    @FXML
    private ImageView playlistImage;
    /**elemento FXML */
    @FXML
    private TextField playlistName;
    /**elemento FXML */
    @FXML
    private VBox tableContainer;
    /**tabella contenente canzoni della playlist da creare */
    private TableViewManager playlistTracks = new TableViewManager(false, false);
    /**tabella per cercare le canzoni della playlist da creare */
    private TableViewManager findTracks = new TableViewManager(false, true);
    /**elemento task asincroni */
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    /**elemento delay ricerca */
    Timer timer = new Timer();
    /**elemento delay ricerca */
    final int DELAY_TIME = 2000;
    /**Inizializza file FXML */
    @FXML
    void initialize() {

        playlistName.setStyle("-fx-background-color: transparent");
        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements(width);
        });
        try {
            setTopTracksAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**inizializza elementi view */
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

        
        tableContainer.getChildren().add(findTracks);
    }
    /**apre finestra con immagini selezionabili
     * @param e evento click javafx
    */
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
    /**registra la nuova playlist nell'applicazione
     * @param e evento click javafx
    */
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
    /**setta top canzoni per popolarità con metodo asincrono*/
    private void setTopTracksAsync() {
        executorService.submit(() -> {
            try {
                setTopTracks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**cerca per artista e anno con metodo asincrono
     * @param s stringa di ricerca inserita dall'utente 
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
    /**cerca per artista e anno con metodo asincrono
     * @param s stringa di ricerca inserita dall'utente 
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

   /**setta top canzoni per popolarità */
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
    /**ricerca per titolo
     * @param s stringa di ricerca inserita dall'utente 
    */
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
    /**ricerca per artista e anno
     * @param s stringa di ricerca inserita dall'utente 
    */
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
