package WindowsLoader;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import controllers.homeWindowController;
import controllers.signWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import util.FXMLLoaders;
import util.StyleManager;

public class SignWindow extends Stage{

    FXMLLoaders fxmlutil = new FXMLLoaders();
    StyleManager styleManager  = new StyleManager();

    public SignWindow(homeWindowController ref) {
             FXMLLoader loader =  fxmlutil.getLoader("signWindow.fxml");
             signWindowController controller = new signWindowController(ref);
             loader.setController(controller);
             try {
                Pane mainContainer = (Pane) loader.load();
                initStyle(StageStyle.UNDECORATED);
                initStyle(StageStyle.TRANSPARENT);
                setResizable(false);
                setScene(new Scene(mainContainer,Color.TRANSPARENT));

             } catch (Exception e) {
                e.printStackTrace();// TODO: handle exception
             }
            //mainContaier.getStylesheets().add(styleManager.loadStyle("main.css"));
            
            setOnShown(event ->{
                //contenuto completamente caricato
                //questa soluzione fixa il frame bianco all'avvio dell'applicazione.
                show();
            });
            
    }
    
}
