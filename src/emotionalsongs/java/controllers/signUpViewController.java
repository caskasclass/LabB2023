/**
 * Contiene le classi controller necessarie a
 * gestire le views e finestre dell'applicazione.
  * @package controllers
 * @see package.emotionalsongs.java
 */
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
/**
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
    * 
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0

    *classe creata e utilizzata per la dinestra di registrazione
    */
public class signUpViewController {
    /**elemento FXML */
    @FXML
    private TextField cap;
    /**elemento FXML */
    @FXML
    private TextField cf;
    /**elemento FXML */
    @FXML
    private TextField citta;
    /**elemento FXML */
    @FXML
    private Label msgErr;
    /**elemento FXML */
    @FXML
    private TextField mail;
    /**elemento FXML */
    @FXML
    private TextField nome;
    /**elemento FXML */
    @FXML
    private PasswordField password;
    /**elemento FXML */
    @FXML
    private TextField residenza;
    /**elemento FXML */
    @FXML
    private Button signUpButton;
    /**elemento FXML */
    @FXML
    private TextField username;
    
    /**metodo per registrazione utente
     * @param e click event javafx 
     */
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
    /**metodo check delle stringhe inserite dall'utente
     * @param data stringa
     * @param regex pattern per il match 
     */
    private boolean stringMatches(String data, String regex) {
        // Compila il pattern regex
        Pattern pattern = Pattern.compile(regex);
        // Verifica se il codice fiscale corrisponde al pattern regex
        return pattern.matcher(data).matches();
    }
    /**metodo chiusura finestra
     * @param e click event javafx 
     */
    public void closeWindow(MouseEvent e) {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        stage.close();
    }

}
