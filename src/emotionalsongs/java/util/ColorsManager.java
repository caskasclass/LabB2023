package util;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ColorsManager {

    public static Color getDominantColor(Image image) {

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

        // sort sula valore
        entryList.sort(Map.Entry.<Color, Integer>comparingByValue().reversed());

        // prendo la chiave della prima pos e lo ritorno
        Color dominantColor = entryList.get(0).getKey();

        // Verifica se il colore dominante è bianco o simile al bianco
        if (isWhiteOrSimilar(dominantColor)) {
            dominantColor = Color.rgb(125, 112, 186); // Sostituisci con un colore predefinito (esempio: BLUE)
        }
        if(isBlackOrSimilar(dominantColor)){
            dominantColor = Color.rgb(174, 140, 163);
        }


        return dominantColor;

    }

    private static boolean isWhiteOrSimilar(Color color) {
        double tolerance = 0.1; // Tolleranza per determinare la somiglianza al bianco

        // Controllo se la differenza tra i canali del colore è inferiore alla
        // tolleranza
        return (Math.abs(color.getRed() - Color.WHITE.getRed()) < tolerance) &&
                (Math.abs(color.getGreen() - Color.WHITE.getGreen()) < tolerance) &&
                (Math.abs(color.getBlue() - Color.WHITE.getBlue()) < tolerance);
    }

    private static boolean isBlackOrSimilar(Color color) {
        double tolerance = 0.1; // Tolleranza per determinare la somiglianza al nero
    
        // Controllo se la differenza tra i canali del colore è inferiore alla tolleranza
        return (Math.abs(color.getRed() - Color.BLACK.getRed()) < tolerance) &&
                (Math.abs(color.getGreen() - Color.BLACK.getGreen()) < tolerance) &&
                (Math.abs(color.getBlue() - Color.BLACK.getBlue()) < tolerance);
    }

}
