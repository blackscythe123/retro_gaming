#!/bin/bash

# Retro Gaming Build Script

echo "Building Retro Gaming Project..."

# Create build directory
mkdir -p build

# Compile all Java files
javac -d build -sourcepath src src/com/retrogaming/main/RetroGameMain.java

if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo "To run the game: java -cp build com.retrogaming.main.RetroGameMain"
else
    echo "Build failed!"
    exit 1
fi