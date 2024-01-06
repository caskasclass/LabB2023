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
/**
    * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
    * 
    * @author Beatrice Bastianello, matricola 751864, VA
    * @author Lorenzo Barbieri  , matricola 748695, VA
    * @author Filippo Storti , matricola 749195, VA
    * @author Nazar Viytyuk, matricola 748964, VA
    * @version 1.0

    *controller per la finestra di login
    */
public class signWindowController {
    /**elemento FXML */
    @FXML
    private TextField id;
    /**elemento FXML */
    @FXML
    private Button loginButton;
    /**elemento FXML */
    @FXML
    private Button closeButton;
    /**elemento FXML */
    @FXML
    private PasswordField password;
    /**elemento FXML */
    @FXML
    private Label signUpButton;
    /**elemento FXML */
    @FXML
    private Label msgErr;
    /**oggetto controller*/
    homeWindowController ref = null;
    /**Costruttore
     * @param ref 
     */
    public signWindowController(homeWindowController ref){
        this.ref = ref;
    }
    /**metodo apertura finestra registrazione
     * @param e click event javafx 
     */
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
    /**metodo login utente
     * @param e click event javafx 
     */
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

    /**metodo check delle stringhe inserite dall'utente
    * @param data stringa
    * @param regex pattern per il match 
    */
    private boolean stringMatches(String data, String regex) {

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(data).matches();
    }
    
    /**metodo chiusura finestra
     * @param e click event javafx 
     */
    public void closeWindow(MouseEvent e) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
