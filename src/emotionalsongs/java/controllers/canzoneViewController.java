/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import Models.TrackModule;
import Session.ClientSession;
import Session.Globals;
import jars.ChartData;
import jars.CommentSection;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.BackgroundTransition;
import util.ColorsManager;
import util.CommentVBox;
import util.EmotionBox;
/**
 * Controller per la visualizzazione della canzone selezionata
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 *
 */
public class canzoneViewController {
    /**canzone corrente visualizzata*/
    Track track = null;
    /**elemento FXML */
    @FXML
    private FlowPane containerEvaluation;
    /**elemento FXML */
    @FXML
    private VBox gradientBackground;
    /**elemento FXML */
    @FXML
    private ImageView immagineCanzone;
    /**elemento FXML */
    @FXML
    private Label infoCanzoni;
    /**elemento FXML */
    @FXML
    private Label nomeAlbum;
    /**elemento FXML */
    @FXML
    private Label nomeCanzone;
    /**elemento FXML */
    @FXML
    private VBox rootCanzoneview;
    /**elemento FXML */
    @FXML
    private TabPane commentSection;
    /**elemento FXML */
    @FXML
    private Label esitoLabel;
    /**elemento FXML */
    @FXML
    private Button saveButton;

    /**mappa di emozioni con valutazioni */
    HashMap<String, Integer> emotions = new HashMap<>();
    /**lista dati emozioni*/
    ArrayList<ChartData> allEmotions = new ArrayList<>();
    /**mappa di commenti */
    HashMap<String, String> emotionComments = new HashMap<>();
    /**lista di commenti */
    ArrayList<CommentSection> comments = new ArrayList<CommentSection>();
    /**true se la canzone ha già una valutazione*/
    Boolean isRated = false;
    /**modulo per accesso servizi server*/
    TrackModule tm = new TrackModule();
    /**Inizializza file FXML */
    @FXML
    private void initialize() {
        Globals.getRootFrame().setVvalue(0.0);
        initMap();
        if (ClientSession.client.getUserid() != null) {
            if (emotionsRated()) {
                isRated = true;
                saveButton.setDisable(true);
                saveButton.setVisible(false);
                EmotionEvaluation ee = tm.getMyEmotions(track);
                emotions = ee.getEmozione();
                emotionComments = ee.getNote();
            }
            createEvalBoxes();
        } else {
            saveButton.setVisible(false);
            containerEvaluation.getChildren().add(new Label("Effettua il login per dirci come ti senti"));
        }
        allEmotions = tm.getAllEmotions(track);
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
            Color tmpColor = color.deriveColor(0, 1, 1, 0);
            BackgroundTransition.setHeaderGraphics(tmpColor);
        });
        unitializeCommentSection();
        rootCanzoneview.getChildren().add(createChart());

    }
    /**gestione grafica commenti*/
    private void unitializeCommentSection() {
        comments = tm.getAllComments(track);
        commentSection.getStyleClass().add("commentTab");
        // for each emotion create a tab and add it to the tabpane
        for (String string : emotions.keySet()) {
            Tab tab = new Tab(string);
            VBox tabContent = new VBox();
            tabContent.getStyleClass().add("commentSection");
            tabContent.setAlignment(Pos.TOP_LEFT);
            tabContent.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            tabContent.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
            for (CommentSection cs : comments) {
                if (cs.getEmotion().equals(string)) {
                    if (!cs.getComment().equals("Silence is golden")) {
                        CommentVBox commentVBox = new CommentVBox(cs.getComment(), cs.getUserid());
                        tabContent.getChildren().add(commentVBox);
                    }

                }

            }
            if (tabContent.getChildren().isEmpty()) {
                CommentVBox commentVBox = new CommentVBox("Non ci sono i commenti per ora ", "System");
                tabContent.getChildren().add(commentVBox);
            }
            tab.setContent(tabContent);
            commentSection.getTabs().add(tab);

        }
    }
    /**inizializza mappa emozioni*/
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
    /**Setta canzone selezionata
     * @param track canzone corrente
    */
    public void setCanzone(Track track) {
        this.track = track;
    }
    /**crea i box per le valutazioni*/
    public void createEvalBoxes() {
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(64, 63, 63), 12, 0.16, 0, 0);
        shadow.setWidth(25);
        shadow.setHeight(25);
        for (String string : emotions.keySet()) {
            EmotionBox emotionBox = new EmotionBox(string, isRated, emotions.get(string), emotionComments.get(string));
            containerEvaluation.getChildren().add(emotionBox);
            if (isRated) {
                emotionBox.setDisable(true);
            }

        }
    }
    /**verifica se la canzone è già stata valutata
     * @return true se già valutata
    */
    private boolean emotionsRated() {
        if (tm.checkIfRated(track.getTrack_id(), ClientSession.client.getUserid())) {
            return true;

        } else {
            return false;
        }

    }
    /**salva i valori dei box di valutazione*/
    private void getVotes() {
        for (Node node : containerEvaluation.getChildren()) {
            if (node instanceof EmotionBox) {
                EmotionBox emotionBox = (EmotionBox) node;
                int emotionRating = emotionBox.getVote();
                String emotionName = emotionBox.getEmotionName(); // Supponiamo che ci sia un metodo per ottenere il
                                                                  // nome dell'emozione
                String comment = emotionBox.getComment();

                emotionComments.put(emotionName, comment);
                emotions.put(emotionName, emotionRating);
            }
        }
    }
    /**salva emozioni ed eventuali comenti della canzone*/
    public void inserisciEmozioniBrano(ActionEvent event) {
        getVotes();
        if (anyZero()) {
            esitoLabel.setTextFill(Color.RED);
            esitoLabel.setText("Devi votare tutte le emozioni");
            return;
        }
        System.out.println(emotions);
        saveButton.setDisable(true);
        EmotionEvaluation ee = new EmotionEvaluation(emotions, ClientSession.client.getUserid(), track.getTrack_id(),
                emotionComments);
        tm.insertEmotion(ee);
        Platform.runLater(() -> {
            rootCanzoneview.getChildren().remove(3);
            allEmotions = tm.getAllEmotions(track);
            rootCanzoneview.getChildren().add(createChart());
        });

        System.out.println("Emozioni salvate");
    }
    /**check se qualche emozione non valutata
     * @return true se almeno una emozione non è stata valutata
    */
    private boolean anyZero() {
        for (String string : emotions.keySet()) {
            if (emotions.get(string) == 0) {
                return true;
            }
        }
        return false;
    }
    /**crea il barchart della media emozioni
     * @return contenitore con barchart
    */
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
