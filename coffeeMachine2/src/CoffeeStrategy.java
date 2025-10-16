import java.util.Map;

public class CoffeeStrategy implements BeverageStrategy {

    @Override
    public void makeBeverage( BrewContext ctx) {
        ctx.useIngredients(ctx.getRecipe());
        System.out.println("making coffee");
    }
}
