/**
 * Contiene le classi necessarie a racchiudere tutti i servizi
 * implementati dal server e accessibili grazie a ServerInterface.
 * Ogni classe rappresenta un sottogruppo di servizi legati a utente, canzoni etc.
  * @package Models
 * @see package.emotionalsongs.java
 */
package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import jars.Playlist;
import jars.ServerInterface;
import jars.Track;
/**
 *Classe creata per interagire con una ServerInterface utile a gestire le playlist e le tracce musicali

 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 */
public class PlaylistModule {
    /**oggetto interfaccia per servizi server*/
    private ServerInterface si;

/**costruttore: inizializza oggetto  si  con metodo findServer
*@throws  RemoteException Eccezione
*@throws  NotBoundException  Eccezione
*/
    public PlaylistModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }


    
/**metodo che crea una Playlist
*@param  p  parametro di tipo Playlist
*@throws  RemoteException  Eccezione
*/    
    public void createPlaylist(Playlist p) throws RemoteException{
            si.createPlaylist(p);
    }


    
/**metodo che crea un ArrayLsit con tutte le Playlist esistenti
*@throws  RemoteException  Eccezione
*@return  plays  ArrayList di tutte le Playlist 
*/ 
    public ArrayList<Playlist> setPlaylists() throws RemoteException{
        ArrayList<Playlist> plays = si.getAllPlaylist();   
        return plays;
    }



/**metodo che cancella una Playlist specifica
*@param  p  parametro di tipo Playlist
*@throws  RemoteException  Eccezione
*/ 
    public void deletePlaylist(Playlist p) throws RemoteException{
            si.deletePlayList(p);
    }



/**metodo che cancella una traccia specifica
*@param  p  parametro di tipo Playlist,  track  parametro di tipo String
*@throws  RemoteException  Eccezione
*/ 
    public void deleteTrack(Playlist p, String track) throws RemoteException{
            si.deleteTrack(p, track);;
    }


    
/**metodo che restituisce una determinata Playlist usandone il titolo e il nome dell'utente
*@param  title  parametro di tipo string,  user  parametro di tipo String
*@throws  RemoteException  Eccezione
*@return  p  oggetto di tipo Playlist 
*/ 
    public Playlist getP(String title, String user) throws RemoteException{
        Playlist p = si.getPlaylist(title, user); 
        return p;
    }


    
/**metodo che restituisce informazioni su tutte le tracce nell'intervallo specificato 
*@param  strings  parametro list di String , begin  parametro di tipo int,  end  parametro di tipo int
*@throws  RemoteException  Eccezione
*@return  t  
*/ 
    public ArrayList<Track> getAllTrack(ArrayList<String> strings, int begin, int end) throws RemoteException{
        ArrayList<Track> t =  si.getAllTrackInformation(strings, begin , end);
        return t;
    }


    
/**metodo che restituisce  tutti gli identificatori delle tracce
*@throws  RemoteException  Eccezione
*@return  t  
*/ 
    public ArrayList<String> getAllIds() throws RemoteException{
        ArrayList<String> t =  si.getAllTrackId();
        return t;
    }
}
