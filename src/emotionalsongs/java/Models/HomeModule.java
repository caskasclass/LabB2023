package Models;

import java.util.ArrayList;

import jars.TrackDetails;
import jars.AlbumPreview;
import jars.ServerInterface;

public class HomeModule {

    ServerInterface stub;

    public HomeModule() {
        stub = ServerFinder.findServer();
    }

    public ArrayList<TrackDetails> getToptracks() {
       try {

            ArrayList<TrackDetails> topTracks = stub.getTopTracks();

            System.out.println("Size of array  : "+topTracks.size());
            // close connection
            return topTracks;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore di connessione ... ignorare per ora ...");
        }
        return null;

    }

     public ArrayList<AlbumPreview> getTopAlbums() {
        try {
            System.out.println("\nSono nel metodo getTopAlbums() 1");
            ArrayList<AlbumPreview> topAlbums = stub.getTopAlbums();
            System.out.println("\nSono nel metodo getTopAlbums() 2");
            System.out.println("Size of array  : "+topAlbums.size());
            System.out.println("\nSono nel metodo getTopAlbums() ");
            // close connection
            return topAlbums;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore di connessione ... ignorare per ora ...");
            return null;
        }
    }
}
