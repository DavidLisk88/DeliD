package com.pluralsight.ui;

import java.util.Scanner;

import com.pluralsight.Food.Chips;
import com.pluralsight.Food.Customer;
import com.pluralsight.Food.Drink;
import com.pluralsight.Food.LineItems;
import com.pluralsight.Food.Order;
import com.pluralsight.Food.Sandwich;

/**
 * The OrderScreen now knows which Customer (if any) is placing this order.
 * It will pass that Customer into CheckoutScreen so they can earn loyalty points.
 */
public class OrderScreen {
    private Scanner scanner;
    private Order currentOrder;
    private Customer customer; // may be null for Guest

    public OrderScreen(Scanner scanner, Customer customer) {
        this.scanner  = scanner;
        this.customer = customer;
    }

    public void startNewOrder() {
        currentOrder = new Order();
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    AddSandwichScreen sandwichScreen = new AddSandwichScreen(scanner);
                    Sandwich sandwich = sandwichScreen.buildSandwich();
                    currentOrder.addItem(sandwich);
                    break;
                case "2":
                    AddDrinkScreen drinkScreen = new AddDrinkScreen(scanner);
                    Drink drink = drinkScreen.buildDrink();
                    currentOrder.addItem(drink);
                    break;
                case "3":
                    AddChipsScreen chipsScreen = new AddChipsScreen(scanner);
                    Chips chips = chipsScreen.buildChips();
                    currentOrder.addItem(chips);
                    break;
                case "4":
                    // Pass the customer reference into CheckoutScreen
                    CheckoutScreen checkout = new CheckoutScreen(scanner, currentOrder, customer);
                    boolean success = checkout.processCheckout();
                    // After checkout (confirmed or canceled), always return to HomeScreen
                    return;
                case "0":
                    System.out.println("Order canceled. Returning to home screen.");
                    return;
                default:
                    System.out.println("Invalid option. Please select 1-4 or 0 to cancel order.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- New Order ---");
        System.out.println("Current items in order (" + currentOrder.getItems().size() + "):");
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("  (No items yet)");
        } else {
            for (LineItems item : currentOrder.getItems()) {
                System.out.println("  • " + item.toString());
            }
        }
        System.out.printf("Order Subtotal: $%.2f%n", currentOrder.calculateTotal());
        System.out.println("\n1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Enter choice: ");
    }
}
