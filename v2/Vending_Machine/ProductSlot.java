package Vending_Machine;

public class ProductSlot {
    private final Product product;
    private final int price;
    private  int quantity;

    public ProductSlot(Product product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    boolean hasStock() {
        return quantity > 0;
    }

    void deduct() {
        if(hasStock())
            quantity--;
    }
}
