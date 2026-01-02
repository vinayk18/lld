public class PaymentProcessor {
    private int balance;

    public PaymentProcessor(){
        this.balance = 0;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void refund(){
        System.out.println("returned amount succesfully: " + getBalance() );
        balance = 0;
    }

    public void reduceBalance(int amount){
        balance -= amount;
        System.out.println("balance reduced. Current balance: "+ balance);
    }

    public void addBalance(int amount){
        balance += amount;
        System.out.println("balance added. Current balance: "+ balance);
    }
}
