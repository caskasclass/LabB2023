package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pkg.Track;

public class allTrackViewController {

    @FXML
    private TableView<Track> table;
    
    @FXML
    private TableColumn<Track, String> album;

    @FXML
    private TableColumn<Track, String> autore;

    @FXML
    private TableColumn<Track, Integer> data;

    @FXML
    private TableColumn<Track, Integer> durata;

    @FXML
    private TableColumn<Track, String> titolo;
    
    ObservableList<Track> list ;
    
    public allTrackViewController(ArrayList<Track> ar) {
        this.list = FXCollections.observableArrayList(ar);
    }

    public void inizialize(URL url, ResourceBundle rb) {
        this.titolo.setCellValueFactory(new PropertyValueFactory<Track, String>("titolo"));
        this.durata.setCellValueFactory(new PropertyValueFactory<Track, Integer>("durata"));
        this.data.setCellValueFactory(new PropertyValueFactory<Track, Integer>("data"));
        this.autore.setCellValueFactory(new PropertyValueFactory<Track, String>("autore"));
        this.album.setCellValueFactory(new PropertyValueFactory<Track, String>("album"));
        table.setItems(list);
    }
}
