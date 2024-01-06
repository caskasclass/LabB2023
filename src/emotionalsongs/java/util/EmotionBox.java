/**
  * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import Models.TrackModule;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe  progettata per rappresentare una casella contenente informazioni sull'emozione, inclusi i voti e i commenti degli utenti
 */
public class EmotionBox extends VBox {
    private final String emotionName;
    private final Label[] ratingLabels = new Label[5];
    TrackModule tm = new TrackModule();
    Integer vote = 0;
    String comment = "";
    TextArea description = new TextArea();


 
 /** costruttore della classe: Viene inizializzata l'emozione, il voto e il commento sulla base dei parametri forniti
 *Viene applicato uno stile di ombreggiatura alla casella
 *Vengono impostati stili e dimensioni per la casella, inizializzati i componenti UI e in base al flag di valutazione, viene impostato il voto e il commento iniziali
 *@param <code>emotionName</code> parametro di tipo String, <code>isRated</code> parametro di tipo boolean, <code>index</code> parametro di tipo Integer, <code>comment</code> parametro di tipo String
 */
    public EmotionBox(String emotionName, boolean isRated, Integer index,String comment) {
        super();
        setAccessibleRole(AccessibleRole.BUTTON);
        if (isRated) {
            vote = index;
        }
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(64, 63, 63), 12, 0.16, 0, 0);
        shadow.setWidth(25);
        shadow.setHeight(25);
        this.emotionName = emotionName;
        this.comment = comment;
        setEffect(shadow);
        getStyleClass().add("rateBox");
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10, 15, 50, 15));
        setSpacing(10);
        setMinWidth(280);
        setMaxWidth(280);
        initUI();
        if (isRated) {
            ratingLabels[index - 1].getStyleClass().add("Selected");
            ratingLabels[index - 1].getStyleClass().remove("notSelected");
            description.setText(comment);
        }


    }


 
 /**metodo privato che inizializza i componenti dell'interfaccia utente all'interno della casella
 *Viene creato un contenitore per il titolo dell'emozione
 *Viene creata una TextArea per inserire commenti più dettagliati sull'emozione
 *Viene creato un contenitore per le etichette di valutazione
 */  
    private void initUI() {
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(0, 10, 10, 10));
        titleContainer.setMinHeight(70);
        titleContainer.setMaxHeight(70);
        Label title = new Label(emotionName);
        titleContainer.getChildren().add(title);

        // aggiungo un textarea per la descrizione dell'emozione
        description.setWrapText(true);
        description.getStyleClass().add("commentField");
        description.setPromptText("Dicci qualcosa in più...");
        description.setMaxHeight(65);
        description.setMinHeight(65);
    

        HBox choiceContainer = new HBox();
        choiceContainer.setSpacing(10);
        choiceContainer.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            Label label = createRatingLabel(String.valueOf(i + 1));
            ratingLabels[i] = label;
            choiceContainer.getChildren().add(label);
        }

        getChildren().addAll(titleContainer,description,choiceContainer);
    }


 
 /**metodo privato che Crea e restituisce un'etichetta di valutazione con il testo specificato
 *Viene impostato uno stile di classe CSS all'etichetta *Viene creata una TextArea per inserire commenti più dettagliati sull'emozione
 *Viene aggiunto un gestore di eventi al clic dell'etichetta per gestire la selezione/deselezione del voto
 */  
    private Label createRatingLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().addAll("rateNum", "notSelected");
        label.setMinWidth(35);
        label.setMaxWidth(35);
        label.setMinHeight(35);
        label.setMaxHeight(35);
        label.setAlignment(Pos.CENTER);
        label.setAccessibleRole(AccessibleRole.BUTTON);

        label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleRatingClick(label));

        return label;
    }


 
 /** metodo privato che gestisce il clic sulle etichette di valutazione
 *@param <code>label</code> parametro di tipo Label
 */
    private void handleRatingClick(Label label) {
        int selectedIndex = Integer.parseInt(label.getText()) - 1;

        for (int i = 0; i < ratingLabels.length; i++) {
            if (i == selectedIndex) {
                if (ratingLabels[i].getStyleClass().contains("Selected")) {
                    ratingLabels[i].getStyleClass().remove("Selected");
                    ratingLabels[i].getStyleClass().add("notSelected");
                    vote = 0;
                } else {
                    ratingLabels[i].getStyleClass().add("Selected");
                    ratingLabels[i].getStyleClass().remove("notSelected");
                    vote = i + 1;
                }
            } else {
                ratingLabels[i].getStyleClass().remove("Selected");
                ratingLabels[i].getStyleClass().add("notSelected");
            }

        }
    }



 /**metodo che restituisce il nome dell'emozione*/
    public String getEmotionName() {
        return emotionName;
    }


 
 /**metodo che restituisce il voto associato all'emozione*/
    public Integer getVote() {
        return vote;
    }


 
 /**metodo che restituisce il commento associato all'emozione, con una stringa predefinita se la TextArea è vuota*/
    public String getComment(){
        if(description.getText().isEmpty()){
            return "Silence is golden";
        }else 
            return description.getText();
    }
}
