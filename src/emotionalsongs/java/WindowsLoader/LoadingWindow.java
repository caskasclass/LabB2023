package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import util.FXMLLoaders;
import util.StyleManager;

public class LoadingWindow extends Stage {

    FXMLLoaders loader = new FXMLLoaders();
    StyleManager styleManager  = new StyleManager();

    public LoadingWindow() {
            StackPane mainContaier = (StackPane) loader.loadFXML("loadingWindow.fxml");
            //mainContaier.getStylesheets().add(styleManager.loadStyle("main.css"));
            initStyle(StageStyle.UNDECORATED);
            initStyle(StageStyle.TRANSPARENT);
            setResizable(false);
            setScene(new Scene(mainContaier));

            setOnShown(event ->{
                //contenuto completamente caricato
                //questa soluzione fixa il frame bianco all'avvio dell'applicazione.
                show();
            });
            
    }
}
