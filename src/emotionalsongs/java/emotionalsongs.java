
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Session.WindowAppearance;
import javafx.stage.Stage;
import pkg.ServerInterface;
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
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerInterface stub = null;
            stub = (ServerInterface) registry.lookup("SERVER");
            System.out.println("Premi un tasto : ");
            stub.ciao();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore di connessione ... ignorare per ora ...");
        }
        
        startPoint.initialize();

    }

    @Override
    public void stop() throws Exception {

        // DB_Manager.shutdown(); // Chiude il pool di connessioni alla chiusura
        // dell'applicazione
        super.stop();
    }
}
