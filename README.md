# Sudoku Solver (Java Swing)

## Overview
Sudoku Solver is a Java desktop application that solves Sudoku puzzles using the Backtracking algorithm. Users can enter an incomplete Sudoku puzzle, click the Solve button, and instantly view the completed solution.

## Features
- 9×9 Sudoku Grid
- Solve Sudoku using Backtracking
- User-friendly Java Swing GUI
- Clear and Reset buttons
- Input validation (only digits 1–9)
- Highlights solved cells
- Responsive desktop interface

## Technologies Used
- Java
- Java Swing
- Backtracking Algorithm
- Object-Oriented Programming (OOP)

## How It Works
1. Enter the Sudoku puzzle.
2. Leave empty cells blank.
3. Click the **Solve** button.
4. The program fills in the correct solution.

## Project Structure

SudokuSolver/
├── SudokuSolver.java
├── SudokuGUI.java
└── README.md

## Algorithm
The application uses the **Backtracking Algorithm**.

1. Find an empty cell.
2. Try numbers from 1 to 9.
3. Check whether placing the number is valid.
4. Continue recursively.
5. If a dead end is reached, backtrack.
6. Continue until the puzzle is solved.

## Output

- Enter Sudoku puzzle
- Click Solve
- Solved puzzle displayed instantly

## Future Enhancements

- Dark Mode
- Random Sudoku Generator
- Difficulty Levels
- Timer
- Hint System
- Step-by-Step Visualization

## Author

Your Name