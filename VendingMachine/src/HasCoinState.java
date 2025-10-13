public class HasCoinState implements State{

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
       Slot slot = vm.getSlot(code);
       Item it = slot.getItem();
       if( it.getPrice() > vm.getBalance()){
           System.out.println("Insufficient balance");
       }
       vm.setSelectedCode(code);
       System.out.println("selected code");
       vm.setState(new HasSelectionState());
    }

    @Override
    public void dispense(VendingMachine vm) {
        System.out.println("no selection");
    }
}
