package com.retrogaming.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles keyboard input for the game
 */
public class InputHandler implements KeyListener {
    private Set<Integer> pressedKeys;
    
    public InputHandler() {
        pressedKeys = new HashSet<>();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used for game input
    }
    
    // Movement keys
    public boolean isLeftPressed() {
        return pressedKeys.contains(KeyEvent.VK_LEFT) || pressedKeys.contains(KeyEvent.VK_A);
    }
    
    public boolean isRightPressed() {
        return pressedKeys.contains(KeyEvent.VK_RIGHT) || pressedKeys.contains(KeyEvent.VK_D);
    }
    
    public boolean isUpPressed() {
        return pressedKeys.contains(KeyEvent.VK_UP) || pressedKeys.contains(KeyEvent.VK_W);
    }
    
    public boolean isDownPressed() {
        return pressedKeys.contains(KeyEvent.VK_DOWN) || pressedKeys.contains(KeyEvent.VK_S);
    }
    
    // Action keys
    public boolean isSpacePressed() {
        return pressedKeys.contains(KeyEvent.VK_SPACE);
    }
    
    public boolean isEnterPressed() {
        return pressedKeys.contains(KeyEvent.VK_ENTER);
    }
    
    public boolean isEscapePressed() {
        return pressedKeys.contains(KeyEvent.VK_ESCAPE);
    }
    
    // Check if a specific key is pressed
    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }
    
    // Get all currently pressed keys (for debugging or advanced input handling)
    public Set<Integer> getPressedKeys() {
        return new HashSet<>(pressedKeys);
    }
    
    // Clear all pressed keys (useful for game state transitions)
    public void clearKeys() {
        pressedKeys.clear();
    }
}