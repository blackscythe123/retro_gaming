package com.retrogaming.entities;

import java.awt.*;

/**
 * Enemy entity that provides opposition to the player
 */
public class Enemy extends Entity {
    private static final int ENEMY_WIDTH = 30;
    private static final int ENEMY_HEIGHT = 30;
    
    private int moveDirection = 1; // 1 for right, -1 for left
    private int moveSpeed = 2;
    private long lastMoveTime = 0;
    private static final int MOVE_INTERVAL = 50; // milliseconds between moves
    
    public Enemy(int x, int y) {
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, Color.RED);
        this.lastMoveTime = System.currentTimeMillis();
    }
    
    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        
        // Move enemy in a simple pattern
        if (currentTime - lastMoveTime > MOVE_INTERVAL) {
            x += moveSpeed * moveDirection;
            
            // Simple boundary checking - reverse direction when hitting edges
            if (x <= 0 || x >= 800 - width) {
                moveDirection *= -1;
                y += 10; // Move down when changing direction
            }
            
            lastMoveTime = currentTime;
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        // Draw enemy as a simple colored rectangle
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        // Add a border
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, y, width, height);
        
        // Draw simple enemy features
        g.setColor(Color.BLACK);
        g.fillOval(x + 5, y + 5, 6, 6);   // Left eye
        g.fillOval(x + 19, y + 5, 6, 6);  // Right eye
        
        // Draw angry mouth
        g.setColor(Color.YELLOW);
        g.fillRect(x + 8, y + 20, 14, 4);
    }
    
    // Getters and setters for enemy-specific properties
    public int getMoveDirection() {
        return moveDirection;
    }
    
    public void setMoveDirection(int moveDirection) {
        this.moveDirection = moveDirection;
    }
    
    public int getMoveSpeed() {
        return moveSpeed;
    }
    
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}