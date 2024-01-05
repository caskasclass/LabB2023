package Models;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import Session.ClientSession;
import jars.ChartData;
import jars.CommentSection;
import jars.EmotionEvaluation;
import jars.ServerInterface;
import jars.Track;

public class TrackModule {

    ServerInterface stub;

    public TrackModule() {
            stub = ServerFinder.findServer();
    }

    public void insertEmotion(EmotionEvaluation e) {
        try {
            stub.insertEmotion(e);;
        } catch (RemoteException e1) {
            System.err.println("Qualcosa è andato storto durante l'inserimento dell'emozione");
            e1.printStackTrace();
        }
    }

    public boolean checkIfRated(String track_id,String user_id){
        try {
            return stub.checkIfRated(track_id, user_id);
        } catch (RemoteException e1) {
            System.err.println("Qualcosa è andato storto durante l'inserimento dell'emozione");
            e1.printStackTrace();
        }
        return false;
    }

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
