package dev.tonimatas;

import dev.tonimatas.benchmarks.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        String input = JOptionPane.showInputDialog(null, """
                Select the friendly robot what do you want to use:
                
                1. Aim Trainer
                2. Chimp Test
                3. Number Memory
                4. Reaction Time
                5. Sequence Memory
                6. Typing
                7. Verbal Memory
                8. Visual Memory
                """, "HumanBenchmarkFriendlyRobot", JOptionPane.QUESTION_MESSAGE);
        
        switch (input) {
            case "1" -> new AimTrainer().run();
            case "2" -> new ChimpTest().run();
            case "3" -> new NumberMemory().run();
            case "4" -> new ReactionTime().run();
            case "5" -> new SequenceMemory().run();
            case "6" -> new Typing().run();
            case "7" -> new VerbalMemory().run();
            case "8" -> new VisualMemory().run();
            default -> {
                System.out.println("Invalid trainer");
            }
        }
    }
}