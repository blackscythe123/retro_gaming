package com.retrogaming.utils;

/**
 * Game configuration constants
 */
public class GameConfig {
    // Window settings
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final String WINDOW_TITLE = "Retro Gaming";
    
    // Game settings
    public static final int GAME_FPS = 60;
    public static final int INITIAL_LIVES = 3;
    
    // Player settings
    public static final int PLAYER_SIZE = 40;
    public static final int PLAYER_SPEED = 5;
    
    // Enemy settings
    public static final int ENEMY_SIZE = 30;
    public static final int ENEMY_SPEED = 2;
    public static final int INITIAL_ENEMY_COUNT = 5;
    
    // Projectile settings
    public static final int PROJECTILE_WIDTH = 4;
    public static final int PROJECTILE_HEIGHT = 10;
    public static final int PROJECTILE_SPEED = 8;
    public static final long SHOT_COOLDOWN = 200; // milliseconds
    
    // PowerUp settings
    public static final int POWERUP_SIZE = 20;
    public static final long POWERUP_LIFETIME = 10000; // 10 seconds
    public static final double POWERUP_SPAWN_CHANCE = 0.001; // per frame
    
    // Scoring
    public static final int SCORE_ENEMY_KILL = 100;
    public static final int SCORE_ENEMY_CONTACT = 50;
    public static final int SCORE_LEVEL_COMPLETE = 1000;
    public static final int SCORE_POWERUP_SPEED = 200;
    public static final int SCORE_POWERUP_DOUBLE = 500;
    public static final int SCORE_POWERUP_RAPID = 300;
    
    // Colors (as RGB values for consistency)
    public static final int[] COLOR_PLAYER = {0, 255, 255};     // Cyan
    public static final int[] COLOR_ENEMY = {255, 0, 0};        // Red
    public static final int[] COLOR_PROJECTILE = {255, 255, 0}; // Yellow
    public static final int[] COLOR_BACKGROUND = {0, 0, 0};     // Black
}