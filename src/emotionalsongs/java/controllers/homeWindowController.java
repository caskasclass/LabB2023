package controllers;

import Session.WindowAppearance;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class homeWindowController {

    private double MinWidth = WindowAppearance.getWindowWidth() * 0.23;
    private double MaxWidth = WindowAppearance.getWindowWidth() * 0.30;


    @FXML
    private BorderPane rootPane;

    @FXML
    private VBox rootMenu;

    @FXML
    private ScrollPane centerScrollPane;

    @FXML
    private StackPane centerStackPane;

    //@FXML 
    //private GridPane centerGrid;

    @FXML
    private Button homeButton;

    @FXML
    private Button searchButton;


    @FXML
    private void initialize() {
        // Crea e configura il emnu 
        System.out.println("homeWindowController");
        rootMenu.setMinWidth(MinWidth);// 0.23 è quella giusta
        rootMenu.setMaxWidth(MaxWidth);// 0.30 è ok, anche troppo

        // listener per la width
        rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            animateMenuWidth(newWidth.doubleValue());
        });

        centerScrollPane.setFocusTraversable(false);

        //metto insets tra le region di borderPane 
        //pagherei per tradure sta cosa in css 
        BorderPane.setMargin(rootMenu, new Insets(0, 7, 0, 0));
        BorderPane.setMargin(centerStackPane, new Insets(0, 0, 0, 7));


    }

    private void animateMenuWidth(double newWidth) {
        double menuWidth = Math.min(newWidth * 0.23, MaxWidth); // Calcolo della nuova larghezza

        rootMenu.setPrefWidth(menuWidth);

    }


}
