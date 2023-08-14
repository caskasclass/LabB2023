package views;

import java.io.IOException;

import controllers.canzoneViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import util.FXMLLoaders;

public class CanzoneView extends VBox{

    FXMLLoaders fxmlutil = new FXMLLoaders();


   

    public CanzoneView(){

        FXMLLoader loader = fxmlutil.getLoader("CanzoneView.fxml");
        canzoneViewController controller= new canzoneViewController();
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    
}
