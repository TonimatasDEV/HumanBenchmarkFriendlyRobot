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
        System.out.println("Detecting area...");
        Dimension screenDimension = getScreenDimension();

        Point upperLeft = null;
        Point lowerRight = null;

        for (int y = 0; y < screenDimension.height; y++) {
            Color color = robot.getPixelColor(0, y);
            if (color.equals(BLUE)) {
                upperLeft = new Point(0, y);
                break;
            }
        }

        boolean isLast = true;
        for (int y = 0; y < screenDimension.height; y++) {
            Color color = robot.getPixelColor(0, y);
            robot.mouseMove(0, y);
            if (color.equals(BLUE)) {
                lowerRight = new Point(0, y);
                isLast = false;
            } else {
                if (!isLast) {
                    isLast = true;
                    for (int x = 0; x < screenDimension.width; x++) {
                        Color color1 = robot.getPixelColor(0, y);

                        robot.mouseMove(x, y - 1);
                        if (color1.equals(BLUE)) {
                            lowerRight = new Point(x, y);
                            isLast = false;
                        } else {
                            if (!isLast) {
                                break;
                            }
                        }
                        
                    }
                    
                    break;
                }
            }
        }
        
        if (upperLeft == null || lowerRight == null) {
            return null;
        } else {
            System.out.println("Area detected successfully");
            return new Bound(upperLeft, lowerRight);
        }
    }
}
