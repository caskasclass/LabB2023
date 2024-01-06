/**
 * Contiene le classi necessarie a racchiudere tutti i servizi
 * implementati dal server e accessibili grazie a ServerInterface.
 * Ogni classe rappresenta un sottogruppo di servizi legati a utente, canzoni etc.
  * @package Models
 * @see package.emotionalsongs.java
 */
package Models;

import java.util.ArrayList;

import jars.TrackDetails;
import jars.AlbumPreview;
import jars.ServerInterface;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe creata per interagire con una ServerInterface utile ad ottenere le tracce musicali e gli album migliori da server
 */
public class HomeModule {
    /**oggetto interfaccia per servizi server*/
    ServerInterface stub;

    
/**costruttore: inizializza l'oggetto  stub  */
    public HomeModule() {
        stub = ServerFinder.findServer();
    }


    
/**metodo  utilizzato per ottenere un ArrayList delle migliori tracce
*@return Arraylist di tracce, null altrimenti
*/
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


    
/**metodo  utilizzato per ottenere un ArrayList dei migliori album
*@return Arraylist di album, null altrimenti
*/
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
