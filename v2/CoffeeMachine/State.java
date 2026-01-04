package CoffeeMachine;


public interface State {
    void makeBeverage(CoffeeMachine machine, Beverage beverage);
}