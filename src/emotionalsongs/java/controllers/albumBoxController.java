/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import jars.AlbumPreview;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
/**
 * Controller per la visualizzazione riquadro album
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 *
 */
public class albumBoxController {

    /**oggetto libreria jars */
    private AlbumPreview albumPreview; 
    /**elemento FXML */
    @FXML
    private HBox rootHbox;
    /**elemento FXML */
    @FXML
    private Label albumName;
    /**elemento FXML */
    @FXML
    private Label artistNames;
    /**elemento FXML */
    @FXML
    private VBox regionContainer;
    /**elemento FXML */
    @FXML
    private Region imgregion;

    /**Costruttore: inizializza oggetto albumPreview con un oggetto di classe AlbumPreview.jars
     * @param albumPreview
     */
    public albumBoxController(AlbumPreview albumPreview) {
        this.albumPreview = albumPreview;
    }
    
    /**Inizializza il file FXML */
    @FXML
    void initialize() {
        albumName.setText(albumPreview.getAlbumName()); 
        artistNames.setText(albumPreview.getAlbumArtists());
        imgregion.setStyle("-fx-background-image:url('"+albumPreview.getAlbumImg0()+"');");
    }

    
}
