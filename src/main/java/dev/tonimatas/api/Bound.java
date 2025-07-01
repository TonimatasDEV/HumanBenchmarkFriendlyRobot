package dev.tonimatas.api;

import java.awt.*;

public record Bound(Point upperLeft, Point lowerRight) {
    public boolean contains(Point point) {
        return upperLeft.getX() < point.getX() && upperLeft.getY() < point.getY() && lowerRight.getX() > point.getX() && lowerRight.getY() > point.getY();
    }
}
