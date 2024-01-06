import java.io.IOException;
import Session.WindowAppearance;
import javafx.stage.Stage;
import javafx.application.Application;
import util.ApplicationManager;


/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *main class dell'applicazione
 */
public class emotionalsongs extends Application {
    /**Main*/
    public static void main(String[] args) {
        launch(args);
    }
    /**application start*/
    @Override
    public void start(Stage primaryStage) throws IOException {
        WindowAppearance.calculateWindowDimensions();
        ApplicationManager startPoint = new ApplicationManager();
        startPoint.initialize();

    }
    /**application stop*/
    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
