public class Item {
    private final String ID;
    private final String name;
    private final int price;

    public Item(String id, String name, int price) {
        ID = id;
        this.name = name;
        this.price = price;
    }

    public String getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}
