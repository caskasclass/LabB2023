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
/**
*Classe che estende la classe TableView di JavaFX e aggiunge alcune funzionalità personalizzate per la gestione di una tabella di tracce musicali
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 
 */

public class TableViewManager extends TableView<Track> {

    /**elemento grafica */
    private int margin_error =5;
    /**elemento grafica */
    final int BUTTON_COLUMN_SIZE = 100;
    /**elemento grafica */
    final int INDEX_COLUMN_SIZE = 60;
    /**elemento grafica */
    final int CUSTOM_COLUMN_SIZE = 250;
    /**elemento grafica */
    final int DURATA_COLUMN_SIZE = 50;
    /**elemento grafica */
    final int TOTAL_FIXED_SIZE_COLUMN = BUTTON_COLUMN_SIZE+INDEX_COLUMN_SIZE+CUSTOM_COLUMN_SIZE+DURATA_COLUMN_SIZE; //misura ib pixel 


 /**costruttore: inizializza la tabella, impostando stili e colonne personalizzate
  * @param def se true bottone di default
  *@param add se true bottone per aggiungere canzoni
 */
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


 
/**metodo che aggiunge una colonna di pulsanti alla tabella.
*@param column colonna di bottoni
*@param buttonText label del bottone
*@param handler handler azioni del bottone
*/
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


 
/**metodo che gestisce un clic su un pulsante personalizzato
*@param  track variabile di tipo Track
*/
    private void customMethod(Track track) {
        System.out.println("Clicked on " + track.getName());
        System.out.println(track.toString());
    }


 
/**metodo che gestisce l'aggiunta di una traccia a una playlist
*@param  track variabile di tipo Track
*/
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


 
/**metodo che gestisce la rimozione di una traccia da una playlist
*@param  track variabile di tipo Track
*/
    private void removeTrackFromPlaylist(Track track) {
        TableView<Track> tmp = this; 
        VBox p = (VBox) tmp.getParent();
        tmp.getItems().remove(track);
        ((TableView<Track>) p.getChildren().get(2)).getItems().add(track);
    }



 /**interfaccia funzionale che dichiara un metodo handle per gestire i clic sui pulsanti*/
    private interface ButtonClickHandler {
        void handle(Track track);
    }
}




