package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.FXMLLoaders;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class signUpViewController {
    
    @FXML
    private TextField cap;

    @FXML
    private TextField cf;

    @FXML
    private TextField citta;

    @FXML
    private Label loginButton;

    @FXML
    private TextField mail;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField password;

    @FXML
    private TextField residenza;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField username;

    public void openLogIn(MouseEvent e){
        System.out.println("\n\nCalcolo tempo\n\n");
        long start = System.currentTimeMillis();
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoaders loader = new FXMLLoaders();
        Pane pane = (Pane) loader.loadFXML("signWindow.fxml");
        Scene scene = new Scene(pane, Color.TRANSPARENT);
        stage.setScene(scene);
        long end = System.currentTimeMillis();
        System.out.println("\n\nTempo impiegato : " + (end - start) + " ms.\n\n");
        
        
    }
}
