package util;

import WindowsLoader.HomeWindow;
import WindowsLoader.LoadingWindow;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class ApplicationManager {
    private LoadingWindow loadingWindow;
    private HomeWindow homeWindow;

    // start point dell'applicazione
    public void initialize() {
        // metto a grafica la finestra di caricamento.
        loadingWindow = new LoadingWindow();
        loadingWindow.show();
        // istanzio un task che mi carica elementi pesanti nel mentre
        Task<Void> UIInitializationTask = createUIInitializationTask();

        // Sever per specificare cosa fare alla terminazione del task
        UIInitializationTask.setOnSucceeded(event -> {
            loadingWindow.close();
            homeWindow.show();

            // qui c'èra il proxy
        });
        // Avvio il task
        Thread processingThread = new Thread(UIInitializationTask);
        processingThread.start();
    }

    // il return della logica task
    private Task<Void> createUIInitializationTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                // elaborazione pesante ? potrebbe essere

                Platform.runLater(() -> {
                    long start = System.currentTimeMillis();
                    System.out.println("start home creation ");
                    homeWindow = new HomeWindow();
                    System.out.println("finished home creation ");
                    long end = System.currentTimeMillis();
                    System.out.println("Time di creazione finestra : " + (end - start) + "ms");
                });

                return null;
            }
        };
    }
}
