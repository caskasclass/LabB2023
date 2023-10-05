package views;

import java.io.IOException;

import controllers.canzoneViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import tmp.Canzone;
import util.FXMLLoaders;

public class CanzoneView extends VBox{

    FXMLLoaders fxmlutil = new FXMLLoaders();
    Canzone canzone= null;

    public CanzoneView(Canzone c){
        canzone=c;
    }
   

    public CanzoneView(){

        FXMLLoader loader = fxmlutil.getLoader("CanzoneView.fxml");
        canzoneViewController controller= new canzoneViewController();

        controller.setCanzone(canzone);

        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
    
}
