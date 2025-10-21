public interface ATMContext {
    ATMCard getCard();
    void ejectCard();
    boolean authenticate(ATMCard card,int pin);
    boolean processTransaction(int amount,int accoundId);
    void changeState(ATMState state);
    void updateTransactionStrategy( TransactionStrategy transactionStrategy );
    void withdrawCash(int amount);
    void dispenseCash(int amount);
    TransactionStrategy getTransactionStrategy();
    void display(String message);
    TRANSACTION_TYPE getTransactionType();

}
