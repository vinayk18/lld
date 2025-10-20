public class Game {

    public Board board;
    Player player1, player2;
    Player lastMove;
    GameStatus status;

    private enum GameStatus {
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED,
        DRAW
    }

    public Game(){
        this.board = new Board(3);
        player1 = Player.PLAYER_1;
        player2 = Player.PLAYER_2;
        status = GameStatus.NOT_STARTED;
    }

    public Board getBoard() {
        return board;
    }

    public void startGame( int x, int y ){
        status = GameStatus.IN_PROGRESS;
        makeMove(x,y,Player.PLAYER_1);
    }

    public void makeMove( int x, int y, Player player ){
        if( !status.equals(GameStatus.IN_PROGRESS)){
            System.out.println("Game is already finished!!");
            return;
        }

        if(isPlayersTurn(player)){
            Player nextPlayer = (lastMove == Player.PLAYER_1)? Player.PLAYER_2 : Player.PLAYER_1;
            System.out.println("Incorrect player!! try again."+ nextPlayer+" should be playing");
            return;
        }

        char move = (player == Player.PLAYER_1)? 'X' : 'O';

        if(getBoard().updatePos(x,y,move)){
            lastMove = player;
        }
        else {
            System.out.println("position already occupied. try again");        }

        if( validateBoard(x,y) ){
            System.out.println("Game Over!! " + lastMove + " has won.");
            status = GameStatus.FINISHED;
        }

        if (getBoard().isFull()) {
            System.out.println("Game Over!! Draw.");
            status = GameStatus.DRAW;
            getBoard().print();
            return;
        }
        getBoard().print();
    }

    public boolean isPlayersTurn(Player player){
        return player == lastMove;
    }

    private boolean validateBoard(int x, int y) {
        int n = getBoard().getSize();
        char mark = getBoard().getPos(x, y);
        if (mark == '#') return false;

        // Check row
        boolean win = true;
        for (int col = 0; col < n; col++) {
            if (getBoard().getPos(x, col) != mark) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check column
        win = true;
        for (int row = 0; row < n; row++) {
            if (getBoard().getPos(row, y) != mark) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check main diagonal (only meaningful if on that diagonal, but cheap to check regardless)
        win = true;
        for (int i = 0; i < n; i++) {
            if (getBoard().getPos(i, i) != mark) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check anti-diagonal
        win = true;
        for (int i = 0; i < n; i++) {
            if (getBoard().getPos(i, n - 1 - i) != mark) {
                win = false;
                break;
            }
        }
        return win;
    }

    public void printStatus(){
        getBoard().print();
    }

}
