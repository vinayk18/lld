package TicTacToe;

public class Board {

    private static final int SIZE = 3;
    private final Symbol[][] grid = new Symbol[SIZE][SIZE];

    public boolean placeMove(int row, int col, Symbol symbol) {
        if (!isValidCell(row, col)) return false;
        if (grid[row][col] != null) return false;

        grid[row][col] = symbol;
        return true;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public boolean hasWinner(Symbol s) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == s && grid[i][1] == s && grid[i][2] == s) return true;
            if (grid[0][i] == s && grid[1][i] == s && grid[2][i] == s) return true;
        }

        if (grid[0][0] == s && grid[1][1] == s && grid[2][2] == s) return true;
        if (grid[0][2] == s && grid[1][1] == s && grid[2][0] == s) return true;

        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == null) return false;
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] == null ? "- " : grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}