package CoffeeMachine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine {

    private State state;
    private final Inventory inventory;
    private final OutletManager outletManager;
    private final ExecutorService executor;

    public CoffeeMachine(Inventory inventory, int outletCount) {
        this.inventory = inventory;
        this.state = new IdleState();
        this.outletManager = new OutletManager(outletCount);
        executor = Executors.newFixedThreadPool(outletCount);
    }

    public void makeBeverage(Beverage beverage) {
        state.makeBeverage(this, beverage);
    }

    Inventory getInventory() {
        return inventory;
    }

    void setState(State state) {
        this.state = state;
    }

    public OutletManager getOutletManager() {
        return outletManager;
    }

    void dispenseAsync(Beverage beverage) {
        executor.submit(() -> {
            try {
                System.out.println("Dispensing " + beverage.getName()
                        + " on " + Thread.currentThread().getName());
                Thread.sleep(500); // simulate dispense time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                outletManager.releaseOutlet();
                setState(new IdleState());
            }
        });
    }
}