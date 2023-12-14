package controllers;
import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import Session.ClientSession;
import javafx.fxml.FXML;
import util.FXMLLoaders;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pkg.*;


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

    public void accesso(MouseEvent e) throws IOException {

        String[] s = {id.getText(), password.getText()};
        List<String> s2 = Arrays.asList(s);
        if(s2.contains("")){
            msgErr.setText("non hai compilato tutti i campi");
        }
        else if(!stringMatches(password.getText(),"^(?=.*\\d)[A-Za-z\\d]{6,}$")){
            msgErr.setText("la password deve avere aleno 6 caratteri di cui un numero");
        }
        else{
            try {
            Registry r = LocateRegistry.getRegistry("localhost",1099);
            ServerInterface si = (ServerInterface) r.lookup("SERVER");
            User u = si.access(id.getText(), password.getText());
            if (u == null){
                msgErr.setText("utente non trovato");
            }
            else{
                ClientSession.client = u;
                System.out.println(u.getCity());
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            }
            
            } catch (Exception ex) {
                System.out.println(ex);        }
            
        }

        /*
            else {
            User newuser = new User(btn_username.getText(), btn_passwd.getText(), btn_mail.getText(), btn_nome.getText().toLowerCase(), btn_cf.getText().toUpperCase(), btn_ind.getText().toLowerCase());
            ArrayList<User> users = UserManager.readUsers();
            if(users.contains(newuser)){
                msgErr.setText("utente già registrato");
            }
            else {
                users.add(newuser);
                Stage stage = (Stage) btn_registra.getScene().getWindow(); // chiusura della finestra
                stage.close();
            
            }
            UserManager.getUsers(users); 
            
        }  */    
        
        
        }

        private boolean stringMatches(String data, String regex){
            // Compila il pattern regex
            Pattern pattern = Pattern.compile(regex);
            // Verifica se il codice fiscale corrisponde al pattern regex
            return pattern.matcher(data).matches();
        }

    public void closeWindow(MouseEvent e){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
