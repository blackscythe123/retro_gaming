package com.retrogaming.utils;

/**
 * Utility class for game calculations and common operations
 */
public class GameUtils {
    
    /**
     * Calculate distance between two points
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    /**
     * Check if two rectangles intersect (collision detection)
     */
    public static boolean intersects(int x1, int y1, int w1, int h1, 
                                   int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }
    
    /**
     * Clamp a value between min and max
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Generate random integer between min (inclusive) and max (exclusive)
     */
    public static int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min)) + min;
    }
    
    /**
     * Check if a point is within screen bounds
     */
    public static boolean isOnScreen(int x, int y, int screenWidth, int screenHeight) {
        return x >= 0 && x < screenWidth && y >= 0 && y < screenHeight;
    }
    
    /**
     * Wrap a value around screen bounds (for entities that wrap around edges)
     */
    public static int wrapScreen(int value, int screenSize) {
        if (value < 0) return screenSize - 1;
        if (value >= screenSize) return 0;
        return value;
    }
    
    /**
     * Calculate angle in radians between two points
     */
    public static double angleBetween(int x1, int y1, int x2, int y2) {
        return Math.atan2(y2 - y1, x2 - x1);
    }
    
    /**
     * Convert degrees to radians
     */
    public static double toRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }
    
    /**
     * Convert radians to degrees
     */
    public static double toDegrees(double radians) {
        return radians * 180.0 / Math.PI;
    }
}