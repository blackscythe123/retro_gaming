package com.retrogaming.entities;

import java.awt.*;

/**
 * Projectile fired by player or enemies
 */
public class Projectile extends Entity {
    private static final int PROJECTILE_WIDTH = 4;
    private static final int PROJECTILE_HEIGHT = 10;
    private static final int DEFAULT_SPEED = 8;
    
    private int velocityX;
    private int velocityY;
    private boolean fromPlayer;
    
    public Projectile(int x, int y, int velocityX, int velocityY, boolean fromPlayer) {
        super(x, y, PROJECTILE_WIDTH, PROJECTILE_HEIGHT, 
              fromPlayer ? Color.YELLOW : Color.ORANGE);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.fromPlayer = fromPlayer;
    }
    
    // Common constructor for vertical projectiles
    public Projectile(int x, int y, boolean fromPlayer) {
        this(x, y, 0, fromPlayer ? -DEFAULT_SPEED : DEFAULT_SPEED, fromPlayer);
    }
    
    @Override
    public void update() {
        x += velocityX;
        y += velocityY;
        
        // Deactivate if off screen
        if (y < -height || y > 800 || x < -width || x > 1000) {
            active = false;
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        if (active) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
            
            // Add a glow effect
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 128));
            g.fillOval(x - 1, y - 1, width + 2, height + 2);
        }
    }
    
    public boolean isFromPlayer() {
        return fromPlayer;
    }
    
    public int getVelocityX() {
        return velocityX;
    }
    
    public int getVelocityY() {
        return velocityY;
    }
}