public interface State {
    void insertCoin(VendingMachine vm, int coin);
    void ejectCoin(VendingMachine vm);
    void selectItem(VendingMachine vm,String code);
    void dispense(VendingMachine vm);
}
