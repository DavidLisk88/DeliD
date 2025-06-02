package com.pluralsight.Food;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Customer loyalty system.
 *
 * Each customer has a name (lowercased internally) and a points balance.
 * Points are persisted to "customers.dat" on each change.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int points;

    // In-memory registry of all customers (key = lowercase name)
    private static Map<String, Customer> registry = new HashMap<>();

    // Private constructor; use get() to retrieve or create a Customer
    private Customer(String name, int initialPoints) {
        this.name   = name;
        this.points = initialPoints;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    /** Add points to this customer, then save to disk. */
    public void addPoints(int p) {
        this.points += p;
        saveAllCustomers();
    }

    /**
     * Redeem up to p points (if sufficient), then save.
     * @return true if redemption succeeded; false if not enough points.
     */
    public boolean redeemPoints(int p) {
        if (this.points >= p) {
            this.points -= p;
            saveAllCustomers();
            return true;
        }
        return false;
    }

    /**
     * Load all customers from "customers.dat". Call once on application startup.
     */
    @SuppressWarnings("unchecked")
    public static void loadAllCustomers() {
        File file = new File("src/main/resources/customers.dat");
        if (!file.exists()) {
            registry = new HashMap<>();
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            registry = (Map<String, Customer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Something went wrong reading the old file (e.g. NotSerializableException).
            System.out.println("Warning: could not load loyalty data. Starting with empty registry.");
            registry = new HashMap<>();
            // Optionally delete the corrupted file so we don’t keep hitting it:
            file.delete();
        }
    }

    /** Save the entire registry back to "customers.dat". */
    public static void saveAllCustomers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("customers.dat"))) {
            out.writeObject(registry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve an existing customer by name, or create a new one with 0 points.
     * Internally stores them in lowercase, but returns a Customer whose getName()
     * is the original capitalization the user typed.
     */
    public static Customer get(String nameInput) {
        String key = nameInput.toLowerCase();
        Customer c = registry.get(key);
        if (c == null) {
            c = new Customer(nameInput, 0);
            registry.put(key, c);
            saveAllCustomers();
        }
        return c;
    }
}
