public interface TransactionStrategy {
     void execute(ATMContext ctx,int amount,int accountId);
}
