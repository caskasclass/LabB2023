package util;

import Models.TrackModule;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EmotionBox extends VBox {
    private final String emotionName;
    private final Label[] ratingLabels = new Label[5];
    TrackModule tm = new TrackModule();
    Integer vote = 0;
    String comment = "";
    TextArea description = new TextArea();


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

    public String getEmotionName() {
        return emotionName;
    }

    public Integer getVote() {
        return vote;
    }
    public String getComment(){
        if(description.getText().isEmpty()){
            return "Silence is golden";
        }else 
            return description.getText();
    }
}
