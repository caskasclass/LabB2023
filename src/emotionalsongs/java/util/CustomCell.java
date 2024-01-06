/**
  * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import jars.Track;

import java.util.concurrent.CompletableFuture;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *Classe creata per gestione  di cella personalizzata per l'utilizzo in una TableView 
 *Questa cella mostra dettagli di traccia, inclusa un'immagine, il nome della traccia e il nome dell'autore
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

 */
public class CustomCell extends TableCell<Track, Void> {
    /**elemento FXML */
    private final HBox hbox = new HBox();
    /**elemento FXML */
    private final ImageView imageView = new ImageView();
    /**elemento FXML */
    private final Label trackNameLabel = new Label();
    /**elemento FXML */
    private final Label authorNameLabel = new Label();

 /**costruttore della classe*/
    public CustomCell() {
        hbox.getStyleClass().add("hboxCustomColumn");
        hbox.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        hbox.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        hbox.setSpacing(5);
        hbox.setTranslateY(2);
        VBox tracksdetails = new VBox();
        tracksdetails.getStyleClass().add("track_details");
        authorNameLabel.getStyleClass().add("authorsNames");
        trackNameLabel.getStyleClass().add("trackName");
        tracksdetails.getChildren().addAll(trackNameLabel, authorNameLabel);
        hbox.getChildren().addAll(imageView, tracksdetails);

    }


 
 /**metodo chiamato ogni volta che la cella deve essere aggiornata
*@param item parametro di tipo Void
*@param empty parametro di tipo boolean
*/   
    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            Track track = getTableView().getItems().get(getIndex());

            if (track != null) {
                String url = track.getAlbum_img2S();
                ImageLoader imageLoader = new ImageLoader();
                CompletableFuture<Image> imageFuture = imageLoader.loadImageAsync(url);
                
                imageFuture.thenAcceptAsync(image ->{
                    if(image != null){
                        imageView.setImage(image);
                        imageView.setFitHeight(43);
                        imageView.setFitWidth(43);
                        imageView.setPreserveRatio(false);
                    }else
                        imageView.setImage(imageLoader.getPlaceHolderImage());

                });

                //Image image = new Image(url);

                // Set track name and author
                trackNameLabel.setText(track.getName());
                authorNameLabel.setText(track.getArtist_name());
            }

            setGraphic(hbox);
        }
    }
}
