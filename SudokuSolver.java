public class SudokuSolver {

    // Check if placing a number is valid
    public static boolean isSafe(int[][] board, int row, int col, int num) {

        // Check row
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num)
                return false;
        }

        // Check column
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num)
                return false;
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    // Solve Sudoku using Backtracking
    public static boolean solve(int[][] board) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isSafe(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board))
                                return true;

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // Print board (for testing)
    public static void printBoard(int[][] board) {

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }

    // Test the solver
    public static void main(String[] args) {

        int[][] board = {
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        if (solve(board)) {
            System.out.println("Solved Sudoku:\n");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}