package com.pluralsight.deli;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private int size; // 4, 8, or 12
    private String breadType; // White, Wheat, Rye, Wrap
    private boolean isToasted;

    private List<String> meatToppings;
    private List<String> cheeseToppings;
    private List<String> regularToppings; // Veggies, sauces

    private boolean extraMeat;
    private boolean extraCheese;

    public Sandwich(int size, String breadType) {
        this.size = size;
        this.breadType = breadType;
        this.meatToppings = new ArrayList<>();
        this.cheeseToppings = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.isToasted = false;
        this.extraMeat = false;
        this.extraCheese = false;
    }

    public void setToasted(boolean toasted) { this.isToasted = toasted; }
    public void setExtraMeat(boolean extraMeat) { this.extraMeat = extraMeat; }
    public void setExtraCheese(boolean extraCheese) { this.extraCheese = extraCheese; }

    public void addMeat(String meat) { this.meatToppings.add(meat); }
    public void addCheese(String cheese) { this.cheeseToppings.add(cheese); }
    public void addRegularTopping(String topping) { this.regularToppings.add(topping); }

    @Override
    public double getPrice() {
        int idx = PriceConstants.getSizeIndex(size);
        double totalPrice = PriceConstants.BREAD_PRICES[idx];

        // Apply premium meat math if selected
        if (!meatToppings.isEmpty()) {
            totalPrice += PriceConstants.MEAT_PRICES[idx];
            if (extraMeat) {
                totalPrice += PriceConstants.EXTRA_MEAT_PRICES[idx];
            }
        }

        // Apply premium cheese math if selected
        if (!cheeseToppings.isEmpty()) {
            totalPrice += PriceConstants.CHEESE_PRICES[idx];
            if (extraCheese) {
                totalPrice += PriceConstants.EXTRA_CHEESE_PRICES[idx];
            }
        }

        return totalPrice;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d\" %s Sando", size, breadType));
        if (isToasted) sb.append(" (Toasted)");
        sb.append(String.format(" - $%.2f\n", getPrice()));

        if (!meatToppings.isEmpty()) {
            sb.append("   Meats: ").append(meatToppings).append(extraMeat ? " (Extra)\n" : "\n");
        }
        if (!cheeseToppings.isEmpty()) {
            sb.append("   Cheeses: ").append(cheeseToppings).append(extraCheese ? " (Extra)\n" : "\n");
        }
        if (!regularToppings.isEmpty()) {
            sb.append("   Toppings: ").append(regularToppings).append("\n");
        }

        return sb.toString();
    }
}