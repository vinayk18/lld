package TicTacToe;

public class Game {

    private final Board board = new Board();
    private Symbol currentTurn = Symbol.X;
    private GameState state = GameState.IN_PROGRESS;
    private Symbol winner;

    public boolean makeMove(Symbol player, int row, int col) {

        if (state != GameState.IN_PROGRESS) {
            System.out.println("Game already finished");
            return false;
        }

        if (player != currentTurn) {
            System.out.println("Not " + player + "'s turn");
            return false;
        }

        if (!board.placeMove(row, col, player)) {
            System.out.println("Invalid move");
            return false;
        }

        board.print();

        if (board.hasWinner(player)) {
            state = GameState.WIN;
            winner = player;
            System.out.println(player + " wins!");
            return true;
        }

        if (board.isFull()) {
            state = GameState.DRAW;
            System.out.println("Game is a draw");
            return true;
        }

        currentTurn = currentTurn.opposite();
        return true;
    }

    public GameState getState() {
        return state;
    }

    public Symbol getWinner() {
        return winner;
    }

    public Symbol getCurrentTurn() {
        return currentTurn;
    }
}