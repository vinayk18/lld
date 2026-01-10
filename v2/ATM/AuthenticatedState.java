package ATM;

public class AuthenticatedState implements State{

    @Override
    public void readCard(Card card, ATM atm) {
        throw new IllegalStateException("not allowed");
    }

    @Override
    public void readPIN(int PIN, ATM atm) {
        throw new IllegalStateException("not allowed");
    }

    @Override
    public void selectTransaction(TransactionType txn, int amount,ATM atm) {
        atm.setSelectedTxn(txn,amount);
        atm.setState(new DispenseState(atm));
    }

    @Override
    public void cancel(ATM atm) {
        atm.setState(new IdleState());
    }
}
