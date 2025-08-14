package com.retrogaming.entities;

import java.awt.*;

/**
 * Fast-moving enemy type that moves in different patterns
 */
public class FastEnemy extends Enemy {
    private static final int FAST_ENEMY_SPEED = 4;
    private long lastDirectionChange = 0;
    private static final int DIRECTION_CHANGE_INTERVAL = 1000; // 1 second
    
    public FastEnemy(int x, int y) {
        super(x, y);
        setColor(Color.MAGENTA);
        setMoveSpeed(FAST_ENEMY_SPEED);
        lastDirectionChange = System.currentTimeMillis();
    }
    
    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        
        // Change direction more frequently
        if (currentTime - lastDirectionChange > DIRECTION_CHANGE_INTERVAL) {
            setMoveDirection(getMoveDirection() * -1);
            lastDirectionChange = currentTime;
        }
        
        // Call parent update
        super.update();
    }
    
    @Override
    public void draw(Graphics2D g) {
        // Draw with a different appearance
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
        
        // Add a pulsing border effect
        long time = System.currentTimeMillis();
        int alpha = (int)(128 + 127 * Math.sin(time * 0.01));
        Color borderColor = new Color(255, 255, 255, alpha);
        g.setColor(borderColor);
        g.drawOval(getX(), getY(), getWidth(), getHeight());
        
        // Draw eyes
        g.setColor(Color.BLACK);
        g.fillOval(getX() + 5, getY() + 5, 4, 4);
        g.fillOval(getX() + 15, getY() + 5, 4, 4);
    }
}