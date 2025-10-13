public class DispenseState implements State{

    @Override
    public void insertCoin(VendingMachine vm, int cents) {
        System.out.println("Please wait. Dispensing in progress.");
    }

    @Override
    public void ejectCoin(VendingMachine vm) {
        System.out.println("Cannot eject: currently dispensing.");
    }

    @Override
    public void selectItem(VendingMachine vm, String code) {
        System.out.println("Dispensing; selection ignored.");
    }

    @Override
    public void dispense(VendingMachine vm) {
        String code = vm.getSelectedCode();
        Slot slot = vm.getSlot(code);
        int price = slot.getItem().getPrice();
        vm.deductBalance(price);
        int change = vm.refundBalance(); // return remaining balance as change
        System.out.println("Dispensed: " + slot.getItem().getName() + ". Change returned: " + change + " cents.");

        vm.clearSelection();
        vm.setState(new NoCoinState());
    }
}
