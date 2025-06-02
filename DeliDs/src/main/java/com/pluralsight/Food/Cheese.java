package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * A premium “Cheese” topping. Base price + extra portions are charged.
 */
public class Cheese extends Toppings {
    public Cheese(String name) {
        super(name, Constants.ToppingCategory.CHEESE);
    }

    @Override
    public double calculatePrice(Constants.Size size) {
        double base = 0, extraUnit = 0;
        switch (size) {
            case FOUR:
                base      = Constants.CHEESE_PRICE_4;
                extraUnit = Constants.EXTRA_CHEESE_4;
                break;
            case EIGHT:
                base      = Constants.CHEESE_PRICE_8;
                extraUnit = Constants.EXTRA_CHEESE_8;
                break;
            case TWELVE:
                base      = Constants.CHEESE_PRICE_12;
                extraUnit = Constants.EXTRA_CHEESE_12;
                break;
        }
        return base + (extraCount * extraUnit);
    }
}
