package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import jars.*;

public class ServerFinder {

    public static ServerInterface findServer() {
        try {
            Registry r = LocateRegistry.getRegistry(1099);
            return (ServerInterface) r.lookup("SERVER");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return null;
        /*
         * boolean found = false;
         * int counter = 0;
         * while(!found) {
         * try {
         * Registry r = LocateRegistry.getRegistry(ServerInterface.PORT[counter] +
         * counter);
         * return (ServerInterface) r.lookup("SERVER" + counter);
         * } catch (Exception e) {
         * counter++;
         * if(counter >= ServerInterface.PORT.length) {
         * counter = 0;
         * }
         * }
         * }
         * return null;
         */
    }

}
