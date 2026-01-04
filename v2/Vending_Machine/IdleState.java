package Vending_Machine;

public class IdleState implements State {
    VendingMachine vm;

    public IdleState(VendingMachine vendingMachine) {
        this.vm = vendingMachine;
    }

    @Override
    public void insertMoney(int money) {
         vm.addMoney(money);
         vm.setState(new HasMoneyState(vm));
    }

    @Override
    public void makeSelection(int productId) {
        throw new IllegalStateException("invalid operation");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("invalid operation");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Nothing to cancel");
    }
}
