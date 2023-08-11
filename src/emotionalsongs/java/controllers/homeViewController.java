package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;
import views.AlbumView;
import views.PlaylistBox;

public class homeViewController {

    public final static double PLAYLIST_CONTAINER_SPACING = 12;
    public final static double ROOT_PADDING_LEFT_AND_RIGHT = 30;
    private int myDisplayedPlaylist = 0;
    private int othersDisplayedPlaylist = 0;

    @FXML
    private HBox primaryShader;

    @FXML
    private VBox contentContainer;

    @FXML
    private VBox gridContainer;

    @FXML
    private HBox playlistBoxContainer;

    @FXML
    private TableView<?> tableViewTopClicked;

    @FXML
    private HBox othersPlaylistBoxContainer;

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
            double width = contentContainer.getWidth() - 10;
            initializeElements(width);

            contentContainer.widthProperty().addListener((observable, oldValue, newValue) -> {
                handleElements(newValue.doubleValue());
            });
        });
    }

    private void handleElements(double width) {
        int numAvailablePlylist = 6;
        int numOthersAvailablePlaylist = 4;
        double availableSpace = width - (ROOT_PADDING_LEFT_AND_RIGHT);
        double MyspacingWidth = (playlistBoxContainer.getChildren().size() - 1) * PLAYLIST_CONTAINER_SPACING;
        double OthersSpacingWidth = (playlistBoxContainer.getChildren().size() - 1) * PLAYLIST_CONTAINER_SPACING;
        double TotalMyNodeWidth = 0;
        double TotalOthersNodeWidth = 0;
        boolean MyminReached = false;
        boolean OthersminReached = false;
        for (Node node : playlistBoxContainer.getChildren()) {
            if (node instanceof VBox) {
                VBox child = (VBox) node;
                double childWidth = child.getWidth();
                TotalMyNodeWidth += childWidth;
                if (child.getMinWidth() + 10 >= childWidth) {
                    MyminReached = true;
                }
            }
        }
    
        if ((availableSpace - (MyspacingWidth + TotalMyNodeWidth)) > 25) {
            if (myDisplayedPlaylist < numAvailablePlylist) {
                myDisplayedPlaylist++;
                VBox playlistbox = new PlaylistBox(2);
                HBox.setHgrow(playlistbox, Priority.ALWAYS);
                playlistBoxContainer.getChildren().add(playlistbox);
            }
        } else if (MyminReached) {
            if (!playlistBoxContainer.getChildren().isEmpty()) {
                playlistBoxContainer.getChildren().remove(playlistBoxContainer.getChildren().size() - 1);
                myDisplayedPlaylist--;
            }

        }
        for (Node node : othersPlaylistBoxContainer.getChildren()) {
            if (node instanceof VBox) {
                VBox child = (VBox) node;
                double childWidth = child.getWidth();
                TotalOthersNodeWidth += childWidth;
                if (child.getMinWidth() + 10 >= childWidth) {
                    OthersminReached = true;
                }
            }
        }
        if ((availableSpace - (OthersSpacingWidth + TotalOthersNodeWidth)) > 25) {
            if (othersDisplayedPlaylist < numOthersAvailablePlaylist) {
                othersDisplayedPlaylist++;
                VBox playlistbox = new PlaylistBox(6);
                HBox.setHgrow(playlistbox, Priority.ALWAYS);
                othersPlaylistBoxContainer.getChildren().add(playlistbox);
            }
        } else if (OthersminReached) {
            if (!othersPlaylistBoxContainer.getChildren().isEmpty()) {
                othersPlaylistBoxContainer.getChildren().remove(othersPlaylistBoxContainer.getChildren().size() - 1);
                othersDisplayedPlaylist--;
            }

        }

    }

    public void setPlaylist(double width) {
        // from input width we can calculate the number of playlist box we can add
        // calcolo lo spazio disponibile prendendo in considerazione lo spacing nel hbox
        // e il padding
        double availableSpace = width - (ROOT_PADDING_LEFT_AND_RIGHT);
        // calcolo il numero di playlist box che posso aggiungere
        // dimesione media plalist
        double mediumWidth = (PlaylistBox.MaxWidth + PlaylistBox.MinWidth) / 2;
        System.out.println("Medium width :" + mediumWidth);
        int numberOfPlaylistBox = (int) (availableSpace / mediumWidth);
        System.out.println("Number of playlist box :" + numberOfPlaylistBox);
        myDisplayedPlaylist = numberOfPlaylistBox;
        for (int i = 0; i < numberOfPlaylistBox; i++) {
            PlaylistBox playlist = new PlaylistBox(2);
            HBox.setHgrow(playlist, Priority.ALWAYS);
            playlistBoxContainer.getChildren().add(playlist);
        }
        othersDisplayedPlaylist = numberOfPlaylistBox;
        for (int i = 0; i < numberOfPlaylistBox; i++) {
            PlaylistBox playlist = new PlaylistBox(6);
            HBox.setHgrow(playlist, Priority.ALWAYS);
            othersPlaylistBoxContainer.getChildren().add(playlist);
        }

    }

    public void setAlbums (double width) {
        // insert into grid pane the albumboxes let them fill all the horizontal space of the cell

        GridPane albumBoxContainer = new GridPane();
        albumBoxContainer.getStyleClass().add("albumBox_container");
        albumBoxContainer.setHgap(12); // Spaziatura orizzontale tra le colonne
        albumBoxContainer.setVgap(24); // Spaziatura verticale tra le righe
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50); // 50% dello spazio disponibile
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50); // 50% dello spazio disponibile
        albumBoxContainer.getColumnConstraints().addAll(col1, col2);

         for (int i = 0; i < 4; i++) {
            int row = i / 2; // Calcola la riga in base all'indice
            int col = i % 2; // Calcola la colonna in base all'indice

            AlbumView alvumBox = new AlbumView(5);
            albumBoxContainer.add(alvumBox, col, row); // Aggiungi l'elemento alla griglia
        }
        gridContainer.getChildren().add(1,albumBoxContainer);


        
    }

    private void initializeElements(double width) {
        setPlaylist(width);
        setAlbums(width);
    }

}
