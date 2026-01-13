package ATM;

public class ATM {
    private static final int MAX_PIN_RETRIES = 3;
    private State state = new IdleState();
    private Card card;
    private Account account;
    private TransactionType selectedTxn;
    private int withdrawAmount;
    private int pinRetryCount = 0;
    private final BankService bankService;

    public ATM(BankService bankService) {
        this.bankService = bankService;
    }

     public void setState(State state) {
        this.state = state;
    }

     public void setCard(Card card) {
        this.card = card;
    }

    public void ejectCard() {
        this.card = null;
    }

    public void reset() {
        ejectCard();
        account = null;
        selectedTxn = null;
        withdrawAmount = 0;
        pinRetryCount = 0;
        setState(new IdleState());
    }

     boolean authenticateCard() {
        return bankService.checkIsValidCard(card);
    }

    boolean authenticatePIN(int pin) {
        return bankService.checkIsValidPIN(card, pin);
    }

    void incrementPinRetry() {
        pinRetryCount++;
    }

    boolean pinRetriesExhausted() {
        return pinRetryCount >= MAX_PIN_RETRIES;
    }

    void resetPinRetry() {
        pinRetryCount = 0;
    }

     void setSelectedTxn(TransactionType txn, int amount) {
        this.selectedTxn = txn;
        this.withdrawAmount = amount;
    }

    public BankService getBankService() {
        return bankService;
    }

    public CashDispenser getCashDispenser() {
        //return cashDispenser;
        return null;
    }

    public TransactionType getSelectedTxn() {
        return selectedTxn;
    }

    public int getWithdrawAmount() {
        return withdrawAmount;
    }

    public Account getAccount() {
        return account;
    }
}