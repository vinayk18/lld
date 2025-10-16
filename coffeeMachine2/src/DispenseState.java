public class DispenseState implements State{

    @Override
    public void acceptCoin(CoffeeMachine cm,int coin) {
        System.out.println("invalid");
    }

    @Override
    public void makeSelection(CoffeeMachine cm ,String code) {
            System.out.println("invalid");
    }

    @Override
    public void dispense(CoffeeMachine cm) {
        Beverage bvg  = cm.getSelection();
        cm.setStrategy(bvg);
        cm.getStrategy().makeBeverage(cm);
        System.out.println("dispensed " + cm.getSelection().toString());
        cm.deductBalance(cm.getBalance());
        cm.setState(new ReadyState());
    }
}
