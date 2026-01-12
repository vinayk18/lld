package TicTacToe;

public enum Symbol {
    X, O;

    public Symbol opposite() {
        return this == X ? O : X;
    }
}