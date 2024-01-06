/**
  * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import java.io.IOException;

public class StyleManager {

    public String loadStyle(String cssFile) throws IOException {
        return (getClass().getResource("../css/" + cssFile).toExternalForm());

    }
}
