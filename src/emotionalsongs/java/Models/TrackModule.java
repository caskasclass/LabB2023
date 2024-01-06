/**
 * Contiene le classi necessarie a racchiudere tutti i servizi
 * implementati dal server e accessibili grazie a ServerInterface.
 * Ogni classe rappresenta un sottogruppo di servizi legati a utente, canzoni etc.
  * @package Models
 * @see package.emotionalsongs.java
 */
package Models;

import java.rmi.RemoteException;
import java.util.ArrayList;
import Session.ClientSession;
import jars.ChartData;
import jars.CommentSection;
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
    /**oggetto interfaccia per servizi server*/
    ServerInterface stub;

/**costruttore: inizializza oggetto  si  con metodo findServer*/
    public TrackModule() {
            stub = ServerFinder.findServer();
    }


    
/**metodo che inserisce un'emozione nel server
*@param  e  oggetto di tipo EmotionEvaluation
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
*@param  track_id  oggetto di tipo String,  user_id  oggetto di tipo String
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
*@param  track  oggetto di tipo Track
*@return  ee  oggetto di tipo EmotionEvaluation, null altrimenti
*/  
    public EmotionEvaluation getMyEmotions(Track track){
        
        try {
            EmotionEvaluation ee = stub.getMyEmotion(track, ClientSession.client.getUserid());
            return ee;
        } catch (RemoteException e) {
            System.err.println("Qualcosa è andato storto durante la creazione del registry");
            e.printStackTrace();
        }
        return null;
    }


    
/**metodo creato per ottenere tutte le emozioni associate a una traccia 
*@param  track  oggetto di tipo Track
*@return   contenente le informazioni riguardardo le emozioni
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

    public ArrayList<CommentSection> getAllComments(Track track){
        try {
            return stub.getAllComments(track);
        } catch (RemoteException e) {
            System.err.println("Qualcosa è andato storto durante la creazione del registry");
            e.printStackTrace();
        }
        return null;
    }

 

}
