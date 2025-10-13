public class NoCoinState implements State{

    @Override
    public void insertCoin(VendingMachine vm, int coin) {
        vm.addBalance(coin);
        System.out.println("coins inserted: "+coin);
        vm.setState(new HasCoinState());
    }

    @Override
    public void ejectCoin(VendingMachine vm) {
        System.out.println("no coin to eject");
    }

    @Override
    public void selectItem(VendingMachine vm, String code) {
        System.out.println("please insert coin first");
    }

    @Override
    public void dispense(VendingMachine vm) {
        System.out.println("no coin and no selection");
    }
}
