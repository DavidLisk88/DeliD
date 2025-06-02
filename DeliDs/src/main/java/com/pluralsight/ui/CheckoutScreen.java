package com.pluralsight.ui;

import java.io.IOException;
import java.util.Scanner;

import com.pluralsight.Food.Customer;
import com.pluralsight.Food.Order;
import com.pluralsight.ReceiptWriter;

/**
 * The CheckoutScreen now awards loyalty points if a named Customer is present.
 * Points = floor(finalTotal) (i.e., 1 point per whole dollar spent).
 */
public class CheckoutScreen {
    private Scanner scanner;
    private Order order;
    private Customer customer; // may be null if Guest

    public CheckoutScreen(Scanner scanner, Order order, Customer customer) {
        this.scanner  = scanner;
        this.order    = order;
        this.customer = customer;
    }

    /**
     * Returns true if order was confirmed (and receipt saved),
     * false if canceled. If confirmed and customer != null, award points.
     */
    public boolean processCheckout() {
        System.out.println("\n--- Checkout ---");
        System.out.println(order.toString());

        double subtotal = order.calculateTotal();
        System.out.printf("Subtotal: $%.2f%n", subtotal);

        System.out.print("\nConfirm order? (y to confirm; any other key to cancel): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            // 1) Save the receipt file
            try {
                ReceiptWriter.writeReceipt(order);
                System.out.println("Order confirmed. Receipt saved.");
            } catch (IOException e) {
                System.out.println("Error saving receipt: " + e.getMessage());
            }

            // 2) If we have a named customer, award loyalty points:
            if (customer != null) {
                // Earn 1 point per whole dollar spent:
                int pointsEarned = (int) Math.floor(subtotal);
                customer.addPoints(pointsEarned);
                System.out.printf("You have earned %d points!%n", pointsEarned);
                System.out.printf("Total points balance: %d%n", customer.getPoints());
            }

            return true;
        } else {
            System.out.println("Checkout canceled. Order discarded.");
            return false;
        }
    }
}
