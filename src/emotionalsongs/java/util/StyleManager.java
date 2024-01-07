/**
  * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import java.io.IOException;
/**
*Classe progettata per gestire il caricamento degli stili CSS all'interno di un'applicazione JavaFX
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 
 */

public class StyleManager {


 
/**metodo che carica un file di stile CSS e restituisce l'URL del file sotto forma di stringa
*@param  cssFile  variabile di tipo String
*@throws  IOException  Eccezione
*@return stringa che rappresenta una classe css
*/
    public String loadStyle(String cssFile) throws IOException {
        return (getClass().getResource("../css/" + cssFile).toExternalForm());

    }
}
