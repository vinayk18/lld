import java.util.HashMap;
import java.util.Map;

public class Recipe {
    public static Map<Beverage, Map<Ingredient,Integer>> RECIPES = new HashMap<>();
    private static final Map<Ingredient, Integer> COFFEE_RECIPE = Map.of(
            Ingredient.WATER, 100,
            Ingredient.MILK, 50,
            Ingredient.COFFEE_POWDER, 10,
            Ingredient.SUGAR, 5
    );

    private static final Map<Ingredient, Integer> TEA_RECIPE = Map.of(
            Ingredient.WATER, 100,
            Ingredient.MILK, 50,
            Ingredient.TEA_LEAVES, 20,
            Ingredient.SUGAR, 10
    );

   static {
        RECIPES.put(Beverage.COFFEE,COFFEE_RECIPE);
        RECIPES.put(Beverage.TEA,TEA_RECIPE);
    }

    public static Map<Ingredient,Integer> getRecipe(Beverage bvg) {
        return RECIPES.get(bvg);
    }
}
