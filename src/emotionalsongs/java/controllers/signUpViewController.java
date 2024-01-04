package controllers;

import java.lang.String;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import Models.UserModule;
import Session.Globals;
import Threads.ResizeHandler;
import jars.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import views.HomeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class signUpViewController {

    @FXML
    private TextField cap;

    @FXML
    private TextField cf;

    @FXML
    private TextField citta;

    @FXML
    private Label msgErr;

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

    public void closeWindow(MouseEvent e) {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        stage.close();
    }

    public void registrazione(MouseEvent e) throws IOException {

        String[] s = { username.getText(), nome.getText(), cf.getText(), residenza.getText(), cap.getText(),
                citta.getText(), mail.getText(), password.getText() };
        List<String> s2 = Arrays.asList(s);
        if (s2.contains("")) {
            msgErr.setText("non hai compilato tutti i campi");
        } else if (!stringMatches(cap.getText(), "^\\d{5}$")) {
            msgErr.setText("non hai inserito un CAP corretto");
        } else if (!stringMatches(cf.getText(), "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")) {
            msgErr.setText("il formato del codice fiscale non è corretto");
        } else if (!stringMatches(password.getText(), "^(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            msgErr.setText("la password deve avere aleno 6 caratteri di cui un numero");
        } else if (!stringMatches(mail.getText(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            msgErr.setText("il formato della mail non è corretto");
        } else {
            User u = new User(username.getText(), nome.getText(), nome.getText(), cf.getText(), residenza.getText(),
                    Integer.parseInt(cap.getText()), citta.getText(), mail.getText(), password.getText());
            try {
                UserModule um = new UserModule();
                if (um.alreadyExist(u)) {
                    msgErr.setText("utente già registrato, cambiare username o mail");
                } else {
                    um.registration(u);
                    Stage stage = (Stage) signUpButton.getScene().getWindow();
                    stage.close();
                    Globals.getRootFrame().setContent(new HomeView(ResizeHandler.getCenterWidth()));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    private boolean stringMatches(String data, String regex) {
        // Compila il pattern regex
        Pattern pattern = Pattern.compile(regex);
        // Verifica se il codice fiscale corrisponde al pattern regex
        return pattern.matcher(data).matches();
    }

}
