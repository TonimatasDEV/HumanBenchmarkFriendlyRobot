package dev.tonimatas.util;

import dev.tonimatas.api.Bound;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    
    public static Point getFirstPixelColor(Robot robot, Color color, Bound workingArea, Bound exclude) {
        Dimension screenDimension = getScreenDimension();

        BufferedImage screenshot = robot.createScreenCapture(new Rectangle(0, 0, screenDimension.width, screenDimension.height));

        for (int x = 0; x < screenDimension.width; x++) {
            for (int y = 0; y < screenDimension.height; y++) {
                Color pixelColor = new Color(screenshot.getRGB(x, y));
                if (color.equals(pixelColor)) {
                    Point point = new Point(x, y);

                    if ((exclude == null || !exclude.contains(point)) && workingArea.contains(point)) {
                        return point;
                    }
                }
            }
        }

        return null;
    }
    
    public static Point getFirstPixelColor(Robot robot, Color color, Bound workingArea) {
        return getFirstPixelColor(robot, color, workingArea, new Bound(new Point(0, 0), new Point(0, 0)));
    }
    
    public static Bound getBlueArea(Robot robot) {
        System.out.println("Detecting area...");
        Dimension screenDimension = getScreenDimension();

        BufferedImage screenshot = robot.createScreenCapture(new Rectangle(0, 0, screenDimension.width, screenDimension.height));
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
            Color color = new Color(screenshot.getRGB(0, y));

            if (color.equals(BLUE)) {
                lowerRight = new Point(0, y);
                isLast = false;
            } else {
                if (!isLast) {
                    isLast = true;
                    for (int x = 0; x < screenDimension.width; x++) {
                        Color color1 = new Color(screenshot.getRGB(x, y - 1));

                        if (color1.equals(BLUE)) {
                            lowerRight = new Point(x, y - 1);
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
