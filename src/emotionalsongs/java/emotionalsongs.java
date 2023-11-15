import java.io.IOException;
import Session.WindowAppearance;
import javafx.stage.Stage;
import javafx.application.Application;
import util.ApplicationManager;

/**
 * Hello world!
 *
 */
public class emotionalsongs extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        WindowAppearance.calculateWindowDimensions();
        ApplicationManager startPoint = new ApplicationManager();
        /*try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);
            ServerInterface stub = null;
            stub = (ServerInterface) registry.lookup("SERVER");
            Scanner sc = new Scanner(System.in);
            System.out.println("Premi un tasto : ");
            stub.SayHi("Casca");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore di connessione ... ignorare per ora ...");
        }*/
        
        startPoint.initialize();

    }

    @Override
    public void stop() throws Exception {

        // DB_Manager.shutdown(); // Chiude il pool di connessioni alla chiusura
        // dell'applicazione
        super.stop();
    }
}
