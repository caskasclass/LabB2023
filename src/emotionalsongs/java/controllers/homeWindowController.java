package controllers;

import Session.WindowAppearance;
import WindowsLoader.SignWindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import util.ColorsManager;
import util.FXMLLoaders;

public class homeWindowController {

    private double MinWidth = WindowAppearance.getWindowWidth() * 0.22; // non toccare
    private double MaxWidth = WindowAppearance.getWindowWidth() * 0.25; // non toccare

    private FXMLLoaders loader = new FXMLLoaders();

    @FXML
    private BorderPane rootPane;

    @FXML
    private VBox rootMenu;

    @FXML
    private ScrollPane centerScrollPane;

    @FXML
    private StackPane centerStackPane;

    @FXML
    private HBox header_hbox;

    @FXML
    private Button homeButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button signButton;

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
        Button userButton = createButton("username");
        userButton.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        String url = getClass().getResource("/imgs/playlist_img/img6.png").toExternalForm();
        Image image = new Image(url);

        System.out.println("url image : " + url);
        System.out.println("image " + image);

        Pane pane = new Pane();
        pane.setPrefSize(40, 40);

        System.out.println("\n\nCalcolo colore inizio\n\n");
        long start = System.currentTimeMillis();
        Color c = ColorsManager.getMixedColor(image);
        long end = System.currentTimeMillis();
        System.out.println("\n\nCalcolo colore fine, tempo impiegato : " + (end - start) + " ms.\n\n");
        pane.setBackground(Background.fill(c));

        ImageView img = new ImageView(image);

        img.setFitHeight(24);
        img.setFitWidth(24);
        img.setClip(cropUserImg(img));
        userButton.setGraphic(img);

        StackPane homeView = (StackPane) loader.loadFXML("homeView.fxml");
        centerScrollPane.setContent(homeView);

        // Color c = new Color(end, end, start, end);
        header_hbox.setStyle("-fx-background-color: rgba(40,25,83,0);");
        centerScrollPane.addEventFilter(ScrollEvent.SCROLL, this::handleScrollEvent);
        header_hbox.getChildren().add(userButton);
        header_hbox.getChildren().add(pane);
        userButton.setVisible(true);

        // metto insets tra le region di borderPane
        // pagherei per tradure sta cosa in css
        BorderPane.setMargin(rootMenu, new Insets(0, 7, 0, 0));
        BorderPane.setMargin(centerStackPane, new Insets(0, 0, 0, 7));

    }

    // Metodo per gestire l'evento ScrollEvent
    private void handleScrollEvent(ScrollEvent event) {
        Background originalBackground = header_hbox.getBackground();
        double currentOpacity = ((Color) (originalBackground.getFills().get(0).getFill())).getOpacity();

        originalBackground.getFills().get(0);

        double newOpacity = currentOpacity - event.getDeltaY() / 1000.0;
        newOpacity = Math.min(1.0, Math.max(0.0, newOpacity)); // controllo che l'opacità sia compresa tra 0 e 1

        // creo il nuovo bgFill per il header_hbox
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.rgb(80, 56, 160, newOpacity), // Colore con l'opacità desiderata
                originalBackground.getFills().get(0).getRadii(),
                originalBackground.getFills().get(0).getInsets());

        // Crea un nuovo bg
        Background newBackground = new Background(backgroundFill);

        // JAT per modifiche all UI
        Platform.runLater(() -> header_hbox.setBackground(newBackground));
        // test
        System.out.println("\nEvento di scroll: " + event.getDeltaY());
    }

    private void animateMenuWidth(double newWidth) {
        double menuWidth = Math.min(newWidth * 0.23, MaxWidth); // Calcolo della nuova larghezza
        rootMenu.setPrefWidth(menuWidth);
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.getStyleClass().add("header_buttons");
        return btn;
    }

    private Circle cropUserImg(ImageView img) {
        Circle clip = new Circle();
        clip.setRadius(Math.min(img.getFitWidth(), img.getFitHeight()) / 2);
        clip.setCenterX(img.getFitWidth() / 2);
        clip.setCenterY(img.getFitHeight() / 2);
        return clip;
    }

    public void openWindow(MouseEvent e) {
        SignWindow window = new SignWindow();
        window.show();
    }

}
