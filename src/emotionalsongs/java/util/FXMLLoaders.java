package util;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class FXMLLoaders {

    public Parent loadFXML(String viewFile) {
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+viewFile));
        try {
            parent = loader.load();
        } catch (IOException e) {
            System.out.println("\nLoader Problems, Can't return the Parent node.\n");
            e.printStackTrace();
        }
        return parent;
    }

    public URL loadUrl(String viewFile) throws IOException {
        URL file = getClass().getResource("../view/"+viewFile);
        return file;
    }

    public FXMLLoader getLoader(String viewFile){
        return (new FXMLLoader(getClass().getResource("../view/"+viewFile)));
    }
}
