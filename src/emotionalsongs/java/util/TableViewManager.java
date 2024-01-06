/**
 * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import Session.Globals;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import views.CanzoneView;
import jars.Track;
public class TableViewManager extends TableView<Track> {

    private int margin_error =5;
    final int BUTTON_COLUMN_SIZE = 100; //misura ib pixel
    final int INDEX_COLUMN_SIZE = 60; //misura ib pixel
    final int CUSTOM_COLUMN_SIZE = 250; //misura ib pixel
    final int DURATA_COLUMN_SIZE = 50;
    final int TOTAL_FIXED_SIZE_COLUMN = BUTTON_COLUMN_SIZE+INDEX_COLUMN_SIZE+CUSTOM_COLUMN_SIZE+DURATA_COLUMN_SIZE; //misura ib pixel 

    // t/f tre punti, f/t add, f/f delete
    public TableViewManager(boolean def, boolean add) {
        super();

        this.getStyleClass().add("tableView");
        TableColumn<Track, Integer> indexColumn = new TableColumn<>("#");
        indexColumn.getStyleClass().add("indexColumn");
        indexColumn.setMinWidth(INDEX_COLUMN_SIZE);
        indexColumn.setMaxWidth(INDEX_COLUMN_SIZE);
        this.getColumns().add(indexColumn);

        indexColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(cellData.getValue()) + 1));

        TableColumn<Track, String> album = new TableColumn<>("Album");
        album.setCellValueFactory(new PropertyValueFactory<>("album_name"));
        album.setMinWidth(75);

        TableColumn<Track, String> durata = new TableColumn<>("Dur");
        durata.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durata.setMinWidth(DURATA_COLUMN_SIZE);
        durata.setMaxWidth(DURATA_COLUMN_SIZE);

        // album.setPrefWidth(100); // in % ?, gia.. va fatto. Per ora rimane cosi tho
        // album.setMaxWidth(100);

        TableColumn<Track, Void> customColumn = new TableColumn<>("Title");
        customColumn.setCellFactory(column -> new CustomCell());
        customColumn.getStyleClass().add("customColumn");

        customColumn.setMinWidth(CUSTOM_COLUMN_SIZE);
        customColumn.setMaxWidth(CUSTOM_COLUMN_SIZE);


        album.prefWidthProperty().bind((this.widthProperty().subtract(TOTAL_FIXED_SIZE_COLUMN+margin_error)));
        album.prefWidthProperty().addListener((obs, oldWidth, newWidth) -> {
            double minWidth = 75;
            if (newWidth.doubleValue()-5 <= minWidth) {
                album.setVisible(false);
            } else {
                album.setVisible(true);
            }
        });
        
        this.getColumns().add(customColumn);
        this.getColumns().add(album);
        this.getColumns().add(durata);

        TableColumn<Track, Button> buttonCol = new TableColumn<>("");
        buttonCol.getStyleClass().add("buttonColumn");
        buttonCol.setMinWidth(BUTTON_COLUMN_SIZE);
        buttonCol.setMaxWidth(BUTTON_COLUMN_SIZE);

        if (def) {
          addButtonColumn(buttonCol,"•••", this::customMethod);
        } else if (add) {
            addButtonColumn(buttonCol,"ADD", this::addTrackToPlaylist);
        } else {
            addButtonColumn(buttonCol,"DEL", this::removeTrackFromPlaylist);
        }
        this.getColumns().add(buttonCol);
        this.getColumns().forEach(column -> column.setReorderable(false));

          
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    Track selectedItem = getSelectionModel().getSelectedItem();
                    System.out.println("\n\nDouble click on: "+selectedItem.getName());
                    CanzoneView trackView = new CanzoneView(selectedItem);
                    Globals.getRootFrame().setContent(trackView);
                }
            }
        });
    }

    private void addButtonColumn(TableColumn<Track,Button> column,String buttonText, ButtonClickHandler handler) {
        column.setCellFactory(param -> {
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
        TableView<Track> tmp = this; 
        VBox p = (VBox) tmp.getParent();
        tmp.getItems().remove(track);

        Node tmpNode = p.getChildren().get(0);
        if(tmpNode instanceof TableView){
            if(!(((TableView<Track>) tmpNode).isVisible()))
            {((TableView<Track>) tmpNode).setVisible(true);}
            ((TableView<Track>) tmpNode).getItems().add(track);
        }
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




