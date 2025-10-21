public class HasCardState implements ATMState{
    private ATMContext ctx;
    private ATMCard card;

    HasCardState(ATMContext ctx, ATMCard card){
        this.ctx = ctx;
        this.card = card;
    }

    @Override
    public void readCard( ATMCard card) {
        ctx.display("card already present");
    }

    @Override
    public void enterPIN(int pin) {
        if(ctx.authenticate(card,pin))
            ctx.changeState(new AuthenticatedState(ctx));
        else
            ctx.changeState(new ReadyState(ctx));
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
