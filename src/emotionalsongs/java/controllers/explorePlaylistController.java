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

public class explorePlaylistController {


    @FXML
    private FlowPane MyplaylistBoxContainer;

    @FXML
    private FlowPane OthersplaylistBoxContainer;

    @FXML
    private Button allButton;

    @FXML
    private VBox container;

    @FXML
    private VBox mine;

    @FXML
    private Button myButton;

    @FXML
    private VBox others;

    @FXML
    private Button othrsButton;

    @FXML
    private Separator separator;

    private Label myLab = new Label("login to see your playlists");
    private Label otherLab = new Label("no playlists available");

    private ArrayList<Playlist> plays = new ArrayList<Playlist>();
    private ArrayList<Playlist> Myplays = new ArrayList<Playlist>();
    private ArrayList<Playlist> Othersplays = new ArrayList<Playlist>();

    @FXML
    void initialize() throws RemoteException, NotBoundException{

        myLab.getStyleClass().add("plays_label");
        otherLab.getStyleClass().add("plays_label");
        myLab.setPadding(new Insets(30,0,30,300));
        otherLab.setPadding(new Insets(30,0,30,300));
        allButton.setDisable(true);
        
        PlaylistModule pm = new PlaylistModule();
        plays = pm.setPlaylists();
        initializeButtons();

    }

    public void initializeButtons(){
        if(plays.isEmpty()){
            if(ClientSession.client.getUserid() == null){
                MyplaylistBoxContainer.getChildren().add(myLab);            
            }
            else{
                MyplaylistBoxContainer.getChildren().add(otherLab);   
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
                    if(p.getUser().equals(ClientSession.client.getUserid())){
                        Myplays.add(p);
                    }                    
                }
                if(Myplays.isEmpty()){
                    MyplaylistBoxContainer.getChildren().add(otherLab);
                }
                else{
                    for(Playlist p: Myplays){
                        PlaylistBox playlistBox = new PlaylistBox(p, false);
                        MyplaylistBoxContainer.getChildren().add(playlistBox);
                    }
                }
                for(Playlist p: plays){
                    if(!(p.getUser().equals(ClientSession.client.getUserid()))){
                        Othersplays.add(p);
                    }                    
                }
                if(Othersplays.isEmpty()){
                    OthersplaylistBoxContainer.getChildren().add(otherLab);
                }
                else{
                    for(Playlist p: Othersplays){
                        PlaylistBox playlistBox = new PlaylistBox(p, false);
                        MyplaylistBoxContainer.getChildren().add(playlistBox);
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
