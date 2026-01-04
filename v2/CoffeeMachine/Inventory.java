package CoffeeMachine;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {

    private final Map<Ingredient, Integer> stock;
    private final ReentrantLock lock = new ReentrantLock();

    public Inventory(Map<Ingredient, Integer> stock) {
        this.stock = stock;
    }

    public boolean validateAndConsume(Map<Ingredient, Integer> recipe) {
        lock.lock();
        try {
            for (var entry : recipe.entrySet()) {
                Ingredient ing = entry.getKey();
                int required = entry.getValue();
                if (stock.getOrDefault(ing, 0) < required) {
                    return false;
                }
            }

            for (var entry : recipe.entrySet()) {
                Ingredient ing = entry.getKey();
                stock.put(ing, stock.get(ing) - entry.getValue());
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void refill(Ingredient ingredient, int qty) {
        lock.lock();
        try {
            stock.put(ingredient, stock.getOrDefault(ingredient, 0) + qty);
        } finally {
            lock.unlock();
        }
    }
}