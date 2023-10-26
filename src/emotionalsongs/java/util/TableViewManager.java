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

    public TableViewManager(boolean def, boolean add) {
        super();

        this.getStyleClass().add("tableView");
        TableColumn<Canzone, Integer> indexColumn = new TableColumn<>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setMaxWidth(50);
        this.getColumns().add(indexColumn);
        indexColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(cellData.getValue()) + 1));
        indexColumn.getStyleClass().add("indexColumn");
        TableColumn<Canzone, String> trackName = new TableColumn<>("Name");
        trackName.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName.setMinWidth(100);
        //trackName.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        //trackName.setMaxWidth(100);
        TableColumn<Canzone, String> album = new TableColumn<>("Album");
        album.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        album.setMinWidth(100);
        //album.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        //album.setMaxWidth(100);
        TableColumn<Canzone, String> trackName2 = new TableColumn<>("Name2");
        trackName2.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName2.setMinWidth(100);
        //trackName2.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        //trackName2.setMaxWidth(100);
        TableColumn<Canzone, String> album2 = new TableColumn<>("Album2");
        album2.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        album2.setMinWidth(100);
        //album2.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        //album2.setMaxWidth(100);
        TableColumn<Canzone, String> trackName3 = new TableColumn<>("Name3");
        trackName3.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName3.setMinWidth(100);
        //trackName3.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        //trackName3.setMaxWidth(100);
        trackName.prefWidthProperty().bind(this.widthProperty().divide(6)); // Dividi lo spazio in due colonne
        album.prefWidthProperty().bind(this.widthProperty().divide(6)); 
        trackName2.prefWidthProperty().bind(this.widthProperty().divide(6)); // Dividi lo spazio in due colonne
        album2.prefWidthProperty().bind(this.widthProperty().divide(6));
        trackName3.prefWidthProperty().bind(this.widthProperty().divide(6));
        this.getColumns().add(trackName);
        this.getColumns().add(album);
        this.getColumns().add(trackName2);
        this.getColumns().add(album2);
        this.getColumns().add(trackName3);

        if (def) {
            // ••• button
            TableColumn<Canzone, Button> buttonCol = new TableColumn<>("Button");
            buttonCol.setMinWidth(75);
            buttonCol.setMaxWidth(75);

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
            // add button
            TableColumn<Canzone, Button> buttonCol = new TableColumn<>("Button");
            buttonCol.setMinWidth(75);
            buttonCol.setMaxWidth(75);
            
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
 
                //creazionePlaylistController.playlistTracks.getItems().add(canzone);
                //creazionePlaylistController.findTracks.getItems().remove(canzone);
                
            });

            return cell;
        });
        } else {
            // remove button
            TableColumn<Canzone, Button> buttonCol = new TableColumn<>("Button");
            buttonCol.setMinWidth(150);
            this.getColumns().add(buttonCol);
             // Add a button to the button column for each row
            buttonCol.setCellFactory(param -> {
            Button button = new Button("DELETE");
            
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
                //creazionePlaylistController.playlistTracks.getItems().remove(canzone);
                //creazionePlaylistController.findTracks.getItems().add(canzone);
                
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
    

    


