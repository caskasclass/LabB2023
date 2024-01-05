package Models;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import jars.*;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe creata per trovare un'istanza di ServerInterface attraverso la ricerca su diverse porte e restituirla quando viene trovata
 */
public class ServerFinder {



/**metodo utilizzato per cercare un server attraverso registri RMI su porte specifiche
*in un ciclo while usa la variabile <strong>found</strong> per indicare se è stato trovato
*variabile <strong>count</strong> viene utilizzata per iterare le porte del ciclo
*@return istanza di ServerInterface, null altrimenti
*/
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
