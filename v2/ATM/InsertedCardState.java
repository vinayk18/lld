package ATM;

public class InsertedCardState implements State {

    @Override
    public void readCard(Card card, ATM atm) {
        throw new IllegalStateException("Card already inserted");
    }

    @Override
    public void readPIN(int pin, ATM atm) {

        if (atm.authenticatePIN(pin)) {
            atm.resetPinRetry();
            atm.setState(new AuthenticatedState());
            return;
        }

        atm.incrementPinRetry();

        if (atm.pinRetriesExhausted()) {
            atm.reset();   // eject card + go Idle
            throw new RuntimeException("Invalid PIN. Card ejected.");
        }

        throw new RuntimeException("Invalid PIN. Try again.");
    }

    @Override
    public void selectTransaction(TransactionType txn, int amount, ATM atm) {
        throw new IllegalStateException("Authenticate first");
    }

    @Override
    public void cancel(ATM atm) {
        atm.reset();   // eject card + Idle
    }
}