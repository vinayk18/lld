package ATM;

import java.util.Map;

public class CashDispenser {

    private final CashInventory inventory;

    public CashDispenser(CashInventory inventory) {
        this.inventory = inventory;
    }

    public void dispense(int amount) {
        if (!inventory.canDispense(amount)) {
            throw new IllegalStateException("ATM has insufficient cash");
        }

        Map<Integer, Integer> notes = inventory.deduct(amount);

        // Simulate physical dispensing
        for (Map.Entry<Integer, Integer> entry : notes.entrySet()) {
            System.out.println(
                    "Dispensing " + entry.getValue() + " notes of " + entry.getKey()
            );
        }

        System.out.println("Total dispensed: " + amount);
    }

    public boolean canDispense(int amount) {
       return inventory.canDispense(amount);
    }
}