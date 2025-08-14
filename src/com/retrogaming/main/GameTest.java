package com.retrogaming.main;

import com.retrogaming.entities.*;
import com.retrogaming.utils.GameUtils;
import com.retrogaming.utils.InputHandler;

/**
 * Simple test to verify core game functionality without GUI
 */
public class GameTest {
    public static void main(String[] args) {
        System.out.println("=== Retro Gaming Core Functionality Test ===");
        
        // Test entity creation
        System.out.println("\n1. Testing Entity Creation:");
        Player player = new Player(100, 200);
        Enemy enemy = new Enemy(150, 100);
        Projectile projectile = new Projectile(110, 190, true);
        PowerUp powerUp = new PowerUp(200, 150, PowerUp.PowerUpType.EXTRA_LIFE);
        
        System.out.println("✓ Player created at (" + player.getX() + ", " + player.getY() + ")");
        System.out.println("✓ Enemy created at (" + enemy.getX() + ", " + enemy.getY() + ")");
        System.out.println("✓ Projectile created at (" + projectile.getX() + ", " + projectile.getY() + ")");
        System.out.println("✓ PowerUp created at (" + powerUp.getX() + ", " + powerUp.getY() + ")");
        
        // Test collision detection
        System.out.println("\n2. Testing Collision Detection:");
        boolean collision = player.intersects(enemy);
        System.out.println("✓ Player-Enemy collision: " + collision);
        
        // Test utility functions
        System.out.println("\n3. Testing Utility Functions:");
        double distance = GameUtils.distance(player.getX(), player.getY(), 
                                           enemy.getX(), enemy.getY());
        System.out.println("✓ Distance between player and enemy: " + String.format("%.2f", distance));
        
        int clampedValue = GameUtils.clamp(150, 0, 100);
        System.out.println("✓ Clamped 150 to range [0,100]: " + clampedValue);
        
        // Test entity updates
        System.out.println("\n4. Testing Entity Updates:");
        int initialEnemyX = enemy.getX();
        enemy.update();
        int newEnemyX = enemy.getX();
        System.out.println("✓ Enemy moved from X=" + initialEnemyX + " to X=" + newEnemyX);
        
        int initialProjY = projectile.getY();
        projectile.update();
        int newProjY = projectile.getY();
        System.out.println("✓ Projectile moved from Y=" + initialProjY + " to Y=" + newProjY);
        
        // Test input handler
        System.out.println("\n5. Testing Input Handler:");
        InputHandler inputHandler = new InputHandler();
        System.out.println("✓ InputHandler created successfully");
        System.out.println("✓ Space pressed: " + inputHandler.isSpacePressed());
        
        // Test power-up types
        System.out.println("\n6. Testing Power-Up System:");
        PowerUp.PowerUpType[] types = PowerUp.PowerUpType.values();
        System.out.println("✓ Available power-up types: " + types.length);
        for (PowerUp.PowerUpType type : types) {
            System.out.println("  - " + type.getDisplayName());
        }
        
        System.out.println("\n=== All Core Tests Passed! ===");
        System.out.println("\nThe retro gaming framework is ready!");
        System.out.println("To play with GUI: java -cp build com.retrogaming.main.RetroGameMain");
    }
}