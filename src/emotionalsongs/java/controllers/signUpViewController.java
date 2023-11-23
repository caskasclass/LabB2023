package controllers;

import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import jars.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.FXMLLoaders;
import util.TableViewManager;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jars.ServerInterface;
import jars.Track;
import jars.User;

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

    public void closeWindow(MouseEvent e){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    public void registrazione(MouseEvent e) throws IOException {

        String[] s = { username.getText(), nome.getText(), cf.getText(),residenza.getText(),cap.getText(),citta.getText() ,mail.getText(),password.getText()};
        List<String> s2 = Arrays.asList(s);
        if(s2.contains("")){
            msgErr.setText("non hai compilato tutti i campi");
        }
        else if(!stringMatches(cap.getText(),"^\\d{5}$")){
            msgErr.setText("non hai inserito un CAP corretto");
        }
        else if(!stringMatches(cf.getText(),"^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")){
            msgErr.setText("il formato del codice fiscale non è corretto");
        }
        else if(!stringMatches(password.getText(),"^(?=.*\\d)[A-Za-z\\d]{6,}$")){
            msgErr.setText("la password deve avere aleno 6 caratteri di cui un numero");
        }
        else if(!stringMatches(mail.getText(),"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            msgErr.setText("il formato della mail non è corretto");
        }
        else{
            msgErr.setText("tutto corretto");
            User u = new User(username.getText(), nome.getText(), nome.getText(), cf.getText(),residenza.getText(),Integer.parseInt(cap.getText()),citta.getText() ,mail.getText(),password.getText());
            try {
            Registry r = LocateRegistry.getRegistry("localhost",1099);
            ServerInterface si = (ServerInterface) r.lookup("SERVER");
            si.registration(u);
            } catch (Exception ex) {
                System.out.println(ex);        }
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
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

       
}
