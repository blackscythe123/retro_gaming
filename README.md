# Retro Gaming

A collaborative retro gaming repository implemented in Java using Swing. This project features a classic arcade-style game with modern object-oriented design.

## Features

- **Classic Arcade Gameplay**: Move your player character around the screen to avoid enemies and collect power-ups
- **Shooting Mechanics**: Fire projectiles at enemies to eliminate them and score points
- **Power-up System**: Collect various power-ups for bonuses:
  - Speed Boost (Green)
  - Extra Life (Pink)
  - Double Score (Orange)
  - Rapid Fire (Magenta)
- **Collision Detection**: Realistic collision system between all game entities
- **Game States**: Menu, Playing, Paused, and Game Over states
- **Score System**: Earn points by defeating enemies and collecting power-ups
- **Lives System**: Start with 3 lives, lose lives when touching enemies

## Controls

- **Arrow Keys or WASD**: Move the player character
- **Space Bar**: Shoot projectiles (when playing) or start game (from menu)
- **Escape**: Pause game (planned feature)

## Project Structure

```
src/com/retrogaming/
├── main/           # Application entry point
├── game/           # Core game logic and game loop
├── entities/       # Game entities (Player, Enemy, Projectile, PowerUp)
├── ui/             # User interface components
└── utils/          # Utility classes (Input handling, game calculations)
```

## Building and Running

### Option 1: Using the build script
```bash
./build.sh
java -cp build com.retrogaming.main.RetroGameMain
```

### Option 2: Manual compilation
```bash
mkdir -p build
javac -d build -sourcepath src src/com/retrogaming/main/RetroGameMain.java
java -cp build com.retrogaming.main.RetroGameMain
```

## Requirements

- Java 8 or higher
- GUI environment (the game uses Java Swing)

## Game Architecture

The game follows object-oriented design principles:

- **Entity System**: Base `Entity` class with specialized subclasses
- **Game Loop**: Fixed timestep game loop at 60 FPS
- **Input Handling**: Event-driven keyboard input system
- **Collision Detection**: Rectangle-based collision detection
- **State Management**: Game state pattern for different screens

## Contributing

This is a collaborative project. When contributing:

1. Follow the existing code style and structure
2. Add appropriate comments for new features
3. Test your changes thoroughly
4. Keep the retro gaming aesthetic and feel

## Future Enhancements

- Sound effects and background music
- Multiple levels with increasing difficulty
- Different enemy types with unique behaviors
- Animated sprites instead of simple shapes
- High score persistence
- Two-player mode
- More power-up types and effects

---

Created as a collaborative retro gaming project using Java and Swing.
