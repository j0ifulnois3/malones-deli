package com.pluralsight.deli.model;

import com.pluralsight.deli.data.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) { this.items.add(item); }
    public int getItemCount() { return items.size(); }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) { total += item.getPrice(); }
        return total;
    }

    public String getOrderDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("===================================\n");
        sb.append("        JnJ's SANDO BANDO          \n");
        sb.append("===================================\n");
        for (OrderItem item : items) { sb.append(item.getDescription()).append("\n"); }
        sb.append("-----------------------------------\n");
        sb.append(String.format("TOTAL DUE: $%.2f\n", calculateTotal()));
        sb.append("===================================\n");
        return sb.toString();
    }
}