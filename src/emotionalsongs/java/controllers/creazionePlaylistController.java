package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import util.TableViewManager;

import java.net.URL;
import java.util.ArrayList;

import org.w3c.dom.Node;

import WindowsLoader.ImagesWindow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tmp.Canzone;
import javafx.scene.effect.BoxBlur;


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
    public static TableViewManager playlistTracks = new TableViewManager(false,false); 
    public static TableViewManager findTracks = new TableViewManager(false,true);

    @FXML
    void initialize() {

        
        //crea una array list di test delle canzoni e inizializzalo con 5 caznoni
        ArrayList<Canzone> canzoni = new ArrayList<Canzone>();
        canzoni.add(new Canzone("Canzone1","Album1"));
        canzoni.add(new Canzone("Canzone2","Album2"));
        canzoni.add(new Canzone("Canzone3","Album3"));
        canzoni.add(new Canzone("Canzone4","Album4"));
        canzoni.add(new Canzone("Canzone5","Album5"));


        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements(width);
        });
    }

    private void initializeElements(double width) {
        
        playlistTracks.setMinHeight(300);
        playlistTracks.setVisible(false);
        tableContainer.getChildren().add(0,playlistTracks);

        
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
        ObservableList<Canzone> data = FXCollections.observableArrayList(
                new Canzone("John", "Doe"),
                new Canzone("Jane", "Smith"),
                new Canzone("Alice", "Johnson"),
                new Canzone("John", "Doe"),
                new Canzone("Jane", "Smith"));
        findTracks.setItems(data);


    }


    public void openImages(MouseEvent e){
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




    
}
