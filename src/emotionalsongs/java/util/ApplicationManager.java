package util;

import WindowsLoader.HomeWindow;
import WindowsLoader.LoadingWindow;
import connection.Proxy;
import javafx.concurrent.Task;


public class ApplicationManager {    
    private LoadingWindow loadingWindow;



    //start point dell'applicazione 
    public void initialize(){
        //metto a grafica la finestra di caricamento. 
        loadingWindow = new LoadingWindow();
        loadingWindow.show();
        //istanzio un task che mi carica elementi pesanti nel mentre
        Task<Void> UIInitializationTask = createUIInitializationTask();

        //Sever per specificare cosa fare alla terminazione del task 
        UIInitializationTask.setOnSucceeded(event ->{
            loadingWindow.close();
            HomeWindow homeWindow = new HomeWindow();
            
            homeWindow.show();

            //Connessione al server funziona manca solo da implementare l'interfaccia nel Proxy
            Proxy localProxy = new Proxy();
            localProxy.getStatus();
            localProxy.close();
            
        });
        //Avvio il task 
        Thread processingThread = new Thread(UIInitializationTask);
        processingThread.start();
    }
    //il return della logica task
    private Task<Void> createUIInitializationTask(){
        return new Task<>(){
            @Override
            protected Void call() throws Exception{
                //elaborazione pesante 
                System.out.println("Inizio Elaborazione ... ");
                Thread.sleep(3000);
                System.out.println("Fine Elaborazione. ");
                return null;
            }
        };
    }
}
