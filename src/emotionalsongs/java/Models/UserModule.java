package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import jars.ServerInterface;
import jars.User;

/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe creata per la gestione degli utenti, con metodi per la registrazione, la verifica dell'esistenza di un utente e il login
 */
public class UserModule {
    private ServerInterface si;

    
/**costruttore: inizializza oggetto <code>si</code> con metodo findServer
*@throws <code>RemoteException</code>,<code>NotBoundException</code> Eccezioni
*/
    public UserModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }
    


/**metodo che gestisce la registrazione di un nuovo utente
*@param <code>u</code> parametro di tipo User
*@throws <code>RemoteException</code> Eccezione
*/   
    public void registration(User u) throws RemoteException {
        si.registration(u);
    }



/**metodo che applica un controllo a livello di utente, in particolare andando a controllare se effettivamente esiste
*@param <code>u</code> parametro di tipo User
*@throws <code>RemoteException</code> Eccezione
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
*@param <code>id</code> parametro di tipo String,<code>psw</code> parametro di tipo String
*@throws <code>RemoteException</code> Eccezione
*@return <code>u</code> variabile di tipo User corrispondente al profilo dell'utente che tenta di effettuare un login con successo
*/  
    public User login(String id, String psw) throws RemoteException {
        User u = si.access(id, psw);
        return u;
    }
}
