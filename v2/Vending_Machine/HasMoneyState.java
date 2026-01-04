package Vending_Machine;

public class HasMoneyState implements State{
    VendingMachine vm;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vm = vendingMachine;
    }

    @Override
    public void insertMoney(int money ) {
        vm.addMoney(money);
    }

    @Override
    public void makeSelection(int productId) {
        if (!vm.selectProduct(productId)) {
            throw new IllegalStateException("Invalid product");
        }

        if (!vm.hasStockForSelection()) {
            throw new IllegalStateException("Out of stock");
        }

        if (!vm.canAffordSelection()) {
            throw new IllegalStateException("Insufficient balance");
        }
        vm.setState(new DispenseState(vm));
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("invalid operation");
    }

    @Override
    public void cancel() {
       vm.reset();
    }
}
