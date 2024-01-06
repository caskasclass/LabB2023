/**
 * Contiene le classi necessarie a racchiudere tutti i servizi
 * implementati dal server e accessibili grazie a ServerInterface.
 * Ogni classe rappresenta un sottogruppo di servizi legati a utente, canzoni etc.
  * @package Models
 * @see package.emotionalsongs.java
 */
package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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



/**metodo utilizzato per cercare un server 
*@return istanza di ServerInterface, null altrimenti
*/
    public static ServerInterface findServer() {
        try {
            Registry r = LocateRegistry.getRegistry(1099);
            return (ServerInterface) r.lookup("SERVER");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return null;
    
    }

}
