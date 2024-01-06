/**
 * Contiene le classi necessarie a
 * gestire funzionalità generiche di una sessione in app.
  * @package Session
 * @see package.emotionalsongs.java
 */
package Session;

import jars.User;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0

 *classe static creata per gestire lo stato di utente loggato e non loggato
 */
public class ClientSession {
    public static User client = new User(null, null, null, null, null, 0, null, null, null);
    
}
