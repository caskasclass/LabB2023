package util;
import javafx.scene.image.Image;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public class ImageLoader {
    private final Map<String, CompletableFuture<Image>> imageCache;

    private static final int MAX_CACHE_SIZE = 500; // Dimensione massima della cache
    private final Image placeholderImage;

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

    public Image getPlaceHolderImage() {
        return placeholderImage;
    }
}

