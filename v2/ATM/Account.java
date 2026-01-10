package ATM;

public class Account {
    private final int id;
    private final String name;
    int balance;

    public Account(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
