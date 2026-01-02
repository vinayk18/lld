import java.util.Map;

public class TeaStrategy implements BeverageStrategy {

    @Override
    public void makeBeverage(BrewContext ctx) {
        ctx.useIngredients(ctx.getRecipe());
        System.out.println("making tea");
    }
}
