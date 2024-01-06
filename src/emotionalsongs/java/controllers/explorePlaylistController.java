/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Models.PlaylistModule;
import Session.ClientSession;
import jars.Playlist;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import views.PlaylistBox;
/**
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
    * 
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0

    *classe creata e utilizzata per la view di esplora playlist
    */
public class explorePlaylistController {

    /**elemento FXML */
    @FXML
    private FlowPane MyplaylistBoxContainer;
    /**elemento FXML */
    @FXML
    private FlowPane OthersplaylistBoxContainer;
    /**elemento FXML */
    @FXML
    private Button allButton;
    /**elemento FXML */
    @FXML
    private VBox container;
    /**elemento FXML */
    @FXML
    private VBox mine;
    /**elemento FXML */
    @FXML
    private Button myButton;
    /**elemento FXML */
    @FXML
    private VBox others;
    /**elemento FXML */
    @FXML
    private Button othrsButton;
    /**elemento FXML */
    @FXML
    private Separator separator;
    /**elemento FXML */
    private Label myLab = new Label("login per vedere le tue playlists");
    /**elemento FXML */
    private Label MyotherLab = new Label("non ci sono playlists disponibili");
    /**elemento FXML */
    private Label otherLab = new Label("non ci sono playlists disponibili");
    /**array playlist presenti nell'applicazione */
    private ArrayList<Playlist> plays = new ArrayList<Playlist>();
    /**array playlist dell'utente loggato presenti nell'applicazione */
    private ArrayList<Playlist> Myplays = new ArrayList<Playlist>();
    /**array playlist degli altri utenti presenti nell'applicazione */
    private ArrayList<Playlist> Othersplays = new ArrayList<Playlist>();
   /**Inizializza file FXML */ 
    @FXML
    void initialize() throws RemoteException, NotBoundException{

        myLab.getStyleClass().add("plays_label");
        otherLab.getStyleClass().add("plays_label");
        MyotherLab.getStyleClass().add("plays_label");
        MyotherLab.setPadding(new Insets(30,0,30,300));
        myLab.setPadding(new Insets(30,0,30,300));
        otherLab.setPadding(new Insets(30,0,30,300));
        allButton.setDisable(true);
        
        PlaylistModule pm = new PlaylistModule();
        plays = pm.setPlaylists();
        initializeButtons();

    }
    /**inizializza view con elementi*/
    public void initializeButtons(){
        if(plays.isEmpty()){
            if(ClientSession.client.getUserid() == null){
                MyplaylistBoxContainer.getChildren().add(myLab);            
            }
            else{
                MyplaylistBoxContainer.getChildren().add(MyotherLab);       
            }
            OthersplaylistBoxContainer.getChildren().add(otherLab);
            myButton.setDisable(true);
            allButton.setDisable(true);
            othrsButton.setDisable(true);
            
        }
        else{
            if(ClientSession.client.getUserid() == null){
                update();
                }
            else{
                myButton.setDisable(false);
                othrsButton.setDisable(false);
                for(Playlist p: plays){
                    if((p.getUser().equals(ClientSession.client.getUserid()))){
                        Myplays.add(p);
                    }
                    else{
                        Othersplays.add(p);
                    }
                }
                if(Myplays.isEmpty()){
                    MyplaylistBoxContainer.getChildren().add(MyotherLab);
                }
                else{
                    for(Playlist p: Myplays){
                        PlaylistBox playlistBox = new PlaylistBox(p, false);
                        MyplaylistBoxContainer.getChildren().add(playlistBox);
                    }
                }
                if(Othersplays.isEmpty()){
                    OthersplaylistBoxContainer.getChildren().add(otherLab);
                }
                else{
                    for(Playlist p: Othersplays){
                        PlaylistBox playlistBox = new PlaylistBox(p, false);
                        OthersplaylistBoxContainer.getChildren().add(playlistBox);
                    }
                }
            
            }
           
        }
         myButton.setOnAction(event -> {
            
            if(othrsButton.isDisabled()){
                othrsButton.setDisable(false);
                container.getChildren().remove(others);
                container.getChildren().add(mine);
            }
            else
                {container.getChildren().removeAll(others,separator);
                }
            myButton.setDisable(true);
            allButton.setDisable(false);
        });
        othrsButton.setOnAction(event-> {
            if(myButton.isDisabled()){
                myButton.setDisable(false);
                container.getChildren().remove(mine);
                container.getChildren().add(others);
            }
            else
                {container.getChildren().removeAll(mine ,separator);}

            allButton.setDisable(false);
            othrsButton.setDisable(true);

        });
        allButton.setOnAction(event ->{
            if(myButton.isDisabled()){
                myButton.setDisable(false);
                container.getChildren().addAll(separator,others);
            }
            if(othrsButton.isDisabled()){
                othrsButton.setDisable(false);
                container.getChildren().add(2, mine);
                container.getChildren().add(3, separator);
            }
            allButton.setDisable(true);
        });

        
        }
    /**se utente non loggato*/
    public void update(){
        myButton.setDisable(true);
        allButton.setDisable(true);
        othrsButton.setDisable(true);
        MyplaylistBoxContainer.getChildren().add(myLab);
        for(Playlist p: plays){
                PlaylistBox playlistBox = new PlaylistBox(p, false);
                OthersplaylistBoxContainer.getChildren().add(playlistBox);
            }
        }
        
    }
