package controllers;

import java.lang.String;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import Models.UserModule;
import Session.ClientSession;
import Threads.ResizeHandler;
import javafx.fxml.FXML;
import util.FXMLLoaders;
import views.HomeView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jars.*;
<<<<<<< HEAD
=======

>>>>>>> 44b3b114013f7b8ae2d05290897c2c0f15a6a585

public class signWindowController {

    @FXML
    private TextField id;

    @FXML
    private Button loginButton;

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField password;

    @FXML
    private Label signUpButton;

    @FXML
    private Label msgErr;

    homeWindowController ref = null;

<<<<<<< HEAD
    public signWindowController(homeWindowController ref) {
        this.ref = ref;

    }

    public void openSignUp(MouseEvent e) {
=======
    public signWindowController(homeWindowController ref){
        this.ref = ref;
    }

    public void openSignUp(MouseEvent e){
>>>>>>> 44b3b114013f7b8ae2d05290897c2c0f15a6a585

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

    public void accesso(MouseEvent e) throws IOException {

        String[] s = { id.getText(), password.getText() };
        List<String> s2 = Arrays.asList(s);
        if (s2.contains("")) {
            msgErr.setText("non hai compilato tutti i campi");
        } else if (!stringMatches(password.getText(), "^(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            msgErr.setText("la password deve avere aleno 6 caratteri di cui un numero");
        } else {
            try {
            UserModule um = new UserModule();
            User u = um.login(id.getText(), password.getText());
            if (u == null){
                msgErr.setText("utente non trovato");
            }
            else{
                ClientSession.client= u;
                System.out.println(ClientSession.client.getCity());
                System.out.println(ClientSession.client.getUserid());
                ref.updateWindow();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                ref.centerScrollPane.setContent(new HomeView(ResizeHandler.getCenterWidth()));
            }
            
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
        }

    }

    private boolean stringMatches(String data, String regex) {
        // Compila il pattern regex
        Pattern pattern = Pattern.compile(regex);
        // Verifica se il codice fiscale corrisponde al pattern regex
        return pattern.matcher(data).matches();
    }

    public void closeWindow(MouseEvent e) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
