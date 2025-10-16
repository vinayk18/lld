public interface State {
    public void acceptCoin(CoffeeMachine cm,int coin);
    public void makeSelection( CoffeeMachine cm , String code);
    public void dispense(CoffeeMachine cm);
 }
