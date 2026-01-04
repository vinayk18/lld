package CoffeeMachine;

public class IdleState implements State {

    @Override
    public void makeBeverage(CoffeeMachine machine, Beverage beverage) {
        try {
            machine.getOutletManager().acquireOutlet();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Outlet acquisition interrupted");
        }

        boolean success =
                machine.getInventory().validateAndConsume(beverage.getRecipe());

        if (!success) {
            machine.getOutletManager().releaseOutlet();
            throw new IllegalStateException("Insufficient ingredients");
        }

        machine.setState(new DispenseState());
        machine.dispenseAsync(beverage);
    }
}