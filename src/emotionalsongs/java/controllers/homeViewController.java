/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import javafx.util.Duration;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Models.HomeModule;
import Models.PlaylistModule;
import Session.ClientSession;
import Session.Globals;
import Session.WindowAppearance;
import Session.WindowStyle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jars.Playlist;
import jars.AlbumPreview;
import jars.Track;
import jars.TrackDetails;
import util.BackgroundTransition;
import util.TableViewManager;
import views.AlbumView;
import views.AllTrackView;
import views.ExplorePlaylistView;
import views.PlaylistBox;
/**
    *Controller per la view principale home
    * 
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

    */
public class homeViewController {
    /**elemento grafica */
    private double initialWidth;
    /**elemento grafica */
    public final static double PLAYLIST_CONTAINER_SPACING = 12;
    /**elemento grafica */
    public final static double ROOT_PADDING_LEFT_AND_RIGHT = 30;
    /**elemento grafica */
    private int MAX_ALBUM_DISPLAYED = 6;
    /**elemento grafica */
    private int num_columns = 0;
    /**elemento grafica */
    private int num_rows = 0;
    /**array playlist presenti nell'applicazione */
    private ArrayList<Playlist> plays = new ArrayList<Playlist>();
    /**array playlist dell'utente loggato presenti nell'applicazione */
    private ArrayList<Playlist> Myplays = new ArrayList<Playlist>();
    /**array playlist degli altri utenti presenti nell'applicazione */
    private ArrayList<Playlist> Othersplays = new ArrayList<Playlist>();
    /**elemento FXML */
    private GridPane albumBoxContainer = null;
    /**elemento FXML */
    private Label myLabel = new Label("login per vedere le tue playlists");
    /**elemento FXML */
    private Label MyotherLab = new Label("non ci sono playlists disponibili");
    /**elemento FXML */
    private Label otherLab = new Label("non ci sono playlists disponibili");
    /**elemento FXML */
    @FXML
    private HBox primaryShader;
    /**elemento FXML */
    @FXML
    private VBox contentContainer;
    /**elemento FXML */
    @FXML
    private VBox gridContainer;
    /**elemento FXML */
    @FXML
    private FlowPane playlistBoxContainer;
    /**elemento FXML */
    @FXML
    private VBox tableViewContainer;
    /**elemento FXML */
    @FXML
    private FlowPane othersPlaylistBoxContainer;
    /**modulo per accesso servizi server*/
    private HomeModule homeModule;
    /**Costruttore controller
     * @param initialWidth per resizing window
    */
    public homeViewController(double initialWidth) {
        this.initialWidth = initialWidth;
        homeModule = new HomeModule();
    }
    /**Inizializza file FXML */
    @FXML
    private void initialize() {
        WindowStyle.ResetColor();
        primaryShader.setBackground(WindowStyle.setInitialBackground());
        myLabel.getStyleClass().add("plays_label");
        otherLab.getStyleClass().add("plays_label");
        MyotherLab.getStyleClass().add("plays_label");
        MyotherLab.setPadding(new Insets(30,0,30,300));
        myLabel.setPadding(new Insets(30,0,30,300));
        otherLab.setPadding(new Insets(30,0,30,300));
        System.out.println("Width del center : " + (initialWidth - 16));
        initializeElements(initialWidth - 16);

        Platform.runLater(() -> {
            contentContainer.widthProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() > 0)
                    handleElements(newValue.doubleValue());
            });
        });
        WindowStyle.getColor().addListener((observable, oldValue, newValue) -> {
            smoothShaderTransition(oldValue,newValue);
        });
    }
    /**metodo per la grafica */
    private void smoothShaderTransition(Color oldColor,Color newColor) {
        BackgroundTransition transition = new BackgroundTransition(primaryShader, Duration.seconds(0.85), oldColor, newColor);
        transition.play();

    }
    /**metodo per la grafica resizing*/
    private void handleElements(double width) {  
        // PER SEC ALBUM
        ObservableList<Node> boxes = albumBoxContainer.getChildren();
        double boxCurrentWidth = 0;
        if (boxes.get(0) instanceof HBox) {
            boxCurrentWidth = ((HBox) boxes.get(0)).getWidth();
        }
        if (boxCurrentWidth != 0) {
            System.out.println("Current Width :" + boxCurrentWidth);
            System.out.println("MIN Width :" + (AlbumView.MIN_WIDTH + 5));

            // quando sono in 3 il min di una è 276px
            // window width 1285px con menu e altro.
            // il 0.25 : 321;
            // il 0.35 : 449;

            switch (num_columns) {
                case 1:
                    if (boxCurrentWidth > WindowAppearance.getWindowWidth() * 0.50) {
                        num_columns++;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    }
                    break;
                case 2:
                    if (boxCurrentWidth > WindowAppearance.getWindowWidth() * 0.37) {
                        num_columns++;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    } else if (boxCurrentWidth <= AlbumView.MIN_WIDTH + 25) {
                        num_columns--;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    }
                    break;
                case 3:
                    if (boxCurrentWidth < AlbumView.MIN_WIDTH + 25) {
                        num_columns--;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    }
                    break;
                default:
                    break;
            }

        }

    }
    /**setta la sezione playlist*/
    public void setPlaylist(double width) throws RemoteException, NotBoundException {
        PlaylistModule pm = new PlaylistModule();
        plays = pm.setPlaylists();
                
        // from input width we can calculate the number of playlist box we can add
        // calcolo lo spazio disponibile prendendo in considerazione lo spacing nel hbox
        // e il padding
        double availableSpace = width - (ROOT_PADDING_LEFT_AND_RIGHT);
        // calcolo il numero di playlist box che posso aggiungere
        // dimesione media plalist
        double mediumWidth = (((PlaylistBox.MaxWidth + PlaylistBox.MinWidth) / 2) + 15);

        int numberOfPlaylistBox = (int) (availableSpace / mediumWidth);
        System.out.println("numero di box :" + numberOfPlaylistBox);

        if(plays.isEmpty()){
            if(ClientSession.client.getUserid() == null){
                playlistBoxContainer.getChildren().add(myLabel);            
            }
            else{
                playlistBoxContainer.getChildren().add(MyotherLab);   
            }
            othersPlaylistBoxContainer.getChildren().add(otherLab);
        }
        else{
            if(ClientSession.client.getUserid() == null){
                playlistBoxContainer.getChildren().add(myLabel);
                for(Playlist p: plays){
                    PlaylistBox playlistBox = new PlaylistBox(p, false);
                    HBox.setHgrow(playlistBox, Priority.ALWAYS);
                    othersPlaylistBoxContainer.getChildren().add(playlistBox);
            }
                }
            else{
                for(Playlist p: plays){
                    if((p.getUser().equals(ClientSession.client.getUserid()))){
                        Myplays.add(p);
                    }
                    else{
                        Othersplays.add(p);
                    }
                }
                if(Myplays.isEmpty()){
                    playlistBoxContainer.getChildren().add(MyotherLab);
                }
                else{
                    for(Playlist p: Myplays){
                        PlaylistBox playlistBox = new PlaylistBox(p, false);
                        HBox.setHgrow(playlistBox, Priority.ALWAYS);
                        playlistBoxContainer.getChildren().add(playlistBox);
                    }
                }
                if(Othersplays.isEmpty()){
                    othersPlaylistBoxContainer.getChildren().add(otherLab);
                }
                else{
                    for(Playlist p: Othersplays){
                        PlaylistBox playlistBox = new PlaylistBox(p, false);
                        HBox.setHgrow(playlistBox, Priority.ALWAYS);
                        othersPlaylistBoxContainer.getChildren().add(playlistBox);
                    }
                }
            
            }
           
        }      
        
    }
    /**setta la sezione album */
    public void setAlbums(double width) {
        albumBoxContainer = new GridPane();
        albumBoxContainer.getStyleClass().add("albumBox_container");
        albumBoxContainer.setHgap(12); // Spaziatura orizzontale tra le colonne
        albumBoxContainer.setVgap(24); // Spaziatura verticale tra le righe
        final int MAX_PER_ROW = 3;
        double availableSpace = width - (ROOT_PADDING_LEFT_AND_RIGHT);
        double idealWidth = AlbumView.MIN_WIDTH + 50;
        num_columns = (int) (availableSpace / idealWidth);
        if (num_columns > MAX_PER_ROW) {
            num_columns = MAX_PER_ROW;
        }

        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
        fillGrid(num_columns, num_rows);
        gridContainer.getChildren().add(1, albumBoxContainer);

    }
     /**setta la sezione delle canzoni più popolari */
    private void setTopTracks() {
        System.out.println("sono in setTopTracks 1");
        TableViewManager topTracks = new TableViewManager(true, false);
        System.out.println("sono in setTopTracks 2");
        topTracks.setMinHeight(300);
        System.out.println("sono in setTopTracks 3");
 
        System.out.println("sono in setTopTracks 4");
        //ArrayList<TrackDetails> topT = null;
        ArrayList<TrackDetails> topT = homeModule.getToptracks();
        System.out.println("sono in setTopTracks 5");
        ObservableList<Track> data = FXCollections.observableArrayList();        
        System.out.println("sono in setTopTracks 6");
        for (TrackDetails track : topT) {
            data.add(track.track);

        }
        System.out.println("sono in setTopTracks 7");
        topTracks.setItems(data);
        System.out.println("sono in setTopTracks 8");
        System.out.println("Size of data in homeView : " + data.size());
        System.out.println("sono in setTopTracks 9");
        tableViewContainer.getChildren().add(topTracks);
        System.out.println("sono in setTopTracks 10");
        VBox.setVgrow(topTracks, Priority.ALWAYS);
    }
     /**metodo per la grafica*/
    private void fillGrid(int columns, int rows) {
        int width_percenatge = (int) (100 / columns);
        for (int i = 0; i < columns; i++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(width_percenatge);
            albumBoxContainer.getColumnConstraints().add(constraints);
        }
        ArrayList<AlbumPreview> topAlbums = homeModule.getTopAlbums();
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (index < topAlbums.size()) {
                    AlbumView album = new AlbumView(topAlbums.get(index)); 
                    albumBoxContainer.add(album, col, row);
                    index++;
                } else {
                    // Se abbiamo esaurito gli elementi nella lista, esci dai cicli
                    break;
                }
            }
        }
    }
     /**inizializza view con le sezioni*/
    private void initializeElements(double width) {
        try {
            setPlaylist(width);
        } catch (Exception e) {
            e.printStackTrace();}
        setTopTracks(); // per fare questo ci mette troppo tempo
        setAlbums(width);
    }
    /**apre view con le playlist presenti nell'app
     * @param e evento click javafx
    */
    public void explorePlaylist(MouseEvent e) {
        ExplorePlaylistView view = new ExplorePlaylistView();
        view.prefWidthProperty().bind(Globals.getRootFrame().widthProperty());
        view.prefHeightProperty().bind(Globals.getRootFrame().heightProperty());
        Globals.getRootFrame().setContent(view);
    }
    /**apre view con repository canzoni
     * @param e evento click javafx
    */
    public void openAllTrack(MouseEvent e) {
        AllTrackView view = new AllTrackView();
        view.prefWidthProperty().bind(Globals.getRootFrame().widthProperty());
        view.prefHeightProperty().bind(Globals.getRootFrame().heightProperty());
        Globals.getRootFrame().setContent(view);
    }

}
