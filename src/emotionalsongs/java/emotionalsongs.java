
import java.io.IOException;
import java.util.Scanner;
import java.rmi.registry.*;
import Session.WindowAppearance;
import javafx.stage.Stage;
import javafx.application.Application;
import util.ApplicationManager;
import tmp.ServerInterface;

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
            System.out.println("\n\nHellooo\n\n\n");
            Registry registry = LocateRegistry.getRegistry("192.168.2.70", 9999);
            ServerInterface stub = null;
            stub = (ServerInterface) registry.lookup("SERVER");
            Scanner sc = new Scanner(System.in);
            System.out.println("Come ti chiami : ");
            String name = sc.nextLine();
            stub.ciao(name);
            sc.close();

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
