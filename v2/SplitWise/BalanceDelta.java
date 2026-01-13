package SplitWise;

public class BalanceDelta {

    private final User from;   // debtor
    private final User to;     // creditor
    private final int amount;  // positive

    public BalanceDelta(User from, User to, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }
}