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

import jars.ServerInterface;
import jars.User;

/**
 *Classe creata per la gestione degli utenti, con metodi per la registrazione, la verifica dell'esistenza di un utente e il login
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 */
public class UserModule {
    /**oggetto interfaccia per servizi server*/
    private ServerInterface si;

    
/**costruttore: inizializza oggetto  si  con metodo findServer
*@throws  RemoteException , NotBoundException  Eccezioni
*/
    public UserModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }
    


/**metodo che gestisce la registrazione di un nuovo utente
*@param  u  parametro di tipo User
*@throws  RemoteException  Eccezione
*/   
    public void registration(User u) throws RemoteException {
        si.registration(u);
    }



/**metodo che applica un controllo a livello di utente, in particolare andando a controllare se effettivamente esiste
*@param  u  parametro di tipo User
*@throws  RemoteException  Eccezione
*@return true qualora utente è gia registrat, false altrimenti
*/  
    public boolean alreadyExist(User u) throws RemoteException {
        try {
            if(si.findexExistingUsers().contains(u.getUserid()) || si.findexExistingUsers().contains(u.getMail()))
                return true;
            else 
                return false;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }



/**metodo che si occupa della gestione del login di un utente registrato
*@param  id  parametro di tipo String, psw  parametro di tipo String
*@throws  RemoteException  Eccezione
*@return  u  variabile di tipo User corrispondente al profilo dell'utente che tenta di effettuare un login con successo
*/  
    public User login(String id, String psw) throws RemoteException {
        User u = si.access(id, psw);
        return u;
    }
}
