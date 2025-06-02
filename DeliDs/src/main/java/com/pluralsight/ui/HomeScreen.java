package com.pluralsight.ui;

import java.util.Scanner;

import com.pluralsight.Food.Customer;

/**
 * HomeScreen for Deli D's POS.
 * Now displays "Deli D's POS" in the boxed title.
 * Prompts the user to enter a customer name (or 0 to exit).
 */
public class HomeScreen {
    private Scanner scanner;

    public HomeScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            // Prompt with an option to exit
            System.out.print("\nEnter customer name (or press Enter for Guest), or type 0 to Exit: ");
            String nameInput = scanner.nextLine().trim();

            if (nameInput.equals("0")) {
                return;
            }

            Customer customer = null;
            if (!nameInput.isEmpty()) {
                customer = Customer.get(nameInput);
                System.out.println("Welcome back, " + customer.getName() +
                        "! You have " + customer.getPoints() + " points.");
            } else {
                System.out.println("Proceeding as Guest (no loyalty points).");
            }

            // Display the main menu
            displayMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    // Start a new order for this Customer (or null for Guest)
                    OrderScreen orderScreen = new OrderScreen(scanner, customer);
                    orderScreen.startNewOrder();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid entry. Please choose 1 (New Order) or 0 (Exit).");
            }
        }
    }

    private void displayMenu() {
        UIHelper.printBoxedTitle(" Deli D's POS ", 30);
        System.out.println();
        UIHelper.printMenuOption("1) New Order", 20);
        UIHelper.printMenuOption("0) Exit", 20);
        System.out.print("\nEnter choice: ");
    }
}
