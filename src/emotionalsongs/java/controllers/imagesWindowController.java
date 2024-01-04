package controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class imagesWindowController {

    private ImageView image;
    public imagesWindowController(ImageView img){
        this.image=img;
    }

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
