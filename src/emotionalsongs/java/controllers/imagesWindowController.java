/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
package controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**

    *Controller per la finestra di image picking per la playlist
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023

    */
public class imagesWindowController {
    /**elemento FXML */
    private ImageView image;
    /**Costruttore
     * @param img 
    */
    public imagesWindowController(ImageView img){
        this.image=img;
    }
    /**metodo chiusura finestra
     * @param e click event javafx 
    */
    public void closeWindow(MouseEvent e){
        ImageView imgview=null;
        if(e.getSource() instanceof ImageView){
            imgview = (ImageView)e.getSource();
        }

            if(imgview!=null)
            {
                Image im= imgview.getImage();
                image.setImage(im);
    
            }


        Stage stage = (Stage) imgview.getScene().getWindow();
        stage.close();
    }
}
