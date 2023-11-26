package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.effect.BoxBlur;
import pkg.*;
import util.TableViewManager;
import WindowsLoader.ImagesWindow;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

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
    public static TableViewManager playlistTracks = new TableViewManager(false, false);
    public static TableViewManager findTracks = new TableViewManager(false, true);

    @FXML
    void initialize() {

        // crea un array list di test delle canzoni e inizializzalo con 5 canzoni
        /*ArrayList<Track> canzoni = new ArrayList<>();
        canzoni.add(new Track("1", "Song 1", 180, "Artist 1", "Album 1", "img0", "img1", "img2"));
        canzoni.add(new Track("2", "Song 2", 180, "Artist 2", "Album 2", "img3", "img4", "img5"));
        canzoni.add(new Track("3", "Song 3", 180, "Artist 3", "Album 3", "img6", "img7", "img8"));
        canzoni.add(new Track("4", "Song 4", 180, "Artist 4", "Album 4", "img9", "img10", "img11"));
        canzoni.add(new Track("5", "Song 5", 180, "Artist 5", "Album 5", "img12", "img13", "img14"));*/

        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements(width);
        });
    }

    private void initializeElements(double width) {
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
        tableContainer.getChildren().add(findTracks);
        /*
        try {
        Registry r = LocateRegistry.getRegistry(ServerInterface.PORT);
        ServerInterface si = (ServerInterface) r.lookup("SERVER");
        ArrayList<String> ar = si.getTrackId("Ricordami");
        ArrayList<Track> res = si.getAllTrackInformation(ar, 0, ar.size());
        ObservableList<Track> o = FXCollections.observableArrayList(res);
        findTracks.setItems(o);
        } catch (Exception e) {
            System.out.println(e);
        }
        */
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

    public void registraPlaylist(MouseEvent e) {
        System.out.println("creata");
    }
}
