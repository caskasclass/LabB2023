package Models;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import Session.ClientSession;
import jars.ChartData;
import jars.EmotionEvaluation;
import jars.ServerInterface;
import jars.Track;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe creata per la gestione delle emozioni associate a tracce musicali
 */
public class TrackModule {

    ServerInterface stub;

/**costruttore: inizializza oggetto <code>si</code> con metodo findServer*/
    public TrackModule() {
            stub = ServerFinder.findServer();
    }


    
/**metodo che inserisce un'emozione nel server
*@param <code>e</code> oggetto di tipo EmotionEvaluetion
*@throws <code>RemoteException</code> Eccezione
*/  
    public void insertEmotion(EmotionEvaluation e) {
        try {
            stub.insertEmotion(e);;
        } catch (RemoteException e1) {
            System.err.println("Qualcosa è andato storto durante l'inserimento dell'emozione");
            e1.printStackTrace();
        }
    }


    
/**metodo che verifica se la traccia selezionata è stata valutata dall'utente
*@param <code>track_id</code> oggetto di tipo String, <code>user_id</code> oggetto di tipo String
*@throws <code>RemoteException</code> Eccezione
*@return true se è stata valutata, false altrimenti
*/  
    public boolean checkIfRated(String track_id,String user_id){
        try {
            return stub.checkIfRated(track_id, user_id);
        } catch (RemoteException e1) {
            System.err.println("Qualcosa è andato storto durante l'inserimento dell'emozione");
            e1.printStackTrace();
        }
        return false;
    }


    
/**metodo creato per ottenere le emozioni associate a una traccia per l'utente corrente
*@param <code>track</code> oggetto di tipo Track
*@throws <code>RemoteException</code> Eccezione
*@return <code>ee</code> oggetto di tipo EmotionEvaluation, null altrimenti
*/  
    public HashMap<String,Integer> getMyEmotions(Track track){
        
        try {
            EmotionEvaluation ee = stub.getMyEmotion(track, ClientSession.client.getUserid());
            return ee.getEmozione();
        } catch (RemoteException e) {
            System.err.println("Qualcosa è andato storto durante la creazione del registry");
            e.printStackTrace();
        }
        return null;
    }


    
/**metodo creato per ottenere tutte le emozioni associate a una traccia 
*@param <code>track</code> oggetto di tipo Track
*@throws <code>RemoteException</code> Eccezione
*@return ArrayList<ChartData> contenente le informazioni riguardardo le emozioni
*/ 
    public ArrayList<ChartData> getAllEmotions(Track track){
        try {
            return stub.getAllEmotion(track);
        } catch (RemoteException e) {
            System.err.println("Qualcosa è andato storto durante la creazione del registry");
            e.printStackTrace();
        }
        return null;
    }

 

}
