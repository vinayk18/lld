package CoffeeMachine;

public class DispenseState implements State {

    @Override
    public void makeBeverage(CoffeeMachine machine, Beverage beverage) {
        // not used directly
        throw new UnsupportedOperationException("Already dispensing");
    }
}