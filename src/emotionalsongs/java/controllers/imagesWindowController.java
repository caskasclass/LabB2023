package controllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class imagesWindowController {

    public void closeWindow(MouseEvent e){
        //String path="";
        ImageView imgview=null;
        if(e.getSource() instanceof ImageView){
            imgview = (ImageView)e.getSource();
        }
            /*
             * if(imgview!=null)
            {
               Image img= imgview.getImage();
               path = img.getUrl();
    
            }
             */

        Stage stage = (Stage) imgview.getScene().getWindow();
        stage.close();
    }
}
