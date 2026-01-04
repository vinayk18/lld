package Vending_Machine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private State state;
    private int availableBalance;
    private ProductSlot selection;
    private Map<Integer,ProductSlot> slots;

    VendingMachine(){
        state = new IdleState(this);
        availableBalance = 0;
        selection = null;
        slots = new HashMap<>();
        initVendingMachine();
    }

    private void initVendingMachine(){
        Product coke = new Product(1, "Coke");
        Product pepsi = new Product(2, "Pepsi");

        slots.put(1, new ProductSlot(coke, 10, 3));
        slots.put(2, new ProductSlot(pepsi, 15, 2));
    }

    void addMoney(int money) {
        availableBalance += money;
    }

    boolean selectProduct(int productId) {
        ProductSlot slot = slots.get(productId);
        if (slot == null) return false;
        this.selection = slot;
        return true;
    }

    boolean hasStockForSelection() {
        return selection != null && selection.hasStock();
    }

    boolean canAffordSelection() {
        return selection != null && availableBalance >= selection.getPrice();
    }

    void dispenseSelectedProduct() {
        selection.deduct();
        availableBalance -= selection.getPrice();
        reset();
    }

    void reset() {
        availableBalance = 0;
        selection = null;
        state = new IdleState(this);
    }

    void setState(State state) {
        this.state = state;
    }


    public void insertMoney(int money) {
        state.insertMoney(money);
    }

    public void makeSelection(int productId) {
        state.makeSelection(productId);
    }

    public void cancel() {
        state.cancel();
    }

    public void dispense() {
        state.dispense();
    }
}
