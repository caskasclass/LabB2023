package Models;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import jars.TrackDetails;
import jars.ServerInterface;

public class HomeModule {

    public HomeModule() {
        
    }

    public ArrayList<TrackDetails> getToptracks() {
       try {
            System.out.println("\nSono nel metodo getTopTracks() 1");
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", ServerInterface.PORT);
            System.out.println("\nSono nel metodo getTopTracks() 2");
            ServerInterface stub = null;
            System.out.println("\nSono nel metodo getTopTracks() 3");
            stub = (ServerInterface) registry.lookup("SERVER");
            System.out.println("\nSono nel metodo getTopTracks() 4");
            ArrayList<TrackDetails> topTracks = stub.getTopTracks();
            System.out.println("\nSono nel metodo getTopTracks() 5");
            System.out.println("Size of array  : "+topTracks.size());
            System.out.println("\nSono nel metodo getTopTracks() 6");
            // close connection
            return topTracks;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore di connessione ... ignorare per ora ...");
        }
        return null;

    }
}
