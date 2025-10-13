public class HasSelectionState implements State{

    @Override
    public void insertCoin(VendingMachine vm, int coin) {
        vm.addBalance(coin);
        System.out.println("coins inserted: "+coin);
    }

    @Override
    public void ejectCoin(VendingMachine vm) {
        int  coin = vm.refundBalance();
        System.out.println("coins refunded: "+ coin);
        vm.clearSelection();
        vm.setState(new NoCoinState());
    }

    @Override
    public void selectItem(VendingMachine vm, String code) {
        System.out.println("item already selected");
    }

    @Override
    public void dispense(VendingMachine vm) {
        String code = vm.getSelectedCode();
        Slot slot = vm.getSlot(code);
        int price = slot.getItem().getPrice();
        if( vm.getBalance() < price ){
            System.out.println("not enough balance");
            vm.setState(new HasCoinState());
            return;
        }

        vm.setState(new DispenseState());
        vm.dispense();
    }
}
