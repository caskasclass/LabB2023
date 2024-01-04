package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import jars.Playlist;
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

 *classe creata per interagire con una ServerInterface utile a gestire le playlist e le tracce musicali
 */
public class PlaylistModule {

    private ServerInterface si;

/**costruttore: inizializza oggetto <code>si</code> con metodo findServer
*@throws <code>RemoteException</code>,<code>NotBoundException</code> Eccezioni
*/
    public PlaylistModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }


    
/**metodo che crea una Playlist
*@param <code>p</code> parametro di tipo Playlist
*@throws <code>RemoteException</code> Eccezione
*/    
    public void createPlaylist(Playlist p) throws RemoteException{
            si.createPlaylist(p);
    }


    
/**metodo che crea un ArrayLsit con tutte le Playlist esistenti
*@throws <code>RemoteException</code> Eccezione
*@return <code>plays</code> ArrayList di tutte le Playlist 
*/ 
    public ArrayList<Playlist> setPlaylists() throws RemoteException{
        ArrayList<Playlist> plays = si.getAllPlaylist();   
        return plays;
    }



/**metodo che cancella una Playlist specifica
*@param <code>p</code> parametro di tipo Playlist
*@throws <code>RemoteException</code> Eccezione
*/ 
    public void deletePlaylist(Playlist p) throws RemoteException{
            si.deletePlayList(p);
    }



/**metodo che cancella una traccia specifica
*@param <code>p</code> parametro di tipo Playlist, <code>track</code> parametro di tipo String
*@throws <code>RemoteException</code> Eccezione
*/ 
    public void deleteTrack(Playlist p, String track) throws RemoteException{
            si.deleteTrack(p, track);;
    }


    
/**metodo che restituisce una determinata Playlist usandone il titolo e il nome dell'utente
*@param <code>title</code> parametro di tipo string, <code>user</code> parametro di tipo String
*@throws <code>RemoteException</code> Eccezione
*@return <code>p</code> oggetto di tipo Playlist 
*/ 
    public Playlist getP(String title, String user) throws RemoteException{
        Playlist p = si.getPlaylist(title, user); 
        return p;
    }


    
/**metodo che restituisce informazioni su tutte le tracce nell'intervallo specificato 
*@param <code>strings</code> parametro di tipo ArrayList<String> ,<code>begin</code> parametro di tipo int, <code>end</code> parametro di tipo int
*@throws <code>RemoteException</code> Eccezione
*@return <code>t</code> oggetto di tipo ArrayList<Track> 
*/ 
    public ArrayList<Track> getAllTrack(ArrayList<String> strings, int begin, int end) throws RemoteException{
        ArrayList<Track> t =  si.getAllTrackInformation(strings, begin , end);
        return t;
    }


    
/**metodo che restituisce  tutti gli identificatori delle tracce
*@throws <code>RemoteException</code> Eccezione
*@return <code>t</code> oggetto di tipo ArrayList<String> 
*/ 
    public ArrayList<String> getAllIds() throws RemoteException{
        ArrayList<String> t =  si.getAllTrackId();
        return t;
    }
}
