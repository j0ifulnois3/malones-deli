package com.pluralsight.deli.data;

import com.pluralsight.deli.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    public static void saveReceipt(Order order) {
        // Create the receipts folder if it doesn't exist
        File directory = new File("receipts");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Generate filename using the current timestamp format: yyyyMMdd-HHmmss.txt
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "receipts/" + now.format(formatter) + ".txt";

        // Write the order details straight to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(order.getOrderDetails());
            System.out.println("\nReceipt successfully saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error generating receipt file: " + e.getMessage());
        }
    }
}