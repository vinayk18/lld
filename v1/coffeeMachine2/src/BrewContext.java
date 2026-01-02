import java.util.Map;

public interface BrewContext {
    void dispenseMessage(String message);
    boolean useIngredients(Map<Ingredient, Integer> required);
    Map<Ingredient, Integer> getRecipe();
}
