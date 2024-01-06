/**
 * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import WindowsLoader.HomeWindow;
import WindowsLoader.LoadingWindow;
import javafx.application.Platform;
import javafx.concurrent.Task;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe creata per gestire l'iniziallizzazione dell'applicazione, compresa la visualizzazione di una finestra di caricamento e la creazione di una finestra principale
 *N.B. il lavoro di inizializzazione pesante viene delegato a un task separato (UIInitializationTask) per garantire che l'interfaccia utente rimanga reattiva
 */
public class ApplicationManager {
    /**oggetto finestra di loading */
    private LoadingWindow loadingWindow;
    /**oggetto main finestra dell'applicazione */
    private HomeWindow homeWindow;

    /**start point dell'applicazione */
    public void initialize() {
        // metto a grafica la finestra di caricamento.
        loadingWindow = new LoadingWindow();
        loadingWindow.show();
        // istanzio un task che mi carica elementi pesanti nel mentre
        Task<Void> UIInitializationTask = createUIInitializationTask();

        // Serve per specificare cosa fare alla terminazione del task
        UIInitializationTask.setOnSucceeded(event -> {
            loadingWindow.close();
            homeWindow.show();
        });
        // Avvio il task
        Thread processingThread = new Thread(UIInitializationTask);
        processingThread.start();
    }



 /**metodo il quale restituisce un task che contiene la logica di inizializzazione dell'interfaccia utente
*@return task che contiene la logica di inizializzazione dell'interfaccia utente
*/
    private Task<Void> createUIInitializationTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {

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
