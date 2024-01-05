package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

import controllers.imagesWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import util.FXMLLoaders;
import util.StyleManager;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe creata per estendere la classe Stage di JavaFX e rappresenta una finestra per visualizzare immagini
 */
public class ImagesWindow extends Stage {
    FXMLLoaders fxmlutil = new FXMLLoaders();
    StyleManager styleManager = new StyleManager();

    
/**costruttore: accetta un'immagine da visualizzare, crea un controller per la gestione della finestra
*inizializza lo stile della finestra e la rende non ridimensionabile
*/
    public ImagesWindow(ImageView img) {
        FXMLLoader loader = fxmlutil.getLoader("imagesWindow.fxml");
        imagesWindowController controller = new imagesWindowController(img);
        loader.setController(controller);
        try {
            
            Pane root = (Pane) loader.load();
            initStyle(StageStyle.UNDECORATED);
            initStyle(StageStyle.TRANSPARENT);
            setResizable(false);
            setScene(new Scene(root, Color.TRANSPARENT));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOnShown(event -> {

            show();
        });

    }
}
