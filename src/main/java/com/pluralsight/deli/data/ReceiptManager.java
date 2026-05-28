package com.pluralsight.deli.data;

import com.pluralsight.deli.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    public static void saveReceipt(Order order, String confirmationID, LocalDateTime now) {
        // Create receipts folder if it doesn't exist
        File directory = new File("receipts");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // File named by timestamp: yyyyMMdd-HHmmss.txt
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "receipts/" + now.format(fileFormat) + ".txt";

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime readyTime = now.plusMinutes(10);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            writer.write("=================================\n");
            writer.write("      MALONE'S DELI — RECEIPT    \n");
            writer.write("=================================\n");
            writer.write("  Confirmation:  " + confirmationID + "\n");
            writer.write("  Order Time:    " + now.format(timeFormat) + "\n");
            writer.write("  Pickup By:     " + readyTime.format(timeFormat) + "\n");
            writer.write("---------------------------------\n");
            writer.write("  Items Ordered:\n");
            for (String name : order.getItemNames()) {
                writer.write("    - " + name + "\n");
            }
            writer.write("---------------------------------\n");
            writer.write(String.format("  TOTAL DUE:  $%.2f%n", order.calculateTotal()));
            writer.write("=================================\n");

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}