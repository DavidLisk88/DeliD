package com.pluralsight.Food;

import java.util.ArrayList;
import java.util.List;
import com.pluralsight.Constants;

/**
 * Represents a single sandwich line item.
 * Implements LineItems so it can be added to an Order.
 */
public class Sandwich implements LineItems {
    private Constants.Size size;
    private Constants.BreadType breadType;
    private List<Toppings> toppings;
    private boolean toasted;

    public Sandwich(Constants.Size size, Constants.BreadType breadType) {
        this.size       = size;
        this.breadType  = breadType;
        this.toppings   = new ArrayList<>();
        this.toasted    = false;
    }

    public void addTopping(Toppings t) {
        toppings.add(t);
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public double calculatePrice() {
        double price = 0.0;

        // 1) Add base bread price
        switch (size) {
            case FOUR:
                price += Constants.BREAD_PRICE_4;
                break;
            case EIGHT:
                price += Constants.BREAD_PRICE_8;
                break;
            case TWELVE:
                price += Constants.BREAD_PRICE_12;
                break;
        }

        // 2) Add each topping’s price
        for (Toppings t : toppings) {
            price += t.calculatePrice(size);
        }

        return price;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType);
        if (toasted) {
            sb.append(" (toasted)");
        }
        sb.append("\n  Toppings:\n");
        for (Toppings t : toppings) {
            sb.append("    • ").append(t.getName());
            if (t.getExtraCount() > 0) {
                sb.append(" (extra x").append(t.getExtraCount()).append(")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getDescription() + String.format("  -> $%.2f", calculatePrice());
    }
}
