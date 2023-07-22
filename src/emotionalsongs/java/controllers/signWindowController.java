package controllers;
import javafx.fxml.FXML;
import util.FXMLLoaders;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class signWindowController {

    @FXML
    private TextField idButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField passwordButt;

    @FXML
    private Label signUpButton;

    public void openSignUp(MouseEvent e){

        System.out.println("\n\nCalcolo tempo\n\n");
        long start = System.currentTimeMillis();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoaders loader = new FXMLLoaders();
        Pane pane = (Pane) loader.loadFXML("signUpView.fxml");
        Scene scene = new Scene(pane, Color.TRANSPARENT);
        stage.setScene(scene);
        long end = System.currentTimeMillis();
        System.out.println("\n\nTempo impiegato : " + (end - start) + " ms.\n\n");
       
    }

    public void closeWindow(MouseEvent e){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
