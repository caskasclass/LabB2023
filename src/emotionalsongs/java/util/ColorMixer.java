package util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ColorMixer {

    // a partire da una oggetto image restituisci il mix dei colori di quella
    // immagine e resituisci un colore
    public static Color getMixedColor(Image image) {
        // calcola il mix dei colori dell'immagine
        // prendo la larghezza e l'altezza dell'immagine
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        PixelReader reader = image.getPixelReader();
        Color result = null;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = reader.getColor(i, j);
                if (result == null) {
                    result = color;
                } else {
                    result = mixColor(result, color);
                }

            }
        }

        return result;

    }

    private static Color mixColor(Color color1, Color color2) {
        double red = (double) ((color1.getRed() + color2.getRed()) / 2);
        double green = (double) ((color1.getGreen() + color2.getGreen()) / 2);
        double blue = (double) ((color1.getBlue() + color2.getBlue()) / 2);
        Color color = new Color(red, green, blue, 1);
        return color;
    }
}
