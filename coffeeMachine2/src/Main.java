public class Main {
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine();
        cm.insertCoin(15);
        cm.makeSelection("Tea");
        cm.dispense();

        cm.insertCoin(15);
        cm.makeSelection("Tea");
        cm.dispense();
    }
}