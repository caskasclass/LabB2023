
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Session.WindowAppearance;
import javafx.stage.Stage;
import tmp.ServerInterface;
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
        /*Registry registry = java.rmi.registry.LocateRegistry.getRegistry("192.168.2.79", 8080);
        ServerInterface stub = null;
        try {
            stub = (ServerInterface) registry.lookup("SERVER");
            Scanner sc = new Scanner(System.in);
            System.out.println("Premi un tasto : ");
            stub.ciao();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
