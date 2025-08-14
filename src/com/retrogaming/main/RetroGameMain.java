package com.retrogaming.main;

import com.retrogaming.ui.GameWindow;
import javax.swing.SwingUtilities;

/**
 * Main entry point for the Retro Gaming application
 */
public class RetroGameMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Failed to start the game: " + e.getMessage());
            }
        });
    }
}