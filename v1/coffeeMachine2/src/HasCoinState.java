public class HasCoinState implements State{
    @Override
    public void acceptCoin(CoffeeMachine cm,int coin) {
        cm.addBalance(coin);
        System.out.println("accepted coin: "+ coin);
    }

    @Override
    public void makeSelection(CoffeeMachine cm ,String code) {
        int price = cm.getPrice(Beverage.valueOf(code.toUpperCase()));
        if( price > cm.getBalance() ){
            System.out.println("not enough coins");
            cm.setState(cm.getHasCoinState());
            return;
        }
        cm.setSelection(code);
        cm.setState(cm.getHasSelectionState());
    }

    @Override
    public void dispense(CoffeeMachine cm) {
        System.out.println("state invalid");
    }
}
