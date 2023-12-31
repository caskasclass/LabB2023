/**
 * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;
import javafx.scene.image.Image;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
/**
*Classe progettata per il caricamento asincrono delle immagini, mantenendo una cache delle immagini precedentemente caricate per migliorare le prestazioni
 * 
 * @author Beatrice Bastianello, matricola 751864, VA
 * @author Lorenzo Barbieri  , matricola 748695, VA
 * @author Filippo Storti , matricola 749195, VA
 * @author Nazar Viytyuk, matricola 748964, VA
 * @version 1.0
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 
 */
public class ImageLoader {
    /**cache rappresentata da mappa per le immagini */
    private final Map<String, CompletableFuture<Image>> imageCache;
    /**Dimensione massima della cache */
    private static final int MAX_CACHE_SIZE = 500;
    /**elemento FXML */
    private final Image placeholderImage;

 

 /**costruttore:  inizializza la cache delle immagini come una LinkedHashMap con un limite massimo di elementi*/
    public ImageLoader() {
        this.imageCache = new LinkedHashMap<String, CompletableFuture<Image>>(
                MAX_CACHE_SIZE, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CompletableFuture<Image>> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
        placeholderImage = new Image(getClass().getResource("/imgs/track_placeholder.jpg").toExternalForm());
    }



 /**metodo che carica un'immagine asincronamente da un URL. Se l'immagine è già presente nella cache, restituisce la CompletableFuture corrispondente; altrimenti, avvia il processo di caricamento asincrono
*@param  imageUrl  stringa in input 
*@return imageFuture variabile di tipo CompletableFuture
*/
    public CompletableFuture<Image> loadImageAsync(String imageUrl) {
        if (imageCache.containsKey(imageUrl)) {
            return imageCache.get(imageUrl);
        } else {
            CompletableFuture<Image> imageFuture = CompletableFuture.supplyAsync(() -> {
                try { 
                    return new Image(imageUrl);
                } catch (Exception e) {
                    System.out.println("Immagine non disponibile... : "+e.toString());
                    return placeholderImage;
                }
            });

            imageCache.put(imageUrl, imageFuture);
            return imageFuture;
        }
    }



 /**metodo che restituisce l'immagine di fallback
  * @return oggetto fxml che contiene le immagini
 */
    public Image getPlaceHolderImage() {
        return placeholderImage;
    }
}

