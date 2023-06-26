import java.net.URL;
import java.sql.Connection;
import connection.DBconnection;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Hello world!
 *
 */
public class emotionalsongs extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carica la finestra di caricamento
        URL file = getClass().getResource("view/loadingWindow.fxml");
        
        Parent loadingScreen = FXMLLoader.load(file);
        loadingScreen.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());

        // Crea una nuova finestra per la visualizzazione della finestra di caricamento
        Stage loadingStage = new Stage();
        loadingStage.initStyle(StageStyle.UNDECORATED);
        loadingStage.setScene(new Scene(loadingScreen));
        loadingStage.show();

        // Esegui la connessione al database e il prelievo delle tracce in un nuovo thread
        Task<Void> loadingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Connection con = DBconnection.getConnection();
                    System.out.println(con);
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Thread.sleep(   1000);
                //faccio cose con con 
                return null;
            }
        };

        // Aggiungi un listener per gestire l'evento di completamento del caricamento
        loadingTask.setOnSucceeded(event -> {
            // Chiudi la finestra di caricamento
            loadingStage.close();
            BorderPane root = new BorderPane();
            Label warning = new  Label("JavaFX applicaztion !!! ");
            root.setCenter(warning);
            Scene homeScene  = new Scene(root,600,400);
            // Mostra la finestra principale dell'applicazione
            primaryStage.setScene(homeScene);
            primaryStage.setTitle("Titolo della finestra");
            primaryStage.setResizable(false);
            primaryStage.show();
        });

        // Avvia il thread di caricamento
        Thread loadingThread = new Thread(loadingTask);
        loadingThread.start();
    }
}
