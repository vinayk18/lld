public class ProcessTransactionState implements ATMState{
    private TRANSACTION_TYPE transactionType;
    private ATMCard card;
    private ATMContext ctx;

    ProcessTransactionState(TRANSACTION_TYPE transactionType,ATMContext ctx){
        this.ctx = ctx;
        this.transactionType = transactionType;
    }

    @Override
    public void readCard(ATMCard card) {
        System.out.println("card already present");
    }

    @Override
    public void enterPIN(int pin) {
        ctx.display("card already authenticated");
    }

    @Override
    public void selectOperation(TRANSACTION_TYPE transactionType) {
        ctx.display("operation type already selected");
    }

    @Override
    public void processTransaction(int amount,int accountId) {
        ctx.getTransactionStrategy().execute(ctx,amount,accountId);

        if(ctx.getTransactionType().equals(TRANSACTION_TYPE.WITHDRAW))
            ctx.changeState(new DispenseCashState(amount,ctx));
    }

    @Override
    public void dispenseCash(int amount) {

    }

    @Override
    public void ejectCard() {

    }

    @Override
    public void cancel() {

    }
}
