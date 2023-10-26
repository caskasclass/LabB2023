package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tmp.Canzone;
import util.TableViewManager;

public class playlistViewController {
    @FXML
    private TextField cerca;

     @FXML
    private HBox buttonCont;

    @FXML
    private VBox container;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    @FXML
    private TextField playlistName;

    @FXML
    private VBox editcontainter;

    @FXML
    private VBox tableContainer;


    @FXML
    void initialize(){

        editcontainter.setVisible(false);
        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements();
        });

    }

     private void initializeElements() {
        TableViewManager mainTable = new TableViewManager(true, false);
        TableViewManager addTable = new TableViewManager(false, true);
        TableViewManager editTable = new TableViewManager(false, false);

        mainTable.setMinHeight(400);
        addTable.setMinHeight(400);
        addTable.setVisible(false);
        editTable.setMinHeight(400);

        tableContainer.getChildren().add(0,mainTable);

        ObservableList<Canzone> data = FXCollections.observableArrayList(
                new Canzone("John", "Doe"),
                new Canzone("Jane", "Smith"),
                new Canzone("Alice", "Johnson"),
                new Canzone("John", "Doe"),
                new Canzone("Jane", "Smith"));
        ObservableList<Canzone> data2 = FXCollections.observableArrayList(
                new Canzone("John", "Doe"),
                new Canzone("Jane", "Smith"),
                new Canzone("Alice", "Johnson"),
                new Canzone("John", "Doe"),
                new Canzone("Jane", "Smith"));        
        mainTable.setItems(data);
        editTable.setItems(data);
        addTable.setItems(data2);
        cerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                addTable.setVisible(false); // Hide TableView when the TextField is empty
            } else {
                addTable.setVisible(true); // Show TableView when there is text in the TextField
            }
        });
        editButton.setOnMouseClicked(event ->{ 
            editButton.setDisable(true);
            editcontainter.setVisible(true); 
            tableContainer.getChildren().remove(0);
            tableContainer.getChildren().add(0,editTable);
            Button button = new Button("Done");
            button.getStyleClass().add("button_cp");
            buttonCont.getChildren().add(1, button);
            button.setOnMouseClicked(param -> {
                editcontainter.setVisible(false); 
                buttonCont.getChildren().remove(button); 
                editButton.setDisable(false);
                tableContainer.getChildren().remove(0);
                tableContainer.getChildren().add(0,mainTable);
                tableContainer.getChildren().remove(addTable);} );
            tableContainer.getChildren().add(2,addTable);});
            

        deleteButton.setOnMouseClicked(event -> editcontainter.setVisible(false)      
            );

    }
}
