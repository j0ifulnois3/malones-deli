package com.pluralsight.deli;

public class Drink implements OrderItem {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        if (size.equalsIgnoreCase("Small")) return PriceConstants.DRINK_PRICES[0];
        if (size.equalsIgnoreCase("Medium")) return PriceConstants.DRINK_PRICES[1];
        return PriceConstants.DRINK_PRICES[2];
    }

    @Override
    public String getDescription() {
        return String.format("%s %s Drink - $%.2f", size, flavor, getPrice());
    }
}
