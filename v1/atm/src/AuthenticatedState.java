public class AuthenticatedState implements ATMState{

    private ATMContext ctx;

    AuthenticatedState(ATMContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void readCard( ATMCard card) {
        ctx.display("card already present");
    }

    @Override
    public void enterPIN(int pin) {
        ctx.display("card already authenticated");
    }

    @Override
    public void selectOperation(TRANSACTION_TYPE transactionType) {
        if( transactionType == TRANSACTION_TYPE.WITHDRAW )
            ctx.updateTransactionStrategy(new WithdrawStrategy());
        ctx.changeState(new ProcessTransactionState(transactionType,ctx));
    }

    @Override
    public void processTransaction(int amount,int accoundId) {

    }

    @Override
    public void dispenseCash(int amount) {

    }

    @Override
    public void ejectCard( ) {

    }

    @Override
    public void cancel( ) {

    }
}
