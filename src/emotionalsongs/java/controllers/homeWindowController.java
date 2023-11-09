package controllers;

import java.util.ArrayList;

import Session.WindowAppearance;
import Threads.ResizeHandler;
import WindowsLoader.SignWindow;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import views.HomeView;
import views.PlaylistView;
import views.AllTrackView;
import views.CanzoneView;
import views.CreatePlaylistView;
import views.ExplorePlaylistView;
import javafx.stage.Stage;
import tmp.Canzone;
import util.BackgroundTransition;
import javafx.scene.effect.BoxBlur;
import pkg.*;

public class homeWindowController {

    private double MinWidth = WindowAppearance.getWindowWidth() * 0.22; // non toccare
    private double MaxWidth = WindowAppearance.getWindowWidth() * 0.25; // non toccare

    private double opacity = 0.0;
    private CornerRadii cornerRad = new CornerRadii(8, 8, 0, 0, false);

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
    private void initialize() {

        // 1) margini al borderPane
        BorderPane.setMargin(rootMenu, new Insets(0, 7, 0, 0));
        BorderPane.setMargin(centerStackPane, new Insets(0, 0, 0, 7));

        // 2) configuro la largezza min e max del menu.
        System.out.println("homeWindowController");
        rootMenu.setMinWidth(MinWidth);// 0.23 è quella giusta
        rootMenu.setMaxWidth(MaxWidth);// 0.30 è ok, anche troppo

        // 2.5) inizializzo stile header
        header_hbox.setStyle("-fx-background-color: rgba(40,25,83,0);");
        Platform.runLater(() -> {
            BackgroundTransition.setHeaderGraphics((Color) (header_hbox.getBackground().getFills().get(0).getFill()));
        });

        // 3) inizializzo il center con la home
        // System.out.println(ResizeHandler.getCenterWidth()); ok

        HomeView homeView = new HomeView(ResizeHandler.getCenterWidth());
        centerScrollPane.setContent(homeView);

        // centerScrollPane.setFitToHeight(false);
        // listener per la width

        // 4) istanzio il button user per la prova colore + il button in se
        Button userButton = createButton("username");
        userButton.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        String url = getClass().getResource("/imgs/playlist_img/img2.png").toExternalForm();
        Image image = new Image(url);
        ImageView img = new ImageView(image);
        img.setClip(cropUserImg(img, 24, 24));
        userButton.setGraphic(img);
        userButton.setVisible(true);

        // 6) Aggiungo i button al header
        header_hbox.getChildren().add(userButton);

        // 7) vari listener
        Platform.runLater(() -> {
            ResizeHandler resizeHandler = new ResizeHandler(rootPane, rootMenu);
            resizeHandler.start();
        });

        // per la width del left side del borde-rpane

        // per il colore sul SCROLL
        centerScrollPane.addEventFilter(ScrollEvent.SCROLL, this::handleScrollEvent);

    }

    private void handleScrollEvent(ScrollEvent event) {
        // pos in base a V max e min del scroll pane(nel mio caso tra 0 e 100)
        double vPosition = centerScrollPane.getVvalue();

        // calcola l'opacità in base alla posizione di scorrimento
        // vi position è un numero compreso tra 0 e 100
        if (vPosition >= 0 && vPosition <= 45) {
            // System.out.println("Vposition : " + vPosition);
            // passaggio da 0 a 1 quando vPosition è compreso tra 0 e 45
            opacity = vPosition / 45;

            if (opacity >= 0.98)
                opacity = 1;
        }
        // di base creo un nuovo bgFill (devo cambiare in base all view) lo faccio pià
        // avanti
        int red = (int) (BackgroundTransition.hbox_header.getRed() * 255);
        int green = (int) (BackgroundTransition.hbox_header.getGreen() * 255);
        int blue = (int) (BackgroundTransition.hbox_header.getBlue() * 255);
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.rgb(red, green, blue, opacity), // Colore con l'opacità desiderata
                cornerRad, // radii (non ho la minima idea di che cosa sia )
                null // insets
        );

        // ora bg con gbFill
        Background newBackground = new Background(backgroundFill);

        // assegno il nuovo bg
        Platform.runLater(() -> header_hbox.setBackground(newBackground));
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.getStyleClass().add("header_buttons");
        return btn;
    }

    private Circle cropUserImg(ImageView img, double width, double height) {
        img.setFitHeight(height);
        img.setFitWidth(width);
        Circle clip = new Circle();
        clip.setRadius(Math.min(img.getFitWidth(), img.getFitHeight()) / 2);
        clip.setCenterX(img.getFitWidth() / 2);
        clip.setCenterY(img.getFitHeight() / 2);
        return clip;
    }

    public void openWindow(MouseEvent e) {
        Stage parent = (Stage) homeButton.getScene().getWindow();
        SignWindow window = new SignWindow();
        window.initOwner(parent);
        BoxBlur blur = new BoxBlur(10, 10, 3);
        parent.getScene().getRoot().setEffect(blur);
        Platform.runLater(() -> {
            parent.getScene().getRoot().setDisable(true);
        });
        window.setOnHidden(event -> {
            parent.getScene().getRoot().setEffect(null);
            parent.getScene().getRoot().setDisable(false);
        });
        window.show();

    }

    public void openViewCreate(MouseEvent e) {

        CreatePlaylistView view = new CreatePlaylistView();
        view.prefWidthProperty().bind(centerScrollPane.widthProperty());
        view.prefHeightProperty().bind(centerScrollPane.heightProperty());
        centerScrollPane.setContent(view);
    }

    public void openAllTrack(MouseEvent e) {
        AllTrackView view = new AllTrackView(new ArrayList<Track>());
        view.prefWidthProperty().bind(centerScrollPane.widthProperty());
        view.prefHeightProperty().bind(centerScrollPane.heightProperty());
        centerScrollPane.setContent(view);
    }

    public void backHome(MouseEvent e) {
        HomeView view = new HomeView(ResizeHandler.getCenterWidth());
        centerScrollPane.setContent(view);
    }

    public void openPlaylist(MouseEvent e) {
        PlaylistView view = new PlaylistView();
        centerScrollPane.setContent(view);
    }

    public void explorePlaylist(MouseEvent e) {
        ExplorePlaylistView view = new ExplorePlaylistView();
        centerScrollPane.setContent(view);
    }

}
