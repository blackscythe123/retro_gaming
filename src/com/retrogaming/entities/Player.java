package com.retrogaming.entities;

import com.retrogaming.utils.InputHandler;
import java.awt.*;

/**
 * Player character that can be controlled by the user
 */
public class Player extends Entity {
    private static final int PLAYER_WIDTH = 40;
    private static final int PLAYER_HEIGHT = 40;
    private static final int MOVEMENT_SPEED = 5;
    
    public Player(int x, int y) {
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, Color.CYAN);
    }
    
    @Override
    public void update() {
        // Base update - can be overridden for more complex behavior
    }
    
    public void update(InputHandler inputHandler) {
        if (inputHandler == null) return;
        
        // Handle movement input
        if (inputHandler.isLeftPressed()) {
            x -= MOVEMENT_SPEED;
        }
        if (inputHandler.isRightPressed()) {
            x += MOVEMENT_SPEED;
        }
        if (inputHandler.isUpPressed()) {
            y -= MOVEMENT_SPEED;
        }
        if (inputHandler.isDownPressed()) {
            y += MOVEMENT_SPEED;
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        // Draw player as a simple rectangle for now
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Add a border for better visibility
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        
        // Draw a simple face/direction indicator
        g.setColor(Color.BLACK);
        g.fillOval(x + 8, y + 8, 6, 6);   // Left eye
        g.fillOval(x + 26, y + 8, 6, 6);  // Right eye
        g.fillRect(x + 15, y + 25, 10, 3); // Mouth
    }
    
    public void moveLeft() {
        x -= MOVEMENT_SPEED;
    }
    
    public void moveRight() {
        x += MOVEMENT_SPEED;
    }
    
    public void moveUp() {
        y -= MOVEMENT_SPEED;
    }
    
    public void moveDown() {
        y += MOVEMENT_SPEED;
    }
}