package com.retrogaming.ui;

import com.retrogaming.game.GamePanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Main game window that hosts the game panel
 */
public class GameWindow extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private GamePanel gamePanel;
    
    public GameWindow() {
        initializeWindow();
        initializeGame();
    }
    
    private void initializeWindow() {
        setTitle("Retro Gaming");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Center window on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - WINDOW_WIDTH) / 2;
        int y = (screenSize.height - WINDOW_HEIGHT) / 2;
        setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    
    private void initializeGame() {
        gamePanel = new GamePanel(WINDOW_WIDTH, WINDOW_HEIGHT);
        add(gamePanel);
        
        // Pack to fit components properly
        pack();
        
        // Start the game
        gamePanel.startGame();
    }
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}