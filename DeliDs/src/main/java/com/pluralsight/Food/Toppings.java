package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * Abstract superclass for all toppings.
 * Each topping must implement calculatePrice(Size), using Constants for pricing.
 */
public abstract class Toppings {
    protected String name;
    protected Constants.ToppingCategory category;
    protected int extraCount; // 0 = normal portion; >0 = number of extra portions

    public Toppings(String name, Constants.ToppingCategory category) {
        this.name = name;
        this.category = category;
        this.extraCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getExtraCount() {
        return extraCount;
    }

    public void setExtraCount(int extraCount) {
        this.extraCount = extraCount;
    }

    /**
     * Calculate price based on sandwich size and number of extras.
     * Subclasses override to apply correct pricing rules per size.
     */
    public abstract double calculatePrice(Constants.Size size);
}
