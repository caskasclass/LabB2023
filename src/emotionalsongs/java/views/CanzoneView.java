package views;

import java.io.IOException;

import controllers.canzoneViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import jars.*;
import util.FXMLLoaders;

public class CanzoneView extends VBox{

    FXMLLoaders fxmlutil = new FXMLLoaders();


    public CanzoneView(Track c){

        FXMLLoader loader = fxmlutil.getLoader("CanzoneView.fxml");
        canzoneViewController controller= new canzoneViewController();
        controller.setCanzone(c);
        loader.setController(controller);
        try {
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
