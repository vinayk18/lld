public class WithdrawStrategy implements TransactionStrategy{

    @Override
    public void execute(ATMContext ctx, int amount, int accountId) {
        ctx.withdrawCash(amount);
        ctx.display("withdrawing amount: "+ amount);
    }
}
