package controllers;

import Session.WindowAppearance;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    private int MAX_ALBUM_DISPLAYED = 6;
    private int num_columns = 0;
    private int num_rows = 0;

    private GridPane albumBoxContainer = null;

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

        // PER LA SEC MIE PLAYLIST
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
        // PER LA SEC OTHERS PLAYLIST
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
        // PER SEC ALBUM
        ObservableList<Node> boxes = albumBoxContainer.getChildren();
        double boxCurrentWidth = 0;
        if (boxes.get(0) instanceof HBox) {
            boxCurrentWidth = ((HBox) boxes.get(0)).getWidth();
        }
        if (boxCurrentWidth != 0) {
            System.out.println("Current Width :" + boxCurrentWidth);
            System.out.println("MIN Width :" + (AlbumView.MIN_WIDTH+5));

            // quando sono in 3 il min di una è 276px 
            // window width 1285px con menu e altro. 
            // il 0.25 : 321; 
            // il 0.35 : 449;


            switch (num_columns) {
                case 1:
                    if (boxCurrentWidth > WindowAppearance.getWindowWidth() * 0.50){
                        num_columns++;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    }
                    break;
                case 2:
                    if (boxCurrentWidth > WindowAppearance.getWindowWidth() * 0.37){
                        num_columns++;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    } else if (boxCurrentWidth <= AlbumView.MIN_WIDTH+5) {
                        num_columns--;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    }
                    break;
                case 3:
                    if (boxCurrentWidth < AlbumView.MIN_WIDTH + 5) {
                        num_columns--;
                        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
                        albumBoxContainer.getChildren().clear();
                        albumBoxContainer.getColumnConstraints().clear();
                        fillGrid(num_columns, num_rows);
                    }
                    break;
                default:
                    break;
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

    public void setAlbums(double width) {
        albumBoxContainer = new GridPane();
        albumBoxContainer.getStyleClass().add("albumBox_container");
        albumBoxContainer.setHgap(12); // Spaziatura orizzontale tra le colonne
        albumBoxContainer.setVgap(24); // Spaziatura verticale tra le righe
        final int MAX_PER_ROW = 3;
        double availableSpace = width - (ROOT_PADDING_LEFT_AND_RIGHT);
        double idealWidth = AlbumView.MIN_WIDTH + 50;
        num_columns = (int) (availableSpace / idealWidth);
        if (num_columns > MAX_PER_ROW) {
            num_columns = MAX_PER_ROW;
        }
        num_rows = MAX_ALBUM_DISPLAYED / num_columns;
        fillGrid(num_columns, num_rows);
        gridContainer.getChildren().add(1, albumBoxContainer);

    }

    private void fillGrid(int columns, int rows) {
        int width_percenatge = (int) (100 / columns);
        for (int i = 0; i < columns; i++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(width_percenatge);
            albumBoxContainer.getColumnConstraints().add(constraints);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                AlbumView album = new AlbumView(5);
                albumBoxContainer.add(album, j, i);
            }
        }
    }

    private void initializeElements(double width) {
        setPlaylist(width);
        setAlbums(width);
    }

}