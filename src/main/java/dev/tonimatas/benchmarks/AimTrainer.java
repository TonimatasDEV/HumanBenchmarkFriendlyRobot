package dev.tonimatas.benchmarks;

import dev.tonimatas.api.Bound;
import dev.tonimatas.util.Screen;

import java.awt.*;
import java.awt.event.InputEvent;

public class AimTrainer implements Benchmark {
    private static final Color SAVE = new Color(255, 209, 84);
    private static final Color TARGET = new Color(149, 195, 232);
    private static final Bound BOUND = new Bound(new Point(861, 184), new Point(1045, 219));
    
    @Override
    public void run() {
        new Thread(() -> {
            try {
                Robot robot = new Robot();
                Bound workingArea = Screen.getBlueArea(robot);
                
                long timeOut = System.currentTimeMillis();
                for (;;) {
                    Point yellow = Screen.getFirstPixelColor(robot, SAVE, workingArea, BOUND);
                    
                    if (yellow != null) {
                        break;
                    }

                    Point targetPoint = Screen.getFirstPixelColor(robot, TARGET, workingArea, BOUND);

                    if (targetPoint != null) {
                        robot.mouseMove(targetPoint.x, targetPoint.y);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                    
                    long elapsed = System.currentTimeMillis() - timeOut;
                    if (elapsed > 5000) {
                        System.out.println("Skipping benchmark because of " + elapsed + " milliseconds");
                        break;
                    }
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
