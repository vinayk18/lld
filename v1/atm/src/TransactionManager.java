public class TransactionManager {
    private BankClient bankClient;
    private TransactionStrategy strategy;

    TransactionManager( BankClient bankClient ){
        this.bankClient = bankClient;
    }

    public void setStrategy(TransactionStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeOperation(TransactionStrategy strategy){
       // strategy.executeOperation();
    }
}
