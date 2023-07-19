package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class homeViewController {

    @FXML
    private HBox primaryShader;
    @FXML
    private HBox innerShader;

    @FXML
    private void initialize() {
        /*primaryShader.setStyle("-fx-background-color: rgb(83, 83, 83);");
        // StackPane to hold the gradient and noise texture
        StackPane stackPane = new StackPane();

        Canvas canvas =  new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawGradient(gc, 400, 300);
        String imageUrl = getClass().getResource("/imgs/noise.svg").toExternalForm();
        drawNoiseTexture(gc, imageUrl, 400, 300);

        stackPane.getChildren().add(canvas);
        innerShader.getChildren().add(stackPane);*/

    }

   /*  private void drawGradient(GraphicsContext gc, double width, double height) {
        gc.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(0, 0, 0, 0.6)),
                new Stop(1, Color.rgb(18, 18, 18))));
        gc.fillRect(0,0,width,height);
    }

    // Metodo per disegnare la texture di rumore sul Canvas
    private void drawNoiseTexture(GraphicsContext gc, String imageUrl, double width, double height) {
        // Carica l'immagine della texture di rumore
        // Assicurati di fornire il percorso corretto per l'immagine della texture di
        // rumore
        // (assicurati che sia presente nel percorso corretto)
        Image noiseTexture = new Image(imageUrl);

        // Disegna l'immagine della texture di rumore sul Canvas
        gc.setGlobalAlpha(0.1); // Regola l'opacità dell'effetto di rumore
        gc.drawImage(noiseTexture, 0, 0, width, height);
    }
*/
}
