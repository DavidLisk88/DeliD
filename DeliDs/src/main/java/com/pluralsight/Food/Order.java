package com.pluralsight.Food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a customer’s Order, which can contain multiple LineItems.
 * Generates its own timestamp (dateTime) when constructed.
 */
public class Order {
    private LocalDateTime dateTime;
    private List<LineItems> items;

    public Order() {
        this.dateTime = LocalDateTime.now();
        this.items    = new ArrayList<>();
    }

    public void addItem(LineItems item) {
        // Add newest items at index 0 so they display in reverse-chronological order
        items.add(0, item);
    }

    public void removeItem(LineItems item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (LineItems li : items) {
            total += li.calculatePrice();
        }
        return total;
    }

    public List<LineItems> getItems() {
        return Collections.unmodifiableList(items);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order placed: ").append(dateTime).append("\n");
        for (LineItems li : items) {
            sb.append(li.toString()).append("\n");
        }
        sb.append(String.format("Order Total: $%.2f", calculateTotal()));
        return sb.toString();
    }
}
