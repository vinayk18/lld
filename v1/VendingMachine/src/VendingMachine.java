import java.util.LinkedHashMap;
import java.util.Map;

public class VendingMachine {
    private State state;
    private final Map<String,Slot> slots = new LinkedHashMap<>();
    private int balance = 0;
    private String selectedCode = null;

    public VendingMachine(){
        this.state = new NoCoinState();
    }

    public void setState(State s){
        this.state = s;
    }

    public State getState(){
        return state;
    }

    public void addSlot(Slot s){
        slots.put(s.getCode(),s);
    }

    public Slot getSlot(String code){
        return slots.get(code);
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public int getBalance(){
        return balance;
    }

    public String getSelectedCode(){
        return  selectedCode;
    }

    public void setSelectedCode( String code ){
        this.selectedCode = code;
    }

    public void clearSelection(){
        this.selectedCode = null;
    }

    public int refundBalance(){
        int returnBalance = balance;
        balance = 0;
        return returnBalance;
    }
    public void deductBalance(int cents) { this.balance -= cents; if (balance < 0) balance = 0; }
    public void insertCoin(int cents) { state.insertCoin(this, cents); }
    public void ejectCoin() { state.ejectCoin(this); }
    public void selectItem(String code) { state.selectItem(this, code); }
    public void dispense() { state.dispense(this); }
    public void printStatus() {
        System.out.println("=== VendingMachine Status ===");
        System.out.println("State: " + state.getClass().getSimpleName());
        System.out.println("Balance: " + balance + " cents");
        System.out.println("Selected: " + selectedCode);
        System.out.println("Slots:");
        for (Slot s : slots.values()) {
            System.out.printf("%s - %s - %dc - qty:%d\n",
                    s.getCode(), s.getItem().getName(), s.getItem().getPrice(), s.getQuantity());
        }
        System.out.println("=============================");
    }


}
