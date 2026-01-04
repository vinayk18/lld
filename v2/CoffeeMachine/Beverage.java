package CoffeeMachine;

import java.util.HashMap;
import java.util.Map;

public abstract class Beverage {

    private final int id;
    private final String name;
    private final Map<Ingredient,Integer> recipe;

    public Beverage(int id, String name,Map<Ingredient, Integer> recipe) {
        this.id = id;
        this.name = name;
        this.recipe = Map.copyOf(recipe);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }
}
