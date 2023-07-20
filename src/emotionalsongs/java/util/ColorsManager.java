package util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ColorsManager {

    public static LinearGradient setGradient(Color c1, Color c2) {
        Stop[] stops = new Stop[] {
                new Stop(0, c1),
                new Stop(1, c2)
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        return linearGradient;
    }

    public static Color getMixedColor(Image image) {
        // calcola il mix de
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
