package com.pluralsight.ui;

import java.util.Scanner;
import com.pluralsight.Constants;
import com.pluralsight.Food.Drink;

/**
 * Prompts user to build a single Drink (size + flavor) for Deli D's.
 * The user selects both size and flavor from fixed menus.
 */
public class AddDrinkScreen {
    private Scanner scanner;

    public AddDrinkScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public Drink buildDrink() {
        // Boxed header
        UIHelper.printBoxedTitle(" Deli D's - Add a Drink ", 40);
        System.out.println();

        // 1) Select size
        Constants.DrinkSize size = promptForDrinkSize();

        UIHelper.printHorizontalDivider(40);

        // 2) Select flavor from a fixed list
        String flavor = promptForDrinkFlavor();

        Drink drink = new Drink(size, flavor);

        UIHelper.printHorizontalDivider(40);
        System.out.println("Added: " + drink);
        UIHelper.printHorizontalDivider(40);

        return drink;
    }

    private Constants.DrinkSize promptForDrinkSize() {
        while (true) {
            System.out.println("Select drink size:");
            System.out.println("1) Small   2) Medium   3) Large");
            System.out.print("Enter choice: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": return Constants.DrinkSize.SMALL;
                case "2": return Constants.DrinkSize.MEDIUM;
                case "3": return Constants.DrinkSize.LARGE;
                default:
                    System.out.println("Invalid entry. Choose 1, 2, or 3.");
            }
        }
    }

    private String promptForDrinkFlavor() {
        // Fixed list of available drink flavors
        String[] flavors = {
                "Cola",
                "Lemonade",
                "Iced Tea",
                "Orange Juice"
        };

        System.out.println("Available drink flavors:");
        for (int i = 0; i < flavors.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, flavors[i]);
        }

        while (true) {
            System.out.print("Select flavor (1-" + flavors.length + "): ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= flavors.length) {
                    return flavors[choice - 1];
                } else {
                    System.out.println("Invalid entry. Choose a number between 1 and " + flavors.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Enter a number between 1 and " + flavors.length + ".");
            }
        }
    }
}
