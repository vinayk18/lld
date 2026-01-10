package ATM;

public class IdleState implements State{

    @Override
    public void readCard(Card card, ATM atm) {
        atm.setCard(card);
        if(!atm.authenticateCard()){
            atm.ejectCard();
            throw new RuntimeException("invalid card");
        }
        atm.setState(new InsertedCardState());
    }

    @Override
    public void readPIN(int PIN, ATM atm) {
        throw new IllegalStateException("not allowed");
    }

    @Override
    public void selectTransaction(TransactionType txn, int amount , ATM atm) {
        throw new IllegalStateException("not allowed");
    }

    @Override
    public void cancel(ATM atm) {
        throw new IllegalStateException("not allowed");
    }
}
