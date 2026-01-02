public class ATM implements  ATMContext{
    private ATMCard card;
    private BankClient bankClient;
    private ATMState state;
    private TRANSACTION_TYPE transactionType;
    private TransactionStrategy transactionStrategy;
    private CardReader cardReader;
    private KeyPad keyPad;
    private CashDispenser cashDispenser;
    private Screen screen;

    public ATM(){
        this.card = null;
        bankClient = new BankClient();
        cardReader = new CardReader();
        keyPad = new KeyPad();
        state = new ReadyState(this);
        cashDispenser = new CashDispenser();
        screen = new Screen();
    }
    @Override
    public ATMCard getCard() {
        return card;
    }

    @Override
    public void ejectCard() {
        card = null;
    }

    @Override
    public boolean authenticate(ATMCard card, int pin) {
        return bankClient.authenticate(card,pin);
    }

    @Override
    public boolean processTransaction(int amount,int accountId) {
        state.processTransaction(amount,accountId);
        return true;
    }

    @Override
    public void changeState(ATMState state) {
        this.state = state;
        this.state.onEnter();
    }

    @Override
    public void updateTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }

    @Override
    public void withdrawCash(int amount) {
        bankClient.withdrawalValidation(card,amount);
        System.out.println("withdrawal approved for amount: " + amount);
    }

    @Override
    public void dispenseCash(int amount) {
        cashDispenser.dispenseCash(amount);
    }

    @Override
    public TransactionStrategy getTransactionStrategy() {
        return transactionStrategy;
    }

    @Override
    public void display(String message) {
        screen.display(message);
    }

    public void readCard(ATMCard card){
         this.card = card;
        state.readCard(card);
    }

    public void readPIN(){
        int PIN = keyPad.readInput();
        state.enterPIN(PIN);
    }

    public void selectionTransaction(TRANSACTION_TYPE transactionType){
        this.transactionType = transactionType;
        state.selectOperation(transactionType);
    }

    public TRANSACTION_TYPE getTransactionType(){
        return this.transactionType;
    }

}
