package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import jars.Playlist;
import jars.ServerInterface;
import jars.Track;

public class PlaylistModule {

    private ServerInterface si;

    public PlaylistModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }
    
    public void createPlaylist(Playlist p) throws RemoteException{
            si.createPlaylist(p);
    }

    public ArrayList<Playlist> setPlaylists() throws RemoteException{
        ArrayList<Playlist> plays = si.getAllPlaylist();   
        return plays;
    }

    public void deletePlaylist(Playlist p) throws RemoteException{
            si.deletePlayList(p);
    }

    public void deleteTrack(Playlist p, String track) throws RemoteException{
            si.deleteTrack(p, track);;
    }

    public Playlist getP(String title, String user) throws RemoteException{
        Playlist p = si.getPlaylist(title, user); 
        return p;
    }

    public ArrayList<Track> getAllTrack(ArrayList<String> strings, int begin, int end) throws RemoteException{
        ArrayList<Track> t =  si.getAllTrackInformation(strings, begin , end);
        return t;
    }

    public ArrayList<String> getAllIds() throws RemoteException{
        ArrayList<String> t =  si.getAllTrackId();
        return t;
    }
}
