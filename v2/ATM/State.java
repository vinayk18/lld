package ATM;

public interface State {
    void readCard( Card card, ATM atm);
    void readPIN( int PIN, ATM atm);
    void selectTransaction( TransactionType txn,int amount,ATM atm);
    void cancel(ATM atm);
}
