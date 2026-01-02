public class DispenseCashState implements ATMState{

    private int amount;
    private ATMContext ctx;

    DispenseCashState(int amount,ATMContext ctx){
        this.amount = amount;
        this.ctx = ctx;
    }

    @Override
    public void onEnter() {
        ctx.display("Dispensing ₹" + amount + " — please wait.");
        ctx.dispenseCash(amount); // blocks until done or fails
        ctx.ejectCard();
        ctx.changeState(new ReadyState(ctx));
    }

    @Override
    public void readCard(  ATMCard card) {
        ctx.display("card already present");
    }

    @Override
    public void enterPIN( int pin) {
        ctx.display("card already authenticated");
    }

    @Override
    public void selectOperation(TRANSACTION_TYPE transactionType) {
        ctx.display("operation type already selected");
    }

    @Override
    public void processTransaction( int amount,int accountId) {
    }

    @Override
    public void dispenseCash( int amount) {
        ctx.dispenseCash(amount);
    }

    @Override
    public void ejectCard( ) {

    }

    @Override
    public void cancel( ) {

    }
}
