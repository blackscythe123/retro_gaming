package com.retrogaming.entities;

import java.awt.*;

/**
 * Base class for all game entities
 */
public abstract class Entity {
    protected int x, y;
    protected int width, height;
    protected Color color;
    protected boolean active;
    
    public Entity(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.active = true;
    }
    
    public abstract void update();
    public abstract void draw(Graphics2D g);
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean intersects(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }
    
    // Getters and setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}