package controllers;

import Session.WindowAppearance;
import WindowsLoader.SignWindow;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import util.FXMLLoaders;

public class homeWindowController {

    private double MinWidth = WindowAppearance.getWindowWidth() * 0.08;
    private double MaxWidth = WindowAppearance.getWindowWidth() * 0.15;

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

        centerScrollPane.setFocusTraversable(false);

        // metto insets tra le region di borderPane
        // pagherei per tradure sta cosa in css
        BorderPane.setMargin(rootMenu, new Insets(0, 7, 0, 0));
        BorderPane.setMargin(centerStackPane, new Insets(0, 0, 0, 7));

        //************** Questo è il button per user (qunado è loggato) deve poter fare anche logout :(
        Button userButton = createButton("username");
        userButton.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        String url = getClass().getResource("/imgs/test_icon.png").toExternalForm();
        ImageView img = new ImageView(url);
        img.setFitHeight(24);
        img.setFitWidth(24);
        img.setClip(cropUserImg(img));
        userButton.setGraphic(img);
        header_hbox.getChildren().add(userButton);
        userButton.setVisible(false);


        //********* mancano altri bottoni ********************
        // ... 
        //***************************************************


        // carico la home 

        StackPane homeView = (StackPane) loader.loadFXML("homeView.fxml");
        centerScrollPane.setContent(homeView);

        
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

    public void openSignWindow(MouseEvent e){
        SignWindow window = new SignWindow();
    }

}
