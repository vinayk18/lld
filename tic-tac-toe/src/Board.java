public class Board {
    private int size;
    private char EMPTY = '#';
    private char[][] board = new char[3][3];

    public Board(int size){
        this.size = size;
        for(int i = 0;i<size;i++){
            for( int j=0;j<size;j++){
                board[i][j] = EMPTY;
            }
        }
    }

    public int getSize() {
        return size;
    }

    private void validateCoords(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new IllegalArgumentException("Invalid coordinates: (" + x + "," + y + ")");
    }

    public char getPos(int x, int y) {
        validateCoords(x, y);
        return board[x][y];
    }

    public boolean updatePos(int x,int y, char move){
        validateCoords(x, y);
        if(board[x][y] != '#'){
            return false;
        }
        board[x][y] = move;
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------\n");
        for(int i = 0;i<size;i++){
            for( int j=0;j<size;j++){
                sb.append(getPos(i,j)).append(" ");
            }
            sb.append('\n');
        }
        sb.append("-----------------\n");
        return sb.toString();
    }

    public void print(){
        System.out.println(this.toString());
    }
}
