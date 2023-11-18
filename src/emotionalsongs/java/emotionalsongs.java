
import java.io.IOException;
import java.rmi.registry.*;
import Session.WindowAppearance;
import jars.ServerInterface;
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
        try {
            System.out.println("\n\nHellooo\n\n\n");
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", ServerInterface.PORT);
            ServerInterface stub = null;
            stub = (ServerInterface) registry.lookup("SERVER");
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
