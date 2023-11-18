package util;

import controllers.creazionePlaylistController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tmp.Canzone;

public class TableViewManager extends TableView<Canzone> {

    creazionePlaylistController contr = new creazionePlaylistController();


    private int num_columns = 2;
    private int margin_error =5;
    final int BUTTON_COLUMN_SIZE = 100; //misura ib pixel
    final int INDEX_COLUMN_SIZE = 60; //misura ib pixel
    final int CUSTOM_COLUMN_SIZE = 150; //misura ib pixel
    final int DURATA_COLUMN_SIZE = 50;
    final int TOTAL_FIXED_SIZE_COLUMN = BUTTON_COLUMN_SIZE+INDEX_COLUMN_SIZE+CUSTOM_COLUMN_SIZE+DURATA_COLUMN_SIZE; //misura ib pixel 

    public TableViewManager(boolean def, boolean add) {
        super();

        this.getStyleClass().add("tableView");
        TableColumn<Canzone, Integer> indexColumn = new TableColumn<>("#");
        indexColumn.getStyleClass().add("indexColumn");
        indexColumn.setMinWidth(INDEX_COLUMN_SIZE);
        indexColumn.setMaxWidth(INDEX_COLUMN_SIZE);
        this.getColumns().add(indexColumn);

        indexColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(cellData.getValue()) + 1));

        TableColumn<Canzone, String> trackName = new TableColumn<>("Name");
        trackName.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName.setMinWidth(75);
        // trackName.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi
        // tho
        // trackName.setMaxWidth(100);
        TableColumn<Canzone, String> album = new TableColumn<>("Album");
        album.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        album.setMinWidth(75);

        TableColumn<Canzone, String> durata = new TableColumn<>("Dur");
        durata.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        durata.setMinWidth(DURATA_COLUMN_SIZE);
        durata.setMaxWidth(DURATA_COLUMN_SIZE);

        // album.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        // album.setMaxWidth(100);

        TableColumn<Canzone, Void> customColumn = new TableColumn<>("Track Details");
        customColumn.setCellFactory(column -> new CustomCell());
        customColumn.getStyleClass().add("customColumn");

        customColumn.setMinWidth(CUSTOM_COLUMN_SIZE);
        customColumn.setMaxWidth(CUSTOM_COLUMN_SIZE);


        trackName.prefWidthProperty().bind((this.widthProperty().subtract(TOTAL_FIXED_SIZE_COLUMN+margin_error)).divide(num_columns)); // Dividi lo spazio in due colonne
        album.prefWidthProperty().bind((this.widthProperty().subtract(TOTAL_FIXED_SIZE_COLUMN+margin_error)).divide(num_columns));
        album.prefWidthProperty().addListener((obs, oldWidth, newWidth) -> {
            double minWidth = 75;
            if (newWidth.doubleValue()-5 <= minWidth) {
                album.setVisible(false);
                num_columns--;
            } else {
                album.setVisible(true);
                num_columns++;
            }
        });
        album.visibleProperty().addListener((obs, wasVisible, isVisible) -> {
            if (!isVisible) {
                durata.setVisible(false);
                margin_error -=50;
            } else {
                durata.setVisible(true);
                margin_error =5;
            }
        });
        
        this.getColumns().add(customColumn);
        this.getColumns().add(trackName);
        this.getColumns().add(album);
        this.getColumns().add(durata);

        TableColumn<Canzone, Button> buttonCol = new TableColumn<>("");
        buttonCol.getStyleClass().add("buttonColumn");
        buttonCol.setMinWidth(BUTTON_COLUMN_SIZE);
        buttonCol.setMaxWidth(BUTTON_COLUMN_SIZE);
        if (def) {
            // ••• button

            this.getColumns().add(buttonCol);
            // Add a button to the button column for each row
            buttonCol.setCellFactory(param -> {
                Button button = new Button("•••");

                button.getStyleClass().add("tableView_button");
                button.setTextFill(Color.GREEN);
                TableCell<Canzone, Button> cell = new TableCell<Canzone, Button>() {
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
                    Canzone canzone = cell.getTableView().getItems().get(cell.getIndex());
                    customMethod(canzone);
                });

                return cell;
            });

        } else if (add) {
            // add butto

            this.getColumns().add(buttonCol);
            // Add a button to the button column for each row
            buttonCol.setCellFactory(param -> {
                Button button = new Button("ADD");

                button.getStyleClass().add("tableView_button");
                button.setTextFill(Color.GREEN);
                TableCell<Canzone, Button> cell = new TableCell<Canzone, Button>() {
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

                    creazionePlaylistController.playlistTracks.setVisible(true);
                    TableView<Canzone> tmp = cell.getTableView();
                    VBox p = (VBox) tmp.getParent();
                    Canzone canzone = cell.getTableView().getItems().get(cell.getIndex());
                    tmp.getItems().remove(canzone);
                    ((TableView<Canzone>) p.getChildren().get(0)).getItems().add(canzone);

                    // creazionePlaylistController.playlistTracks.getItems().add(canzone);
                    // creazionePlaylistController.findTracks.getItems().remove(canzone);

                });

                return cell;
            });
        } else {
            // remove button
            buttonCol.setMinWidth(75);
            this.getColumns().add(buttonCol);
            // Add a button to the button column for each row
            buttonCol.setCellFactory(param -> {
                Button button = new Button("DEL");

                button.getStyleClass().add("tableView_button");
                button.setTextFill(Color.GREEN);
                TableCell<Canzone, Button> cell = new TableCell<Canzone, Button>() {
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
                    TableView<Canzone> tmp = cell.getTableView();
                    VBox p = (VBox) tmp.getParent();
                    Canzone canzone = cell.getTableView().getItems().get(cell.getIndex());
                    tmp.getItems().remove(canzone);
                    ((TableView<Canzone>) p.getChildren().get(2)).getItems().add(canzone);
                    // creazionePlaylistController.playlistTracks.getItems().remove(canzone);
                    // creazionePlaylistController.findTracks.getItems().add(canzone);

                });

                return cell;
            });
        }
        this.getColumns().forEach(column -> column.setReorderable(false));

        // per aprire view canzone: scegliere se singolo click o doppio
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) { // doppio clic
                    Canzone selectedItem = getSelectionModel().getSelectedItem();
                    System.out.println("Hai cliccato sulla riga: " + selectedItem);
                }
            }
        });

    }

    private void customMethod(Canzone canzone) {
        System.out.println("Clicked on " + canzone.getTrackName());
        System.out.println(canzone.toString());
    }

}
