public class CashDispenser {
    private int amount = 10000;

    private int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    public void dispenseCash(int cash){
        if( cash <= getAmount()){
            System.out.println("Cash dispensed successfully");
            setAmount(amount-cash);
        }
        else
            System.out.println("not enough cash in machine to dispense!!");
    }
}
