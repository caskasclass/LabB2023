package Models;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import pkg.*;
import jars.TrackDetails;

public class HomeModule {

    public HomeModule() {
        
    }

    public ArrayList<TrackDetails> getToptracks() {
       try {
        
            System.out.println("\n\nHellooo\n\n\n");
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", ServerInterface.PORT);
            ServerInterface stub = null;
            stub = (ServerInterface) registry.lookup("SERVER");
            //ArrayList<TrackDetails> topTracks = stub.;
            System.out.println("Size of array  : "+topTracks.size());
            // close connection
            return topTracks;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore di connessione ... ignorare per ora ...");
        }
        return null;

    }
}
