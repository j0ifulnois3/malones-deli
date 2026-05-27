public class Sandwich implements OrderItem {
    // 1. Exact match to UML variables
    private int size;
    private String bread; // Changed from breadType
    private boolean isToasted;
    private List<String> toppings; // Single consolidated list for meats, cheeses, and veggies
    private List<String> sauces;   // Explicit list for sauces

    // Extra lists to track upcharges (can be left private/internal to calculate price)
    private List<String> extraMeats;
    private List<String> extraCheeses;

    public Sandwich(int size, String bread) {
        this.size = size;
        this.bread = bread;
        this.toppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.extraMeats = new ArrayList<>();
        this.extraCheeses = new ArrayList<>();
        this.isToasted = false;
    }

    // 2. Methods that interact with the lists
    public void addMeat(String meat) { this.toppings.add(meat); }
    public void addCheese(String cheese) { this.toppings.add(cheese); }
    public void addVeggie(String veggie) { this.toppings.add(veggie); }

    public void addExtraMeat(String meat) {
        this.extraMeats.add(meat);
        // Optional: you can also add it to the main list if you want it printed sequentially
    }

    public void addExtraCheese(String cheese) {
        this.extraCheeses.add(cheese);
    }

    public void addSauce(String sauce) { this.sauces.add(sauce); }

    // 3. Helper getters for the UserInterface logic
    public List<String> getToppings() { return this.toppings; }
    public List<String> getSauces() { return this.sauces; }

    // Internal upcharge calculations stay safely tucked inside getPrice()
    @Override
    public double getPrice() { ... }

    @Override
    public String getDescription() { ... }
}