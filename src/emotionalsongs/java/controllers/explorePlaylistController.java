package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    void initialize(){
        for(int i=0; i<8; i++){
            PlaylistBox playlistbox = new PlaylistBox(i+1,false);
            MyplaylistBoxContainer.getChildren().add(playlistbox);
        }

        for(int i=0; i<8; i++){
            PlaylistBox playlistbox = new PlaylistBox(i+1,false);
            OthersplaylistBoxContainer.getChildren().add(playlistbox);
        }
        allButton.setDisable(true);
        initializeButtons();

    }

    private void initializeButtons(){
        myButton.setOnAction(event -> {
            if(othrsButton.isDisabled()){
                othrsButton.setDisable(false);
                container.getChildren().remove(others);
                container.getChildren().add(mine);
            }
            else
                {container.getChildren().removeAll(others,separator);}
            allButton.setDisable(false);
            myButton.setDisable(true);
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
}
