package Vending_Machine;

public interface State {
    void insertMoney(int money);
    void makeSelection(int productId);
    void dispense();
    void cancel();
}
