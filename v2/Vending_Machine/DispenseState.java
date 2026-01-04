package Vending_Machine;

public class DispenseState implements State{

    VendingMachine vm;
    public DispenseState( VendingMachine vm ) {
        this.vm = vm;
    }

    @Override
    public void insertMoney(int money) {
        throw new IllegalStateException("invalid operation");
    }

    @Override
    public void makeSelection(int productId) {
        throw new IllegalStateException("invalid operation");
    }

    @Override
    public void dispense() {
       vm.dispenseSelectedProduct();
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("cannot cancel during dispensing");
    }
}
