package com.pluralsight;

/**
 * Constants.java
 * Centralizes all fixed prices and enums.
 */
public class Constants {

    // --- Bread base prices by sandwich size ---
    public static final double BREAD_PRICE_4   = 5.50;
    public static final double BREAD_PRICE_8   = 7.00;
    public static final double BREAD_PRICE_12  = 8.50;

    // --- Meat prices (base and extra) per sandwich size ---
    public static final double MEAT_PRICE_4     = 1.00;
    public static final double MEAT_PRICE_8     = 2.00;
    public static final double MEAT_PRICE_12    = 3.00;
    public static final double EXTRA_MEAT_4     = 0.50;
    public static final double EXTRA_MEAT_8     = 1.00;
    public static final double EXTRA_MEAT_12    = 1.50;

    // --- Cheese prices (base and extra) per sandwich size ---
    public static final double CHEESE_PRICE_4   = 0.75;
    public static final double CHEESE_PRICE_8   = 1.50;
    public static final double CHEESE_PRICE_12  = 2.25;
    public static final double EXTRA_CHEESE_4   = 0.30;
    public static final double EXTRA_CHEESE_8   = 0.60;
    public static final double EXTRA_CHEESE_12  = 0.90;

    // --- Drinks (flat prices by size) ---
    public static final double DRINK_SMALL   = 2.00;
    public static final double DRINK_MEDIUM  = 2.50;
    public static final double DRINK_LARGE   = 3.00;

    // --- Chips (flat price) ---
    public static final double CHIPS_PRICE   = 1.50;

    // ================= ENUMS ======================

    /** Sandwich sizes **/
    public enum Size {
        FOUR,  // corresponds to 4"
        EIGHT, // corresponds to 8"
        TWELVE // corresponds to 12"
    }

    /** Bread types **/
    public enum BreadType {
        WHITE, WHEAT, RYE, WRAP
    }

    /** Topping categories **/
    public enum ToppingCategory {
        MEAT, CHEESE, REGULAR, SAUCE, SIDE
    }

    /** Drink sizes **/
    public enum DrinkSize {
        SMALL, MEDIUM, LARGE
    }
}
