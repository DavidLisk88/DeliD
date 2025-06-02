package com.pluralsight.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pluralsight.Constants;
import com.pluralsight.Food.Cheese;
import com.pluralsight.Food.Meat;
import com.pluralsight.Food.Regular;
import com.pluralsight.Food.Sauce;
import com.pluralsight.Food.Sandwich;
import com.pluralsight.Food.Toppings;

/**
 * Guides the user through building one Sandwich:
 *   1) Select size (4", 8", 12")
 *   2) Select bread type (WHITE, WHEAT, RYE, WRAP)
 *   3) Enter all topping codes at once (meats, cheeses, regulars, sauces)
 *      → After choosing, prompt for extra portions on any premium toppings.
 *   4) Ask if it should be toasted
 */
public class AddSandwichScreen {
    private Scanner scanner;

    public AddSandwichScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public Sandwich buildSandwich() {
        // 1) Prompt for size
        Constants.Size size = promptForSize();

        // 2) Prompt for bread type
        Constants.BreadType breadType = promptForBread();

        Sandwich sandwich = new Sandwich(size, breadType);

        // 3) Let user pick all toppings in one line, then ask extras
        buildToppings(sandwich);

        // 4) Ask if toasted
        System.out.print("\nWould you like this sandwich toasted? (y/n): ");
        String toastedChoice = scanner.nextLine().trim().toLowerCase();
        if (toastedChoice.equals("y")) {
            sandwich.setToasted(true);
        }

        System.out.println("\nSandwich created:\n" + sandwich);
        return sandwich;
    }

    private Constants.Size promptForSize() {
        while (true) {
            System.out.println("\nSelect sandwich size:");
            System.out.println("1) 4\"   2) 8\"   3) 12\"");
            System.out.print("Enter choice: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": return Constants.Size.FOUR;
                case "2": return Constants.Size.EIGHT;
                case "3": return Constants.Size.TWELVE;
                default:
                    System.out.println("Invalid entry. Please choose 1, 2, or 3.");
            }
        }
    }

    private Constants.BreadType promptForBread() {
        while (true) {
            System.out.println("\nSelect bread type:");
            System.out.println("1) White   2) Wheat   3) Rye   4) Wrap");
            System.out.print("Enter choice: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": return Constants.BreadType.WHITE;
                case "2": return Constants.BreadType.WHEAT;
                case "3": return Constants.BreadType.RYE;
                case "4": return Constants.BreadType.WRAP;
                default:
                    System.out.println("Invalid entry. Please choose 1-4.");
            }
        }
    }

    private void buildToppings(Sandwich sandwich) {
        System.out.println("\nToppings Menu (enter codes separated by spaces, or 0 to skip):");
        System.out.println("Meats (premium):");
        System.out.println("  a) Steak   b) Ham   c) Salami   d) Roast Beef   e) Chicken   f) Bacon");
        System.out.println("Cheeses (premium):");
        System.out.println("  g) American   h) Provolone   i) Cheddar   j) Swiss");
        System.out.println("Regular toppings (no cost):");
        System.out.println("  k) Lettuce   l) Peppers   m) Onions   n) Tomatoes   o) Jalapeños");
        System.out.println("  p) Cucumbers   q) Pickles   r) Guacamole   s) Mushrooms");
        System.out.println("Sauces (no cost):");
        System.out.println("  t) Mayo   u) Mustard   v) Ketchup   w) Ranch   x) Thousand Islands   y) Vinaigrette");
        System.out.print("\nEnter all topping codes now (e.g. a c k t), or 0 for none: ");

        String line = scanner.nextLine().trim().toLowerCase();
        if (line.equals("0") || line.isEmpty()) {
            System.out.println("No toppings selected.");
            return;
        }

        String[] codes = line.split("\\s+");
        List<Toppings> premiumToppings = new ArrayList<>();

        for (String code : codes) {
            Toppings topping = null;
            switch (code) {
                // Meats
                case "a": topping = new Meat("Steak");       break;
                case "b": topping = new Meat("Ham");         break;
                case "c": topping = new Meat("Salami");      break;
                case "d": topping = new Meat("Roast Beef");  break;
                case "e": topping = new Meat("Chicken");     break;
                case "f": topping = new Meat("Bacon");       break;
                // Cheeses
                case "g": topping = new Cheese("American");   break;
                case "h": topping = new Cheese("Provolone");  break;
                case "i": topping = new Cheese("Cheddar");    break;
                case "j": topping = new Cheese("Swiss");      break;
                // Regulars
                case "k": topping = new Regular("Lettuce");     break;
                case "l": topping = new Regular("Peppers");     break;
                case "m": topping = new Regular("Onions");      break;
                case "n": topping = new Regular("Tomatoes");    break;
                case "o": topping = new Regular("Jalapeños");   break;
                case "p": topping = new Regular("Cucumbers");   break;
                case "q": topping = new Regular("Pickles");     break;
                case "r": topping = new Regular("Guacamole");   break;
                case "s": topping = new Regular("Mushrooms");   break;
                // Sauces
                case "t": topping = new Sauce("Mayo");            break;
                case "u": topping = new Sauce("Mustard");         break;
                case "v": topping = new Sauce("Ketchup");         break;
                case "w": topping = new Sauce("Ranch");           break;
                case "x": topping = new Sauce("Thousand Islands");break;
                case "y": topping = new Sauce("Vinaigrette");     break;
                default:
                    System.out.println("Invalid code \"" + code + "\" - skipping.");
                    continue;
            }

            sandwich.addTopping(topping);
            System.out.println("Added: " + topping.getName());
            // Collect premium toppings to prompt for extras later
            if (topping instanceof Meat || topping instanceof Cheese) {
                premiumToppings.add(topping);
            }
        }

        // Now prompt extras for any premium topping chosen
        for (Toppings premium : premiumToppings) {
            while (true) {
                System.out.print("How many extra portions of " + premium.getName() + "? (enter 0 for none): ");
                String extraInput = scanner.nextLine().trim();
                try {
                    int extras = Integer.parseInt(extraInput);
                    if (extras < 0) {
                        System.out.println("Please enter 0 or a positive integer.");
                        continue;
                    }
                    premium.setExtraCount(extras);
                    if (extras > 0) {
                        System.out.println("Set " + extras + " extra portion(s) of " + premium.getName() + ".");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Try again.");
                }
            }
        }
    }
}
