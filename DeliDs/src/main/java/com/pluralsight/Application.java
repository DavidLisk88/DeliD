package com.pluralsight;

import java.util.Scanner;

import com.pluralsight.Food.Customer;
import com.pluralsight.ui.HomeScreen;


public class Application {
    public static void main(String[] args) {
        // 1) Load all saved customers (if customers.dat exists)
        Customer.loadAllCustomers();

        // 2) Launch the home screen (which will prompt for customer name)
        Scanner scanner = new Scanner(System.in);
        HomeScreen home = new HomeScreen(scanner);
        home.run();

        System.out.println("Thank you for eating at Deli D's! Goodbye!");
        scanner.close();
    }
}
