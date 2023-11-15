package util;

import controllers.creazionePlaylistController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pkg.Track;

public class TableViewManager extends TableView<Track> {

    creazionePlaylistController contr = new creazionePlaylistController();

    // t/f tre punti, f/t add, f/f delete
    public TableViewManager(boolean def, boolean add) {
        super();

        this.getStyleClass().add("tableView");
        TableColumn<Track, Integer> indexColumn = new TableColumn<>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setMaxWidth(50);
        this.getColumns().add(indexColumn);
        indexColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(cellData.getValue()) + 1));
        indexColumn.getStyleClass().add("indexColumn");
        TableColumn<Track, String> trackName = new TableColumn<>("Name");
        trackName.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName.setMinWidth(100);

        TableColumn<Track, String> album = new TableColumn<>("Album");
        album.setCellValueFactory(new PropertyValueFactory<>("album"));
        album.setMinWidth(100);

        TableColumn<Track, String> trackName2 = new TableColumn<>("Name2");
        trackName2.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName2.setMinWidth(100);

        TableColumn<Track, String> album2 = new TableColumn<>("Album2");
        album2.setCellValueFactory(new PropertyValueFactory<>("album"));
        album2.setMinWidth(100);

        TableColumn<Track, String> trackName3 = new TableColumn<>("Name3");
        trackName3.setCellValueFactory(new PropertyValueFactory<>("name"));
        trackName3.setMinWidth(100);

        trackName.prefWidthProperty().bind(this.widthProperty().divide(6)); 
        album.prefWidthProperty().bind(this.widthProperty().divide(6));
        trackName2.prefWidthProperty().bind(this.widthProperty().divide(6)); 
        album2.prefWidthProperty().bind(this.widthProperty().divide(6));
        trackName3.prefWidthProperty().bind(this.widthProperty().divide(6));

        this.getColumns().addAll(trackName, album, trackName2, album2, trackName3);

        if (def) {
            addButtonColumn("•••", this::customMethod);
        } else if (add) {
            addButtonColumn("ADD", this::addTrackToPlaylist);
        } else {
            addButtonColumn("DELETE", this::removeTrackFromPlaylist);
        }

        this.getColumns().forEach(column -> column.setReorderable(false));

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    Track selectedItem = getSelectionModel().getSelectedItem();
                    System.out.println("Hai cliccato sulla riga: " + selectedItem);
                }
            }
        });
    }

    private void addButtonColumn(String buttonText, ButtonClickHandler handler) {
        TableColumn<Track, Button> buttonCol = new TableColumn<>("Button");
        buttonCol.setMinWidth(75);
        buttonCol.setMaxWidth(75);
        this.getColumns().add(buttonCol);

        buttonCol.setCellFactory(param -> {
            Button button = new Button(buttonText);

            button.getStyleClass().add("tableView_button");
            button.setTextFill(Color.GREEN);
            TableCell<Track, Button> cell = new TableCell<Track, Button>() {
                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(button, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };

            button.setOnAction(event -> {
                Track track = cell.getTableView().getItems().get(cell.getIndex());
                handler.handle(track);
            });

            return cell;
        });
    }

    private void customMethod(Track track) {
        System.out.println("Clicked on " + track.getName());
        System.out.println(track.toString());
    }

    private void addTrackToPlaylist(Track track) {
        creazionePlaylistController.playlistTracks.setVisible(true);
        TableView<Track> tmp = this; 
        VBox p = (VBox) tmp.getParent();
        tmp.getItems().remove(track);
        ((TableView<Track>) p.getChildren().get(0)).getItems().add(track);
    }

    private void removeTrackFromPlaylist(Track track) {
        TableView<Track> tmp = this; 
        VBox p = (VBox) tmp.getParent();
        tmp.getItems().remove(track);
        ((TableView<Track>) p.getChildren().get(2)).getItems().add(track);
    }

    private interface ButtonClickHandler {
        void handle(Track track);
    }
}
