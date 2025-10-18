public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        VendingMachine vm = new VendingMachine();

        // Add items/slots
        vm.addSlot(new Slot("A1", new Item("SODA001", "Soda", 125), 5, 2));
        vm.addSlot(new Slot("B2", new Item("CHIP001", "Chips", 75), 5, 1));
        vm.addSlot(new Slot("C3", new Item("CANDY01", "Candy", 50), 10, 0));

        vm.printStatus();

        // Scenario 1: try buy Soda with insufficient funds, then exact
        vm.insertCoin(100);              // insert $1.00
        vm.selectItem("A1");             // insufficient
        vm.insertCoin(25);               // now exact
        vm.selectItem("A1");             // selects
        vm.dispense();                   // dispenses soda
        vm.printStatus();
    }
}