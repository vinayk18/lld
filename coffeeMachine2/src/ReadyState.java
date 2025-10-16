public class ReadyState implements State{

    @Override
    public void acceptCoin(CoffeeMachine cm ,int coin) {
        cm.addBalance(coin);
        System.out.println("accepted coin: "+ coin);
        cm.setState(cm.getHasCoinState());
    }

    @Override
    public void makeSelection(CoffeeMachine cm ,String code) {
        System.out.println("invalid");
    }

    @Override
    public void dispense(CoffeeMachine cm) {
        System.out.println("invalid");
    }
}
