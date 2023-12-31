/**
 * Contiene le classi necessarie a racchiudere tutti i servizi
 * implementati dal server e accessibili grazie a ServerInterface.
 * Ogni classe rappresenta un sottogruppo di servizi legati a utente, canzoni etc.
  * @package Models
 * @see package.emotionalsongs.java
 */
package Models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import jars.Track;
import jars.ServerInterface;
import java.rmi.RemoteException;
/**
 *Classe creata e utilizzata per la ricerca di tracce musicali
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 */

public class AllTrackModule {
    /**oggetto interfaccia per servizi server*/
    private ServerInterface si;
    /**elemento task asincroni */
    private ExecutorService executorService;

    
/**costruttore: inizializza oggetto  si  con metodo findServer*/
    public AllTrackModule() {
            si = ServerFinder.findServer();
            executorService = Executors.newFixedThreadPool(5); 
    }
    

    
/**metodo per ricerca asincrona di tracce in base al nome della traccia stessa
*@param  input  stringa in input 
*@return oggetto che rappresenta il risultato della ricerca 
*/
    public Future<ArrayList<Track>> searchTracksNameAsync(String input) {
        return executorService.submit(() -> searchTracksName(input));
    }
    

    
/**metodo per ricerca asincrona di tracce in base al nome dell'autore
*@param  input  stringa in input 
*@return oggetto che rappresenta il risultato della ricerca 
*/
    public Future<ArrayList<Track>> searchTracksAutorAsync(String input) {
        return executorService.submit(() -> searchTracksAutor(input));
    }


    
/*chiusura del pool di thread <strong>'executorService'</strong>*/
    public void shutdown() {
        executorService.shutdown();
    }



/**metodo per ricerca di tracce in base al nome della traccia utilizzando l'oggetto <strong>si</strong>
*@param  input  stringa in input 
*@throws  RemoteException  Eccezione
*@return ArrayList di tracce come risultato della ricerca
*/
    public ArrayList<Track> searchTracksName(String input) throws RemoteException {
        if (input != null) {
            ArrayList<String> trackIds = si.getTrackId(input);
            return getAllTrackInformation(trackIds);
        }
        return new ArrayList<>(); // Restituisci una lista vuota in caso di input nullo
    }



/**metodo per ricerca di tracce in base al nome dell'autore utilizzando l'oggetto <strong>si</strong>
*@param  input  stringa in input 
*@throws  RemoteException  Eccezione
*@return ArrayList di tracce come risultato della ricerca
*/
    public ArrayList<Track> searchTracksAutor(String input) throws RemoteException {
        if (input != null) {
            String[] inputArray = input.split(" ");
            int numericIndex = findNumericIndex(inputArray);
            if (numericIndex != -1) {
                int date = Integer.parseInt(inputArray[numericIndex]);
                inputArray[numericIndex] = "";
                String modifiedInput = String.join(" ", inputArray).trim();
                ArrayList<String> trackIds = si.getTrackId(modifiedInput, date);
                return getAllTrackInformation(trackIds);
            }
        }
        return new ArrayList<>(); // Restituisci una lista vuota in caso di input nullo o non valido
    }


    
/**metodo privato per ottenere tutte le informazioni per traccia
*@param trackIds  ArrayLIst di identificatori di tracce 
*@return ArrayList di tracce ottenute dalla chiamata del metodo getAllTrackInformation
*/
    private ArrayList<Track> getAllTrackInformation(ArrayList<String> trackIds) {
        try {
            if (trackIds != null && !trackIds.isEmpty()) {
                return si.getAllTrackInformation(trackIds, 0, trackIds.size());
            } else {
                return new ArrayList<>(); // Restituisci una lista vuota se trackIds è nullo o vuoto
            }
        } catch (RemoteException e) {
            // Gestisci l'eccezione in modo appropriato, ad esempio:
            throw new RuntimeException("Errore durante la chiamata a getAllTrackInformation", e);
        }
    }


    
/**metodo privato per ottenere l'indice della prima stringa numerica nell'array
*@param  array  Array di stringhe
*@return indice dell'array altrimenti -1 
*/
    private static int findNumericIndex(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (isNumeric(array[i])) {
                return i;
            }
        }
        return -1;
    }



/**metodo privato utilizzato per accertarsi se la stringa è numerica
*@param  str str  stringa
*@return true se stringa è numerica, false altrimenti 
*/
    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
