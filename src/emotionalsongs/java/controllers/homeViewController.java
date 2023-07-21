package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import util.ColorsManager;

public class homeViewController {

    @FXML
    private HBox primaryShader;

    @FXML
    private void initialize() {

        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        BackgroundFill backgroundFill = new BackgroundFill(
            ColorsManager.setGradient(Color.rgb(62, 32, 146, 0.6), Color.rgb(18, 18, 18)),
            cornerRadii,
            null
        );

        Background background = new Background(backgroundFill);
        primaryShader.setBackground(background);

    }

}
