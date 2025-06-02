package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * A sauce topping that is always included at no extra cost.
 */
public class Sauce extends Toppings {
    public Sauce(String name) {
        super(name, Constants.ToppingCategory.SAUCE);
    }

    @Override
    public double calculatePrice(Constants.Size size) {
        return 0.0; // sauces are free
    }
}
