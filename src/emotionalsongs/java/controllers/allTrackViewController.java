package controllers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import pkg.*;

public class allTrackViewController {

    @FXML
    private VBox tableContainer;
    ObservableList<Track> list;

    @FXML
    void initialize() throws RemoteException, NotBoundException {
        /*try {
        Registry r = LocateRegistry.getRegistry(ServerInterface.PORT);
        ServerInterface si = (ServerInterface) r.lookup("SERVER");
        ArrayList<String> ar = si.getTrackId("Ricordami");
        ArrayList<Track> res = si.getAllAlbumInformation(ar, 0, ar.size());
        TableViewManager t = new TableViewManager(true, false);
        ObservableList<Track> o = FXCollections.observableArrayList(res);
        t.setItems(o);
        tableContainer.getChildren().add(t);
        } catch (Exception e) {
            System.out.println(e);        }
     */   
    }

}
