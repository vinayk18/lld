package ATM;

import java.util.HashMap;
import java.util.Map;

public class CashInventory {

    private final Map<Integer, Integer> notes = new HashMap<>();

    public CashInventory(Map<Integer, Integer> initialNotes) {
        this.notes.putAll(initialNotes);
    }
    public synchronized boolean canDispense(int amount) {
        int remaining = amount;

        for (int denom : notes.keySet().stream().sorted((a, b) -> b - a).toList()) {
            int available = notes.get(denom);
            int needed = Math.min(remaining / denom, available);
            remaining -= needed * denom;
        }

        return remaining == 0;
    }

    public synchronized Map<Integer, Integer> deduct(int amount) {
        Map<Integer, Integer> dispensed = new HashMap<>();
        int remaining = amount;

        for (int denom : notes.keySet().stream().sorted((a, b) -> b - a).toList()) {
            int available = notes.get(denom);
            int needed = Math.min(remaining / denom, available);

            if (needed > 0) {
                notes.put(denom, available - needed);
                dispensed.put(denom, needed);
                remaining -= needed * denom;
            }
        }

        if (remaining != 0) {
            throw new IllegalStateException("Invariant broken: cash deducted without validation");
        }

        return dispensed;
    }
}