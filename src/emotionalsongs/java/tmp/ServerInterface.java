package tmp;

import java.rmi.*;
/**
import tmp.Album;
import tmp.Emotion;
import tmp.Playlist;
import tmp.Track;
import tmp.User;


 *
 * @author lorenzo
 */
public interface ServerInterface extends Remote {

    int PORT = 8080;

   /*User access(String strAccess, String psw) throws RemoteException;

    void createPlaylist(Playlist p) throws RemoteException;

    ArrayList<String> getAlbumId(String name) throws RemoteException;

    ArrayList<String> getAlbumId(String artist, int year) throws RemoteException;

    ArrayList<Album> getAllAlbumInformation(ArrayList<String> trackId, int begin, int end) throws RemoteException;

    ArrayList<Track> getAllTrackInformation(Playlist p, int begin, int end) throws RemoteException;

    ArrayList<Emotion> getEmotion(Track track) throws RemoteException;

    Playlist getPlaylist(String title, User user) throws RemoteException;

    ArrayList<String> getTrackId(String title) throws RemoteException;

    ArrayList<String> getTrackId(String artist, int year) throws RemoteException;

    ArrayList<Track> gtAllTrackInformation(ArrayList<String> trackId, int begin, int end) throws RemoteException;

    void insertEmotion(Emotion e) throws RemoteException;

    void registration(User u) throws RemoteException;*/
    
    String ciao(String name ) throws RemoteException;
    
}
