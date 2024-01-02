package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import Models.TrackModule;
import Session.ClientSession;
import jars.ChartData;
import jars.EmotionEvaluation;
import jars.Track;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.BackgroundTransition;
import util.ColorsManager;
import util.EmotionBox;

public class canzoneViewController {

    Track track = null;

    @FXML
    private FlowPane containerEvaluation;

    @FXML
    private VBox gradientBackground;

    @FXML
    private ImageView immagineCanzone;

    @FXML
    private Label infoCanzoni;

    @FXML
    private Label nomeAlbum;

    @FXML
    private Label nomeCanzone;

    @FXML
    private VBox rootCanzoneview;

    @FXML
    private Label esitoLabel;

    @FXML
    private Button saveButton;
    // create a map of emotions and their values

    HashMap<String, Integer> emotions = new HashMap<>();
    ArrayList<ChartData> allEmotions = new ArrayList<>();

    Boolean isRated = false;
    TrackModule tm = new TrackModule();

    @FXML
    private void initialize() {

        if (ClientSession.client.getUserid() != null) {
            if (emotionsRated()) {
                isRated = true;
                saveButton.setDisable(true);
                saveButton.setVisible(false);
                emotions = tm.getMyEmotions(track);
            } else {
                initMap();
            }
            createEvalBoxes();
        } else {
            saveButton.setVisible(false);
            containerEvaluation.getChildren().add(new Label("Effettua il login per dirci come ti senti"));
        }
        allEmotions = tm.getAllEmotions(track);
        rootCanzoneview.getChildren().add(createChart());
        Color color = ColorsManager.getDominantColor(new Image(track.getAlbum_img1S()));
        gradientBackground.setBackground(BackgroundTransition.gettLinearGradient(color));
        Platform.runLater(() -> {
            nomeAlbum.setText(track.getAlbum_name());
            nomeCanzone.setText(track.getName());
            infoCanzoni.setText(track.getArtist_name());
            track.getAlbum_img2S();
            immagineCanzone.setImage(new Image(track.getAlbum_img1S()));
            DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(30, 30, 30), 12, 0.16, 0, 0);
            shadow.setWidth(45);
            shadow.setHeight(45);
            immagineCanzone.setEffect(shadow);
        });

    }

    private void initMap() {
        emotions.put("Amazement", 0);
        emotions.put("Solemnity", 0);
        emotions.put("Tenderness", 0);
        emotions.put("Nostalgia", 0);
        emotions.put("Calmness", 0);
        emotions.put("Power", 0);
        emotions.put("Joy", 0);
        emotions.put("Tension", 0);
        emotions.put("Sadness", 0);
    }

    public void setCanzone(Track track) {
        this.track = track;
    }

    public void createEvalBoxes() {
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(64, 63, 63), 12, 0.16, 0, 0);
        shadow.setWidth(25);
        shadow.setHeight(25);
        for (String string : emotions.keySet()) {
            EmotionBox emotionBox = new EmotionBox(string, isRated, emotions.get(string));
            containerEvaluation.getChildren().add(emotionBox);
            if (isRated) {
                emotionBox.setDisable(true);
            }

        }
    }

    private boolean emotionsRated() {
        TrackModule tm = new TrackModule();
        if (tm.checkIfRated(track.getTrack_id(), ClientSession.client.getUserid())) {
            System.out.println("Hai già votato questa canzone");
            return true;

        } else {
            System.out.println("Non hai ancora votato questa canzone");
            return false;
        }

    }

    private void getVotes() {
        for (Node node : containerEvaluation.getChildren()) {
            if (node instanceof EmotionBox) {
                EmotionBox emotionBox = (EmotionBox) node;
                int emotionRating = emotionBox.getVote();
                String emotionName = emotionBox.getEmotionName(); // Supponiamo che ci sia un metodo per ottenere il
                                                                  // nome dell'emozione
                emotions.put(emotionName, emotionRating);
            }
        }
    }

    public void saveEmotions(ActionEvent event) {
        getVotes();
        if (anyZero()) {
            esitoLabel.setTextFill(Color.RED);
            esitoLabel.setText("Devi votare tutte le emozioni");
            return;
        }
        System.out.println(emotions);
        saveButton.setDisable(true);
        EmotionEvaluation ee = new EmotionEvaluation(emotions, ClientSession.client.getUserid(), track.getTrack_id(),
                "Silence is golden");
        tm.insertEmotion(ee);
        Platform.runLater(()->{
            rootCanzoneview.getChildren().remove(3);
            allEmotions = tm.getAllEmotions(track);
            rootCanzoneview.getChildren().add(createChart());
        });
        
        System.out.println("Emozioni salvate");
    }

    private boolean anyZero() {
        for (String string : emotions.keySet()) {
            if (emotions.get(string) == 0) {
                return true;
            }
        }
        return false;
    }

    private VBox createChart() {
        VBox chartBox = new VBox();
        chartBox.setAlignment(Pos.CENTER);
        System.out.println("allEmotions size: " + allEmotions.size());
        // Creazione delle serie dati per il grafico a barre
        if (!allEmotions.isEmpty()) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();


            // Aggiungi i dati dalla lista allEmotions alla serie
            for (ChartData data : allEmotions) {
                series.getData().add(new XYChart.Data<>(data.getEmotionName(), data.getAverageRating()));
            }

            // Creazione del grafico a barre
            NumberAxis yAxis = new NumberAxis();
            yAxis.setTickUnit(0.2);
            yAxis.setTickLabelFill(Color.WHITE);
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setTickLabelFill(Color.WHITE);
            xAxis.setTickLabelFont(new Font(18));
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setMinHeight(500);
            barChart.setTitle("Media delle valutazioni per emozione");
            barChart.setStyle(null);

            // Aggiungi la serie al grafico a barre
            barChart.getData().add(series);
            barChart.setLegendVisible(false);

            Label label = new Label("Totale voti: " + allEmotions.get(0).getTotalRatings());

            chartBox.getChildren().addAll(barChart, label);
        } else {
            Label label = new Label("Non ci sono ancora voti per questa canzone");
            ImageView fallbackImage = new ImageView(
                    new Image(getClass().getResource("/imgs/noDataFound.png").toExternalForm()));

            // Imposta la proprietà di ridimensionamento per far sì che l'immagine si adatti
            // alla finestra
            fallbackImage.setPreserveRatio(true);
            fallbackImage.setFitWidth(300);
            // finestra
            chartBox.getChildren().addAll(label, fallbackImage);
        }
        return chartBox;
    }



}
