import java.io.IOException;
import Session.WindowAppearance;
import javafx.stage.Stage;
import javafx.application.Application;
import util.ApplicationManager;



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
    public void start(Stage primaryStage) throws IOException {  
        WindowAppearance.calculateWindowDimensions();
        ApplicationManager startPoint = new ApplicationManager();

        startPoint.initialize();
    }
    @Override
    public void stop() throws Exception {
        //DB_Manager.shutdown(); // Chiude il pool di connessioni alla chiusura dell'applicazione
        super.stop();
    }
}
