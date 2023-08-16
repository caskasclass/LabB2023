package util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tmp.Canzone;

public class TableViewManager extends TableView<Canzone> {

    public TableViewManager(/*boolean button*/) {
        super();
        this.getStyleClass().add("tableView");
        TableColumn<Canzone, Integer> indexColumn = new TableColumn<>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setMaxWidth(50);
        this.getColumns().add(indexColumn);
        indexColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(cellData.getValue()) + 1));
        TableColumn<Canzone, String> trackName = new TableColumn<>("Name");
        trackName.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        trackName.setMinWidth(75);
        trackName.setPrefWidth(80); // in % ?, gia.. va fatto. Per ora rimane cosi tho 
        trackName.setMaxWidth(85);
        TableColumn<Canzone, String> album = new TableColumn<>("Album");
        album.setCellValueFactory(new PropertyValueFactory<>("trackAlbum"));
        album.setMinWidth(75);
        album.setPrefWidth(80); // in % ?, gia.. va fatto. Per ora rimane cosi tho 
        album.setMaxWidth(85);
        this.getColumns().add(trackName);
        this.getColumns().add(album);
        
    }

}
