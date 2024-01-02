package util;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;


public class ColorsManager {


    public static Color getDominantColor(Image  image) {

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
    
        PixelReader reader = image.getPixelReader();
        Map<Color, Integer> colorCounts = new HashMap<>();
    
        // conta tutti i colori 
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = reader.getColor(i, j);
                colorCounts.put(color, colorCounts.getOrDefault(color, 0) + 1);
            }
        }
    
        // trasformi il map in una lista di coppie chiave valore 
        List<Map.Entry<Color, Integer>> entryList = new ArrayList<>(colorCounts.entrySet());
    
        //sort sula valore 
        entryList.sort(Map.Entry.<Color, Integer>comparingByValue().reversed());
    
        // prendo la chiave della prima pos e lo ritorno 
        Color dominantColor = entryList.get(0).getKey();
    
        return dominantColor;
    }

}
