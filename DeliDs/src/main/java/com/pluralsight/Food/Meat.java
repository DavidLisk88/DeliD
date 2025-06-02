package com.pluralsight.Food;

import com.pluralsight.Constants;

/**
 * A premium “Meat” topping. Base price + extra portions are charged.
 */
public class Meat extends Toppings {
    public Meat(String name) {
        super(name, Constants.ToppingCategory.MEAT);
    }

    @Override
    public double calculatePrice(Constants.Size size) {
        double base = 0, extraUnit = 0;
        switch (size) {
            case FOUR:
                base      = Constants.MEAT_PRICE_4;
                extraUnit = Constants.EXTRA_MEAT_4;
                break;
            case EIGHT:
                base      = Constants.MEAT_PRICE_8;
                extraUnit = Constants.EXTRA_MEAT_8;
                break;
            case TWELVE:
                base      = Constants.MEAT_PRICE_12;
                extraUnit = Constants.EXTRA_MEAT_12;
                break;
        }
        return base + (extraCount * extraUnit);
    }
}
