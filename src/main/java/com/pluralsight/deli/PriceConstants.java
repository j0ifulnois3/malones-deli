package com.pluralsight.deli;

public class PriceConstants {
    // Bread base pricing index mapped to sizes: 4", 8", 12"
    public static final double[] BREAD_PRICES = {5.50, 7.00, 8.50};

    // Premium Meat costs & extra meat upgrades
    public static final double[] MEAT_PRICES = {1.00, 2.00, 3.00};
    public static final double[] EXTRA_MEAT_PRICES = {0.50, 1.00, 1.50};

    // Premium Cheese costs & extra cheese upgrades
    public static final double[] CHEESE_PRICES = {0.75, 1.50, 2.25};
    public static final double[] EXTRA_CHEESE_PRICES = {0.30, 0.60, 0.90};

    // Drinks (Small, Medium, Large)
    public static final double[] DRINK_PRICES = {2.00, 2.50, 3.00};

    // Chips flat rate
    public static final double CHIPS_PRICE = 1.50;

    // Math helper matching sizing numbers to array positions
    public static int getSizeIndex(int size) {
        if (size == 4) return 0;
        if (size == 8) return 1;
        return 2; // Default fallback for 12"
    }
}