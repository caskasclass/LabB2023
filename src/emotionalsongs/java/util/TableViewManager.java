package util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import tmp.Canzone;

public class TableViewManager extends TableView<Canzone> {

    public TableViewManager(boolean def, boolean add) {
        super();
        this.getStyleClass().add("tableView");
        TableColumn<Canzone, Integer> indexColumn = new TableColumn<>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setMaxWidth(50);
        this.getColumns().add(indexColumn);
        indexColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(cellData.getValue()) + 1));
        indexColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn<Canzone, String> trackName = new TableColumn<>("Name");
        trackName.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName.setMinWidth(220);
        trackName.setPrefWidth(220); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        trackName.setMaxWidth(220);
        TableColumn<Canzone, String> album = new TableColumn<>("Album");
        album.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        album.setMinWidth(220);
        album.setPrefWidth(220); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        album.setMaxWidth(220);
        TableColumn<Canzone, String> trackName2 = new TableColumn<>("Name2");
        trackName2.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName2.setMinWidth(220);
        trackName2.setPrefWidth(220); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        trackName2.setMaxWidth(220);
        TableColumn<Canzone, String> album2 = new TableColumn<>("Album2");
        album2.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        album2.setMinWidth(220);
        album2.setPrefWidth(220); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        album2.setMaxWidth(220);
        TableColumn<Canzone, String> trackName3 = new TableColumn<>("Name3");
        trackName3.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName3.setMinWidth(220);
        trackName3.setPrefWidth(220); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        trackName3.setMaxWidth(220);
        this.getColumns().add(trackName);
        this.getColumns().add(album);
        this.getColumns().add(trackName2);
        this.getColumns().add(album2);
        this.getColumns().add(trackName3);

        if (def) {
            // ••• button
            TableColumn<Canzone, Button> buttonCol = new TableColumn<>("Button");
            buttonCol.setMinWidth(150);
            buttonCol.setMaxWidth(150);
            this.getColumns().add(buttonCol);
             // Add a button to the button column for each row
            buttonCol.setCellFactory(param -> {
            Button button = new Button("•••");
            
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

        } else {
            // remove button
        }

       

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
    }

}
