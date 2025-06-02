package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * A bag of chips line item.
 * Always has flat price = Constants.CHIPS_PRICE.
 */
public class Chips implements LineItems {
    private String type; // e.g., “Plain”, “BBQ”, etc.

    public Chips(String type) {
        this.type = type;
    }

    @Override
    public double calculatePrice() {
        return Constants.CHIPS_PRICE;
    }

    @Override
    public String getDescription() {
        return "Chips (" + type + "): $" + String.format("%.2f", calculatePrice());
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
