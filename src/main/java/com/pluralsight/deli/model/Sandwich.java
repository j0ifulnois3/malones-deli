package com.pluralsight.deli.model;

import com.pluralsight.deli.data.OrderItem;
import com.pluralsight.deli.data.PriceConstants;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {

    private int size;
    private String bread;
    private boolean isToasted;
    private List<String> toppings;
    private List<String> sauces;
    private List<String> extraMeats;
    private List<String> extraCheeses;

    // Tracks premium base toppings separately for pricing
    private List<String> meats;
    private List<String> cheeses;

    public Sandwich(int size, String bread) {
        this.size = size;
        this.bread = bread;
        this.toppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.extraMeats = new ArrayList<>();
        this.extraCheeses = new ArrayList<>();
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.isToasted = false;
    }

    // --- Topping adders ---

    public void addMeat(String meat) {
        this.meats.add(meat);
        this.toppings.add(meat);
    }

    public void addCheese(String cheese) {
        this.cheeses.add(cheese);
        this.toppings.add(cheese);
    }

    public void addVeggie(String veggie) {
        this.toppings.add(veggie);
    }

    public void addExtraMeat(String meat) {
        this.extraMeats.add(meat);
        this.toppings.add("Extra " + meat);
    }

    public void addExtraCheese(String cheese) {
        this.extraCheeses.add(cheese);
        this.toppings.add("Extra " + cheese);
    }

    public void addSauce(String sauce) {
        this.sauces.add(sauce);
    }

    // --- Getter & setter ---

    public void setToasted(boolean toasted) {
        this.isToasted = toasted;
    }

    public List<String> getToppings() { return this.toppings; }
    public List<String> getSauces()   { return this.sauces; }

    // --- Pricing logic ---

    @Override
    public double getPrice() {
        int idx = PriceConstants.getSizeIndex(size);
        double total = PriceConstants.BREAD_PRICES[idx];

        // Base premium meat charge (one per meat added)
        total += meats.size() * PriceConstants.MEAT_PRICES[idx];

        // Extra meat upcharge
        total += extraMeats.size() * PriceConstants.EXTRA_MEAT_PRICES[idx];

        // Base premium cheese charge
        total += cheeses.size() * PriceConstants.CHEESE_PRICES[idx];

        // Extra cheese upcharge
        total += extraCheeses.size() * PriceConstants.EXTRA_CHEESE_PRICES[idx];

        return total;
    }

    // --- Receipt / live-cart description ---

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d\" %s Sandwich%s - $%.2f\n",
                size, bread, isToasted ? " [TOASTED]" : "", getPrice()));

        if (!toppings.isEmpty()) {
            sb.append("  Toppings: ");
            sb.append(String.join(", ", toppings));
            sb.append("\n");
        }

        if (!sauces.isEmpty()) {
            sb.append("  Sauces:   ");
            sb.append(String.join(", ", sauces));
            sb.append("\n");
        }

        return sb.toString();
    }
}