package com.pluralsight.ui;

import java.util.Scanner;
import com.pluralsight.Food.Chips;

/**
 * Prompts user to build a single Chips item for Deli D's.
 * The user selects chip type from a fixed list.
 */
public class AddChipsScreen {
    private Scanner scanner;

    public AddChipsScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public Chips buildChips() {
        // Boxed header
        UIHelper.printBoxedTitle(" Deli D's - Add Chips ", 40);
        System.out.println();

        // Fixed list of chip types
        String[] chipTypes = {
                "Plain",
                "BBQ",
                "Salt & Vinegar",
                "Sour Cream & Onion"
        };

        System.out.println("Available chip types:");
        for (int i = 0; i < chipTypes.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, chipTypes[i]);
        }

        String type = null;
        while (true) {
            System.out.print("Select chip type (1-" + chipTypes.length + "): ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= chipTypes.length) {
                    type = chipTypes[choice - 1];
                    break;
                } else {
                    System.out.println("Invalid entry. Choose a number between 1 and " + chipTypes.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Enter a number between 1 and " + chipTypes.length + ".");
            }
        }

        Chips chips = new Chips(type);

        UIHelper.printHorizontalDivider(40);
        System.out.println("Added: " + chips);
        UIHelper.printHorizontalDivider(40);

        return chips;
    }
}
