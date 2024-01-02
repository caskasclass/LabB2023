package Models;

import java.util.ArrayList;

import jars.ServerInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import jars.Track;

public class AllTrackModule {

    private ServerInterface si;

    public AllTrackModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }

    public ArrayList<Track> searchTracksName(String input) throws RemoteException {
        if(input != null) {
            ArrayList<String> trackid = si.getTrackId(input);
            return si.getAllTrackInformation(trackid, 0, trackid.size());
        }
        return null;
    }

    public ArrayList<Track> searchTracksAutor(String input) throws RemoteException {
        if(input != null) {
            String[] tmp = input.split(" ");
            if(findNumericIndex(tmp) != -1) {
                int date = Integer.parseInt(tmp[findNumericIndex(tmp)]);
                tmp[findNumericIndex(tmp)] = "";
                input = "";
                for(int i = 0; i < tmp.length - 1; i++) {
                    if (i == tmp.length - 2) {
                        input += tmp[i];
                    } else {
                        input += tmp[i] + " ";
                    }
                }
                ArrayList<String> trackid = si.getTrackId(input, date);
                return si.getAllTrackInformation(trackid, 0, trackid.size());
            }
        }
        return null;
    }

    private static int findNumericIndex(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (isNumeric(array[i])) {
                return i; // Restituisce la posizione del primo numero trovato
            }
        }
        return -1; // Se nessun numero è presente nell'array
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
