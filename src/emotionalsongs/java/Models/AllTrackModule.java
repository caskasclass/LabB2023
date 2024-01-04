package Models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import jars.Track;
import jars.ServerInterface;
import java.rmi.RemoteException;

public class AllTrackModule {

    private ServerInterface si;
    private ExecutorService executorService;

    public AllTrackModule() {
            si = ServerFinder.findServer();
            executorService = Executors.newFixedThreadPool(5); 
    }

    public Future<ArrayList<Track>> searchTracksNameAsync(String input) {
        return executorService.submit(() -> searchTracksName(input));
    }

    public Future<ArrayList<Track>> searchTracksAutorAsync(String input) {
        return executorService.submit(() -> searchTracksAutor(input));
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public ArrayList<Track> searchTracksName(String input) throws RemoteException {
        if (input != null) {
            ArrayList<String> trackIds = si.getTrackId(input);
            return getAllTrackInformation(trackIds);
        }
        return new ArrayList<>(); // Restituisci una lista vuota in caso di input nullo
    }

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

    private static int findNumericIndex(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (isNumeric(array[i])) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
