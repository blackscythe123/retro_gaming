package com.retrogaming.game;

import com.retrogaming.entities.Player;
import com.retrogaming.entities.Enemy;
import com.retrogaming.entities.Projectile;
import com.retrogaming.entities.PowerUp;
import com.retrogaming.utils.InputHandler;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 * Main game panel that handles the game loop, rendering, and game logic
 */
public class GamePanel extends JPanel implements ActionListener {
    private static final int GAME_FPS = 60;
    private static final int GAME_DELAY = 1000 / GAME_FPS;
    
    private int width;
    private int height;
    private Timer gameTimer;
    private GameState gameState;
    private InputHandler inputHandler;
    
    // Game entities
    private Player player;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private List<PowerUp> powerUps;
    
    // Game statistics
    private int score;
    private int lives;
    private long gameStartTime;
    private long lastShotTime;
    private static final long SHOT_COOLDOWN = 200; // milliseconds
    
    public enum GameState {
        MENU, PLAYING, PAUSED, GAME_OVER
    }
    
    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.gameState = GameState.MENU;
        this.score = 0;
        this.lives = 3;
        
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
        
        initializeGame();
        setupInputHandler();
    }
    
    private void initializeGame() {
        // Initialize game entities
        player = new Player(width / 2, height - 50);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        powerUps = new ArrayList<>();
        
        // Add some initial enemies for demonstration
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(100 + i * 120, 100));
        }
        
        gameStartTime = System.currentTimeMillis();
        lastShotTime = 0;
    }
    
    private void setupInputHandler() {
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
    }
    
    public void startGame() {
        gameState = GameState.PLAYING;
        gameTimer = new Timer(GAME_DELAY, this);
        gameTimer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
    
    private void update() {
        if (gameState == GameState.PLAYING) {
            // Update player
            if (player != null) {
                player.update(inputHandler);
                
                // Keep player within bounds
                if (player.getX() < 0) player.setX(0);
                if (player.getX() + player.getWidth() > width) {
                    player.setX(width - player.getWidth());
                }
                if (player.getY() < 0) player.setY(0);
                if (player.getY() + player.getHeight() > height) {
                    player.setY(height - player.getHeight());
                }
                
                // Handle shooting
                handlePlayerShooting();
            }
            
            // Update enemies
            for (Enemy enemy : enemies) {
                enemy.update();
            }
            
            // Update projectiles
            for (int i = projectiles.size() - 1; i >= 0; i--) {
                Projectile proj = projectiles.get(i);
                proj.update();
                if (!proj.isActive()) {
                    projectiles.remove(i);
                }
            }
            
            // Update power-ups
            for (int i = powerUps.size() - 1; i >= 0; i--) {
                PowerUp powerUp = powerUps.get(i);
                powerUp.update();
                if (!powerUp.isActive()) {
                    powerUps.remove(i);
                }
            }
            
            // Check for collisions and other game logic
            checkCollisions();
            checkWinCondition();
            
            // Randomly spawn power-ups
            if (Math.random() < 0.001) { // 0.1% chance per frame
                spawnRandomPowerUp();
            }
        } else if (gameState == GameState.MENU) {
            // Check for game start input
            if (inputHandler.isSpacePressed()) {
                gameState = GameState.PLAYING;
                resetGame();
            }
        }
    }
    
    private void handlePlayerShooting() {
        if (inputHandler.isSpacePressed() && 
            System.currentTimeMillis() - lastShotTime > SHOT_COOLDOWN) {
            
            // Create projectile from player position
            int projX = player.getX() + player.getWidth() / 2;
            int projY = player.getY();
            projectiles.add(new Projectile(projX, projY, true));
            lastShotTime = System.currentTimeMillis();
        }
    }
    
    private void spawnRandomPowerUp() {
        PowerUp.PowerUpType[] types = PowerUp.PowerUpType.values();
        PowerUp.PowerUpType randomType = types[(int)(Math.random() * types.length)];
        
        int x = (int)(Math.random() * (width - 20));
        int y = (int)(Math.random() * (height / 2));
        
        powerUps.add(new PowerUp(x, y, randomType));
    }
    
    private void resetGame() {
        score = 0;
        lives = 3;
        gameStartTime = System.currentTimeMillis();
        
        // Reset entities
        player.setX(width / 2);
        player.setY(height - 50);
        
        enemies.clear();
        projectiles.clear();
        powerUps.clear();
        
        // Respawn enemies
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(100 + i * 120, 100));
        }
    }
    
    private void checkCollisions() {
        if (player == null) return;
        
        Rectangle playerBounds = player.getBounds();
        
        // Check projectile-enemy collisions
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile proj = projectiles.get(i);
            if (!proj.isFromPlayer()) continue;
            
            Rectangle projBounds = proj.getBounds();
            
            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy enemy = enemies.get(j);
                if (projBounds.intersects(enemy.getBounds())) {
                    // Hit! Remove both projectile and enemy
                    projectiles.remove(i);
                    enemies.remove(j);
                    score += 100;
                    break;
                }
            }
        }
        
        // Check player-enemy collisions
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            if (playerBounds.intersects(enemy.getBounds())) {
                // Player hit by enemy
                enemies.remove(i);
                lives--;
                score += 50; // Partial points for contact
                
                if (lives <= 0) {
                    gameState = GameState.GAME_OVER;
                }
                break;
            }
        }
        
        // Check player-powerup collisions
        for (int i = powerUps.size() - 1; i >= 0; i--) {
            PowerUp powerUp = powerUps.get(i);
            if (playerBounds.intersects(powerUp.getBounds())) {
                // Collect power-up
                powerUps.remove(i);
                applyPowerUp(powerUp.getType());
            }
        }
    }
    
    private void applyPowerUp(PowerUp.PowerUpType type) {
        switch (type) {
            case EXTRA_LIFE:
                lives++;
                break;
            case DOUBLE_SCORE:
                score += 500; // Bonus points
                break;
            case SPEED_BOOST:
                // Speed boost would require modifying player movement speed
                score += 200;
                break;
            case RAPID_FIRE:
                // Rapid fire would require reducing shot cooldown temporarily
                score += 300;
                break;
        }
    }
    
    private void checkWinCondition() {
        if (enemies.isEmpty()) {
            // Player wins this level/wave
            score += 1000;
            // Respawn enemies for continuous gameplay
            for (int i = 0; i < 5; i++) {
                enemies.add(new Enemy(100 + i * 120, 100));
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch (gameState) {
            case MENU:
                drawMenu(g2d);
                break;
            case PLAYING:
                drawGame(g2d);
                break;
            case PAUSED:
                drawGame(g2d);
                drawPauseOverlay(g2d);
                break;
            case GAME_OVER:
                drawGameOver(g2d);
                break;
        }
    }
    
    private void drawMenu(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        
        String title = "RETRO GAMING";
        int x = (width - fm.stringWidth(title)) / 2;
        int y = height / 2 - 50;
        g.drawString(title, x, y);
        
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        fm = g.getFontMetrics();
        String instruction = "Press SPACE to start";
        x = (width - fm.stringWidth(instruction)) / 2;
        y = height / 2 + 20;
        g.drawString(instruction, x, y);
    }
    
    private void drawGame(Graphics2D g) {
        // Draw game entities
        if (player != null) {
            player.draw(g);
        }
        
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
        
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }
        
        // Draw HUD
        drawHUD(g);
    }
    
    private void drawHUD(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Score
        g.drawString("Score: " + score, 10, 25);
        
        // Lives
        g.drawString("Lives: " + lives, 10, 45);
        
        // Time
        long elapsedTime = (System.currentTimeMillis() - gameStartTime) / 1000;
        g.drawString("Time: " + elapsedTime + "s", 10, 65);
    }
    
    private void drawPauseOverlay(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, width, height);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g.getFontMetrics();
        
        String pauseText = "PAUSED";
        int x = (width - fm.stringWidth(pauseText)) / 2;
        int y = height / 2;
        g.drawString(pauseText, x, y);
    }
    
    private void drawGameOver(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        
        String gameOverText = "GAME OVER";
        int x = (width - fm.stringWidth(gameOverText)) / 2;
        int y = height / 2 - 50;
        g.drawString(gameOverText, x, y);
        
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        fm = g.getFontMetrics();
        String scoreText = "Final Score: " + score;
        x = (width - fm.stringWidth(scoreText)) / 2;
        y = height / 2 + 20;
        g.drawString(scoreText, x, y);
    }
    
    // Getters and setters
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState gameState) { this.gameState = gameState; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public Player getPlayer() { return player; }
    public List<Enemy> getEnemies() { return enemies; }
}