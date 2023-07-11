package controllers;

import Session.WindowAppearance;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class homeWindowController {

    private double MinWidth = WindowAppearance.getWindowWidth() * 0.23;
    private double MaxWidth = WindowAppearance.getWindowWidth() * 0.30;


    @FXML
    private BorderPane rootPane;

    @FXML
    private VBox rootMenu;

    @FXML
    private ScrollPane rootScrollPane;

    @FXML
    private void initialize() {
        // Crea e configura il label
        System.out.println("homeWindowController");
        rootMenu.setMinWidth(MinWidth);// 0.23 è quella giusta
        rootMenu.setMaxWidth(MaxWidth);// 0.30 è ok, anche troppo

        // listener per la width
        rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            animateMenuWidth(newWidth.doubleValue());
        });
        // Aggiungi un listener per l'evento di trascinamento del bordo destro della
        // VBox
    }

    private void animateMenuWidth(double newWidth) {
        double menuWidth = Math.min(newWidth * 0.23, MaxWidth); // Calcolo della nuova larghezza

        rootMenu.setPrefWidth(menuWidth);

    }


}
