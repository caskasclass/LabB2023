import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    public void start(Stage primaryStage) {
        Label center = new Label("Hello, JavaFX!");
        StackPane root = new StackPane(center);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("emotionalsongs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
