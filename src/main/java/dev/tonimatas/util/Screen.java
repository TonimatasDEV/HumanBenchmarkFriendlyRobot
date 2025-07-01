package dev.tonimatas.util;

import dev.tonimatas.api.Bound;

import java.awt.*;

public class Screen {
    public static final Color BLUE = new Color(43, 135, 209);
    
    public static Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Point getCenterPixel() {
        Dimension screenDimension = getScreenDimension();

        int centerX = screenDimension.width / 2;
        int centerY = screenDimension.height / 2;

        return new Point(centerX, centerY);
    }
    
    public static Bound getBlueArea(Robot robot) {
        Dimension screenDimension = getScreenDimension();
        
        boolean firstSet = false;
        Point upperLeft = null;
        Point lowerRight = null;
        for (int i = 0; i < screenDimension.width; i++) {
            for (int j = 0; j < screenDimension.height; j++) {
                if (robot.getPixelColor(i, j) == BLUE) {
                    if (!firstSet) {
                        upperLeft = new Point(i, j);
                    }
                    
                    lowerRight = new Point(i, j);
                }
            }
        }
        
        if (upperLeft == null) {
            return null;
        } else {
            return new Bound(upperLeft, lowerRight);
        }
    }
}
