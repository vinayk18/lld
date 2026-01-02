public class HasSelectionState implements State{
    @Override
    public void acceptCoin(CoffeeMachine cm,int coin) {
        cm.addBalance(coin);
        System.out.println("accepted coin");
    }

    @Override
    public void makeSelection(CoffeeMachine cm ,String code) {
        cm.setSelection(code);
        cm.setState(cm.getDispenseState());
    }

    @Override
    public void dispense(CoffeeMachine cm) {
        if(!cm.hasRequiredIngredients()){
            cm.refund();
            return;
        }
        cm.setState(cm.getDispenseState());
        cm.dispense();
    }
}
