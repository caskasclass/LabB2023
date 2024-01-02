package Models;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import jars.*;

public class ServerFinder {

    public static ServerInterface findServer() {
        boolean found = false;
        int counter = 0;
        while(!found) {
            try {
                Registry r = LocateRegistry.getRegistry(ServerInterface.PORT[counter] + counter);
                return (ServerInterface) r.lookup("SERVER" + counter);
            } catch (Exception e) {
                counter++;
                if(counter >= ServerInterface.PORT.length) {
                    counter = 0;
                }
            }
        }
        return null;
    }

}
