package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * A regular topping that is always included at no extra cost.
 */
public class Regular extends Toppings {
    public Regular(String name) {
        super(name, Constants.ToppingCategory.REGULAR);
    }

    @Override
    public double calculatePrice(Constants.Size size) {
        return 0.0; // regular toppings are included at no additional cost
    }
}
