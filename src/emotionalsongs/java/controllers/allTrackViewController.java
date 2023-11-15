package controllers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import pkg.ServerInterface;
import pkg.Track;
import util.TableViewManager;

public class allTrackViewController {

    @FXML
    private VBox tableContainer;
    ObservableList<Track> list;

    @FXML
    void initialize() throws RemoteException, NotBoundException {
        Registry r = LocateRegistry.getRegistry(ServerInterface.PORT);
        ServerInterface si = (ServerInterface) r.lookup("SERVER");
        ArrayList<String> ar = si.getTrackId("Ricordami");
        ArrayList<Track> res = si.getAllTrackInformation(ar, 0, ar.size());
        TableViewManager t = new TableViewManager(true, false);
        ObservableList<Track> o = FXCollections.observableArrayList(res);
        t.setItems(o);
        tableContainer.getChildren().add(t);
    }

}
