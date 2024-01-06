/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import Session.WindowStyle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;
/**
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
    * 
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0

    *controller per i riquadri delle playlist
    */
public class playlistBoxController {
    /**elemento grafica */
    private boolean focusEnabler;
    /**elemento grafica */
    public String image = null;
    /**elemento grafica */
    public String title = null;
    /**elemento grafica */
    public String user = null;

    /**elemento FXML */
    @FXML
    private VBox vboxroot;
    /**elemento FXML */
    @FXML
    private Label playlistName;
    /**elemento FXML */
    @FXML
    private Label playlistOwner;
    /**elemento FXML */
    @FXML
    private VBox container_for_ragion;
    /**elemento FXML */
    @FXML
    private Region playlistBox_image;
    /**Inizializza file FXML */
    @FXML
    void initialize() {

        playlistName.setText(title);
        playlistOwner.setText(user);
        double padding = vboxroot.getPadding().getLeft() * 2;
        container_for_ragion.minHeightProperty().bind(vboxroot.widthProperty().subtract((padding)));
        playlistBox_image.setStyle("-fx-background-image:url("+ image + ");");

    }
    /**metodo grafico
     * @param tf 
     */
    public void setFocusaEnabler(Boolean tf) {
        focusEnabler = tf;
    }

    /**metodo grafico
     * @param e click event javafx 
     */
    public void captureColor(MouseEvent e) {
        if (focusEnabler) {
            Color captured = ColorsManager.getDominantColor(playlistBox_image.getBackground().getImages().get(0).getImage());
            WindowStyle.setColor(captured);
        }
    }
}
