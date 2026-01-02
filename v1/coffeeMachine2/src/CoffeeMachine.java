import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine implements BrewContext{
    private State state;
    private String selection;
    private Map<Beverage, Integer> menu = new HashMap<>();
    private Map<Ingredient,Integer> inventory = new HashMap<>();
    private BeverageStrategy strategy;
    private PaymentProcessor paymentProcessor;
    private final Map<Beverage, BeverageStrategy> strategies = Map.of(
            Beverage.COFFEE, new CoffeeStrategy(),
            Beverage.TEA, new TeaStrategy()
    );
    private final State readyState = new ReadyState();
    private final State hasCoinState = new HasCoinState();
    private final State hasSelectionState = new HasSelectionState();
    private final State dispenseState = new DispenseState();
    private Map<Ingredient, Integer> currentRecipe;

    public CoffeeMachine(){
        state = readyState;
        paymentProcessor = new PaymentProcessor();
        menu = Map.of(Beverage.COFFEE,20, Beverage.TEA,15);
        initializeInventory();

    }

    private void initializeInventory(){
        inventory.put(Ingredient.WATER, 500);  // in ml
        inventory.put(Ingredient.MILK, 300);
        inventory.put(Ingredient.COFFEE_POWDER, 100);
        inventory.put(Ingredient.TEA_LEAVES, 30);
        inventory.put(Ingredient.SUGAR, 100);
    }

    public void setStrategy(Beverage bvg){
        strategy = strategies.get(bvg);
    }

    public State getReadyState(){
        return readyState;
    }

    public State getHasCoinState(){
        return hasCoinState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getHasSelectionState(){
        return hasSelectionState;
    }

    public BeverageStrategy getStrategy(){
        return strategy;
    }

    public int getBalance(){
        return paymentProcessor.getBalance();
    }

    public void addBalance(int amount){
        paymentProcessor.addBalance(amount);
    }

    public void deductBalance(int amount){
        paymentProcessor.reduceBalance(amount);
    }

    public State getState(){
        return state;
    }

    public Beverage getSelection(){
        return Beverage.valueOf(selection.toUpperCase());
    }

    public void setState(State s){
        state = s;
    }

    public void setSelection(String s){
        selection = s;
        currentRecipe = Recipe.getRecipe(getSelection());
    }

    public int getPrice(Beverage bvg){
        return menu.get(bvg);
    }

    public void refund(){
       paymentProcessor.refund();
       state = readyState;
    }

    public int checkAvailability( Beverage bvg){
        return inventory.get(bvg);
    }



    public void insertCoin(int coins){
        state.acceptCoin(this,coins);
    }
    public void makeSelection( String s){
        state.makeSelection(this,s);
    }
    public void dispense( ) {
        state.dispense(this);
    }

    public void printStatus(){
        System.out.println("Printing coffee machine status");
        System.out.println("Balance: " + getBalance());
        System.out.println("Selection: " + getSelection().toString());
        System.out.println("State: " + getState());
    }

    @Override
    public void dispenseMessage(String message) {
        System.out.println(message);
    }

    @Override
    public boolean useIngredients(Map<Ingredient, Integer> required) {
        for (var entry : required.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int needed = entry.getValue();
            int available = inventory.getOrDefault(ingredient, 0);
            if (available < needed) {
                System.out.println("Not enough " + ingredient);
                return false;
            }
        }
        // Deduct after confirming availability
        required.forEach((ingredient, needed) ->
                inventory.put(ingredient, inventory.get(ingredient) - needed)
        );
        return true;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return currentRecipe;
    }

    public boolean hasRequiredIngredients() {
        if (currentRecipe == null) {
            System.out.println("No recipe selected yet.");
            return false;
        }

        for (Map.Entry<Ingredient, Integer> entry : currentRecipe.entrySet()) {
            int available = inventory.getOrDefault(entry.getKey(), 0);
            if (available < entry.getValue()) {
                System.out.println("Not enough " + entry.getKey());
                return false;
            }
        }
        return true;
    }
}
