package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;
import util.FXMLLoaders;

public class homeViewController {

    @FXML
    private HBox primaryShader;

    @FXML
    private VBox contentContainer;

    @FXML
    private HBox playlistBoxContainer;

    @FXML
    private FlowPane albumBoxContainer;

    @FXML
    private TableView<?> tableViewTopClicked;

    @FXML
    private HBox othersPlaylistBoxContainer;

    private FXMLLoaders fxmlutil = new FXMLLoaders();

    @FXML
    private void initialize() {

        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        BackgroundFill backgroundFill = new BackgroundFill(
                ColorsManager.setGradient(Color.rgb(62, 32, 146, 0.6), Color.rgb(18, 18, 18)),
                cornerRadii,
                null);
        Background background = new Background(backgroundFill);
        primaryShader.setBackground(background);
        Platform.runLater(() -> {
            setView();
        });

        // coportamento desiderato (contoller figlio )

    }

    public void resizeHandler(double width) {
        //setHbox(playlistBoxContainer, width);
        setFlowPane(albumBoxContainer, width);
        //setHbox(othersPlaylistBoxContainer, width);
    }

    private void setView() {
        resizeHandler(contentContainer.getWidth());
    }

    private void setHbox(HBox hbox, double width) {
        // Numero di elementi grafici iniziali
        int initialNumElements = 4;

        // Rapporto larghezza/altezza degli elementi grafici (assumendo un valore)
        //double elementWidthToHeightRatio = 2.0;

        // Gap tra gli elementi all'interno dell'HBox (puoi modificarlo a tuo
        // piacimento)
        double gap = hbox.getSpacing();

        // Larghezza massima e minima di ciascun elemento (puoi modificarli a tuo
        // piacimento)
        double maxElementWidth = 0.8 * width; // Imposta la larghezza massima al 80% della larghezza del container
        double minElementWidth = 100;

        // Larghezza disponibile all'interno dell'HBox considerando il gap tra gli
        // elementi
        double availableWidth = width - hbox.getPadding().getLeft() - hbox.getPadding().getRight()
                - (initialNumElements - 1) * gap;

        // Calcola la larghezza ottimale per ciascun elemento considerando il gap tra
        // gli elementi
        double optimalElementWidth = Math.min(maxElementWidth, availableWidth / initialNumElements);

        // Calcola l'altezza ottimale per ciascun elemento in base al rapporto
        // width/height
        //double optimalElementHeight = optimalElementWidth / elementWidthToHeightRatio;

        // Verifica se la larghezza è inferiore alla larghezza minima per un elemento
        if (optimalElementWidth < minElementWidth) {
            // Riduci il numero di elementi visibili se la larghezza è troppo piccola
            int numVisibleElements = (int) (availableWidth / (minElementWidth + gap));
            final int finalnumVisibleElements = numVisibleElements;
            optimalElementWidth = (availableWidth - (numVisibleElements - 1) * gap) / numVisibleElements;
            // Rimuovi gli elementi in eccesso dal contenitore se necessario
            if (hbox.getChildren().size() > numVisibleElements) {

                Platform.runLater(() -> {
                    hbox.getChildren().remove(finalnumVisibleElements, hbox.getChildren().size());
                });

            }
        } else {
            // Verifica se è possibile aggiungere un elemento in più
            int numVisibleElements = (int) (availableWidth / (optimalElementWidth + gap));
            // Aggiungi elementi fino a raggiungere il numero massimo di elementi visibili
            Platform.runLater(() -> {
                while (hbox.getChildren().size() < numVisibleElements) {
                    // Crea e personalizza l'elemento grafico (VBox) qui
                    VBox playlsitBox = (VBox) fxmlutil.loadFXML("playlistBox.fxml");

                    hbox.getChildren().add(playlsitBox);

                }
            });
        }
        // Imposta la larghezza degli elementi nel VBox in percentuale rispetto alla
        // larghezza del VBox
        for (Node node : hbox.getChildren()) {
            if (node instanceof VBox) {
                double elementWidthPercentage = optimalElementWidth / width * 100;
                ((VBox) node).setStyle("-fx-pref-width: " + elementWidthPercentage + "%;");
            }
        }
    }

    private void setFlowPane(FlowPane flowpane, double width) {
        // calcoli

        // javaFX application thread
        Platform.runLater(() -> {

        });
    }

}
