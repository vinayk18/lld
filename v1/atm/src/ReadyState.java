public class ReadyState implements ATMState{
    private ATMContext ctx;

    ReadyState(ATMContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void readCard( ATMCard card) {
        ctx.changeState(new HasCardState(ctx,card));
    }

    @Override
    public void enterPIN(int pin) {

    }

    @Override
    public void selectOperation(TRANSACTION_TYPE transactionType) {

    }

    @Override
    public void processTransaction(int amount,int accoundId) {

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
