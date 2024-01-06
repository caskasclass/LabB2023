/**
  * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe utility per il caricamento di file FXML
 */

public class FXMLLoaders {


 /**metodo per caricare un file FXML e restituisce un nodo Parent, che può essere utilizzato come radice per la gerarchia dell'interfaccia utente
*@param  <code>viewFile</code>  stringa in input 
*@throws  <code>IOEXception</code>  Eccezione
*@return <code>parent</code> variabile di tipo Parent
*/
    public Parent loadFXML(String viewFile) {
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+viewFile));
        try {
            parent = loader.load();
        } catch (IOException e) {
            System.out.println("\nLoader Problems, Can't return the Parent node.\n");
            e.printStackTrace();
        }
        return parent;
    }



 /**metodo che restituisce un oggetto URL associato al file FXML specificato
*@param  <code>viewFile</code>  stringa in input 
*@throws  <code>IOEXception</code>  Eccezione
*@return <code>file</code> variabile di tipo URL
*/
    public URL loadUrl(String viewFile) throws IOException {
        URL file = getClass().getResource("../view/"+viewFile);
        return file;
    }



  /**metodo che restituisce un nuovo oggetto FXMLLoader configurato per caricare il file FXML specificato
*@param  <code>viewFile</code>  stringa in input 
*/
    public FXMLLoader getLoader(String viewFile){
        return (new FXMLLoader(getClass().getResource("../view/"+viewFile)));
    }
    
}
