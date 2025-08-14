package com.retrogaming.entities;

import java.awt.*;

/**
 * PowerUp items that provide benefits to the player
 */
public class PowerUp extends Entity {
    private static final int POWERUP_SIZE = 20;
    
    public enum PowerUpType {
        SPEED_BOOST("Speed", Color.GREEN),
        EXTRA_LIFE("Life", Color.PINK),
        DOUBLE_SCORE("2x Score", Color.ORANGE),
        RAPID_FIRE("Rapid Fire", Color.MAGENTA);
        
        private final String displayName;
        private final Color color;
        
        PowerUpType(String displayName, Color color) {
            this.displayName = displayName;
            this.color = color;
        }
        
        public String getDisplayName() { return displayName; }
        public Color getColor() { return color; }
    }
    
    private PowerUpType type;
    private long creationTime;
    private static final long LIFETIME = 10000; // 10 seconds
    
    public PowerUp(int x, int y, PowerUpType type) {
        super(x, y, POWERUP_SIZE, POWERUP_SIZE, type.getColor());
        this.type = type;
        this.creationTime = System.currentTimeMillis();
    }
    
    @Override
    public void update() {
        // Check if powerup has expired
        if (System.currentTimeMillis() - creationTime > LIFETIME) {
            active = false;
        }
        
        // Simple floating animation
        long elapsed = System.currentTimeMillis() - creationTime;
        y += (int)(Math.sin(elapsed * 0.005) * 0.5);
    }
    
    @Override
    public void draw(Graphics2D g) {
        if (active) {
            // Draw powerup with pulsing effect
            long elapsed = System.currentTimeMillis() - creationTime;
            float alpha = 0.7f + 0.3f * (float)Math.sin(elapsed * 0.01);
            
            Color drawColor = new Color(
                color.getRed(), color.getGreen(), color.getBlue(), 
                (int)(255 * alpha)
            );
            
            g.setColor(drawColor);
            g.fillOval(x, y, width, height);
            
            // Draw border
            g.setColor(Color.WHITE);
            g.drawOval(x, y, width, height);
            
            // Draw type indicator (first letter)
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 10));
            FontMetrics fm = g.getFontMetrics();
            String letter = type.getDisplayName().substring(0, 1);
            int textX = x + (width - fm.stringWidth(letter)) / 2;
            int textY = y + (height + fm.getHeight()) / 2 - 2;
            g.drawString(letter, textX, textY);
        }
    }
    
    public PowerUpType getType() {
        return type;
    }
    
    public boolean isExpired() {
        return System.currentTimeMillis() - creationTime > LIFETIME;
    }
    
    public long getTimeRemaining() {
        return Math.max(0, LIFETIME - (System.currentTimeMillis() - creationTime));
    }
}