package dev.tonimatas.benchmarks;

import dev.tonimatas.api.Bound;
import dev.tonimatas.util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class ReactionTime implements Benchmark {

    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            Bound bound = Screen.getBlueArea(robot);

            if (bound == null) {
                JOptionPane.showMessageDialog(null, "Blue area wasn't detected. Make sure it is visible.");
            }

            JOptionPane.showMessageDialog(null, "All ready, start the test");
            int counter = 0;
            for (;;) {
                Point green = Screen.getFirstPixelColor(robot, new Color(75, 219,106), bound);
                if (green != null) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    TimeUnit.MILLISECONDS.sleep(100);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    TimeUnit.MILLISECONDS.sleep(100);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    counter++;
                }
                if (counter >= 5) break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
