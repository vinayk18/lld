public class Slot {
    private final String code;
    private final Item item;
    private final int capacity;
    private int quantity;

    public Slot(String code, Item item, int capacity, int quantity) {
        this.code = code;
        this.item = item;
        this.capacity = capacity;
        this.quantity = quantity;
    }

    public String getCode(){
        return code;
    }

    public Item getItem(){
        return item;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getQuantity(){
        return quantity;
    }

    public boolean isEmpty(){
        return getQuantity() == 0;
    }

}
