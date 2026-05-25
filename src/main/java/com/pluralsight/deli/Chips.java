package com.pluralsight.deli;

public class Chips implements OrderItem {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    @Override
    public double getPrice() {
        return PriceConstants.CHIPS_PRICE;
    }

    @Override
    public String getDescription() {
        return String.format("%s Chips - $%.2f", type, getPrice());
    }
}