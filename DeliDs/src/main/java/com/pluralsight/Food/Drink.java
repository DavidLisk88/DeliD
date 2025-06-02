package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * A single drink line item.
 * Price depends on Constants.DRINK_SMALL, DRINK_MEDIUM, DRINK_LARGE.
 */
public class Drink implements LineItems {
    private Constants.DrinkSize size;
    private String flavor; // e.g., “Cola”, “Lemonade”

    public Drink(Constants.DrinkSize size, String flavor) {
        this.size   = size;
        this.flavor = flavor;
    }

    @Override
    public double calculatePrice() {
        switch (size) {
            case SMALL:
                return Constants.DRINK_SMALL;
            case MEDIUM:
                return Constants.DRINK_MEDIUM;
            case LARGE:
                return Constants.DRINK_LARGE;
            default:
                return 0.0;
        }
    }

    @Override
    public String getDescription() {
        return size + " drink (" + flavor + "): $" +
                String.format("%.2f", calculatePrice());
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
