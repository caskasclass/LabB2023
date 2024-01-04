package Session;

import javafx.stage.Screen;

public class WindowAppearance {

    private static final double WIDTH_PERCENTAGE = 0.85;
    private static final double HEIGHT_PERCENTAGE = 0.90;

    private static double screenWidth;
    private static double screenHeight;
    private static double windowWidth;
    private static double windowHeight;
    private static double windowX;
    private static double windowY;

    public static void calculateWindowDimensions() {
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        windowWidth = screenWidth * WIDTH_PERCENTAGE;
        windowHeight = screenHeight * HEIGHT_PERCENTAGE;

        windowX = (screenWidth - windowWidth) / 2;
        windowY = (screenHeight - windowHeight) / 2;
    }

    public static double getWindowWidth() {
        return windowWidth;
    }

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static double getWindowX() {
        return windowX;
    }

    public static double getWindowY() {
        return windowY;
    }

}
