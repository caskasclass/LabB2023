package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import util.FXMLLoaders;
import util.StyleManager;

public class ImagesWindow extends Stage{
    FXMLLoaders loader = new FXMLLoaders();
    StyleManager styleManager  = new StyleManager();

    public ImagesWindow() {
            Pane mainContaier = (Pane) loader.loadFXML("imagesWindow.fxml");
            //mainContaier.getStylesheets().add(styleManager.loadStyle("main.css"));
            initStyle(StageStyle.UNDECORATED);
            initStyle(StageStyle.TRANSPARENT);
            setResizable(false);
            setScene(new Scene(mainContaier,Color.TRANSPARENT));

            setOnShown(event ->{
                //contenuto completamente caricato
                //questa soluzione fixa il frame bianco all'avvio dell'applicazione.
                show();
            });
            
    }
}
