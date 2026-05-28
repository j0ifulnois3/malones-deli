package com.pluralsight.deli.ui;

import com.pluralsight.deli.data.ReceiptManager;
import com.pluralsight.deli.model.Chips;
import com.pluralsight.deli.model.Drink;
import com.pluralsight.deli.model.Order;
import com.pluralsight.deli.model.Sandwich;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

    public void displayHomeScreen() {
        while (true) {
            // Replaced the blind newlines with a neat, intentional header block
            System.out.println("""
                
                ==========================================================
                                MALONE'S DELI TERMINAL
                ==========================================================
                     "Why waste time say lot word when few word do trick?"
                ----------------------------------------------------------
                 [1] New Order
                 [0] Exit Application
                ==========================================================
                """);
            System.out.print(" Selection: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                currentOrder = new Order();
                displayOrderScreen();
            } else if (choice.equals("0")) {
                System.out.println("\n Thanks for stopping by Malone's Deli! We'll catch you on the flippity flip.\n");
                break;
            } else {
                printInvalidNotice();
            }
        }
    }

    private void displayOrderScreen() {
        while (true) {
            System.out.println("""
                
                ==========================================================
                                  MAIN SELECTION MENU
                ==========================================================
                 [1] Create your Ultra-Feast (Build Your Sandwich)
                 [2] Breakroom Vending Machines (Select Drinks & Chips)
                 [3] Complete & Pay (Review Tray and Complete Purchase)
                 [0] Cancel Order
                ==========================================================
                """);
            System.out.print(" Selection: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) addSandwichScreen();
            else if (choice.equals("2")) displayVendingMachineMenu();
            else if (choice.equals("3")) {
                if (checkoutScreen()) return;
            } else if (choice.equals("0")) {
                System.out.println("\n [!] Order cancelled. Returning to the main menu.");
                return;
            } else {
                printInvalidNotice();
            }
        }
    }

    private void displayVendingMachineMenu() {
        while (true) {
            System.out.println("""
                
                ==========================================================
                                BREAKROOM VENDING MACHINES
                ==========================================================
                 [1] Purchase a Cold Drink
                 [2] Purchase a Bag of Chips
                 [0] Back to Main Menu
                ==========================================================
                """);
            System.out.print(" Selection: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) addDrinkScreen();
            else if (choice.equals("2")) addChipsScreen();
            else if (choice.equals("0")) break;
            else printInvalidNotice();
        }
    }

    private void printLiveCart(Sandwich s) {
        System.out.println("\n ┌──────────────────────────────────────────────────────┐");
        System.out.println("   SANDWICH BUILDER LIVE PREVIEW                        ");
        System.out.println(" └──────────────────────────────────────────────────────┘");
        System.out.print(s.getDescription());
        System.out.println(" ────────────────────────────────────────────────────────\n");
    }

    private void addSandwichScreen() {
        System.out.println("""
            
            ==========================================================
                               Build Your Sandwich
            ==========================================================
            """);

        int size = 4;
        System.out.println(" ── SELECT SIZE ──");
        System.out.println("  [1] 4\" Baby Halpert\n  [2] 8\" Just Jim\n  [3] 12\" The Big Tuna");
        System.out.print("\n Selection: ");
        String sizeChoice = scanner.nextLine();
        if (sizeChoice.equals("2")) size = 8;
        else if (sizeChoice.equals("3")) size = 12;

        String bread = "White";
        System.out.println("\n ── SELECT BREAD TYPE ──");
        System.out.println("  [1] White\n  [2] Wheat\n  [3] Rye\n  [4] Wrap");
        System.out.print("\n Selection: ");
        String breadChoice = scanner.nextLine();
        switch (breadChoice) {
            case "2" -> bread = "Wheat";
            case "3" -> bread = "Rye";
            case "4" -> bread = "Wrap";
        }

        Sandwich s = new Sandwich(size, bread);
        printLiveCart(s);

        String[] meatsList = {"Steak", "Ham", "Salami", "Roast Beef", "Turkey", "Chicken"};
        String[] cheesesList = {"American", "Provolone", "Cheddar", "Swiss"};

        System.out.println(" ── SELECT YOUR MEAT ──");
        for (int i = 0; i < meatsList.length; i++) {
            System.out.printf("  [%d] %s\n", i + 1, meatsList[i]);
        }
        System.out.println("  [7] No Meat (The Martin)");
        System.out.print("\n Selection: ");
        String meatChoice = scanner.nextLine();
        int meatIdx = Integer.parseInt(meatChoice) - 1;

        if (meatIdx >= 0 && meatIdx < meatsList.length) {
            s.addMeat(meatsList[meatIdx]);
        }
        printLiveCart(s);

        System.out.print(" Add extra meat? Florida Stanley approved portions, of course. (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\n ── STACK EXTRA MEAT ──");
            for (int i = 0; i < meatsList.length; i++) {
                System.out.printf("  [%d] Extra %s\n", i + 1, meatsList[i]);
            }
            System.out.print("\n Selection: ");
            int extraMeatIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (extraMeatIdx >= 0 && extraMeatIdx < meatsList.length) {
                s.addExtraMeat(meatsList[extraMeatIdx]);
            }
        }
        printLiveCart(s);

        System.out.println(" ── SELECT YOUR CHEESE ──");
        for (int i = 0; i < cheesesList.length; i++) {
            System.out.printf("  [%d] %s\n", i + 1, cheesesList[i]);
        }
        System.out.println("  [5] No Cheese");
        System.out.print("\n Selection: ");
        String cheeseChoice = scanner.nextLine();
        int cheeseIdx = Integer.parseInt(cheeseChoice) - 1;

        if (cheeseIdx >= 0 && cheeseIdx < cheesesList.length) {
            s.addCheese(cheesesList[cheeseIdx]);
        }
        printLiveCart(s);

        System.out.print(" Add extra cheese? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\n ── STACK EXTRA CHEESE ──");
            for (int i = 0; i < cheesesList.length; i++) {
                System.out.printf("  [%d] Extra %s\n", i + 1, cheesesList[i]);
            }
            System.out.print("\n Selection: ");
            int extraCheeseIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (extraCheeseIdx >= 0 && extraCheeseIdx < cheesesList.length) {
                s.addExtraCheese(cheesesList[extraCheeseIdx]);
            }
        }
        printLiveCart(s);

        String[] veggiesList = {"Lettuce", "Tomatoes", "Onions", "Peppers", "Pickles", "Jalapenos", "Cucumbers"};
        System.out.println(" ── FREE VEGGIE TOPPINGS ──");
        System.out.println(" (Type numbers separated by commas, e.g., 1,2,5 or enter '0' for none)");
        for (int i = 0; i < veggiesList.length; i++) {
            System.out.printf("  [%d] %s\n", i + 1, veggiesList[i]);
        }
        System.out.print("\n Selection: ");
        String vegInput = scanner.nextLine();
        if (!vegInput.trim().equals("0")) {
            String[] tokens = vegInput.split(",");
            for (String token : tokens) {
                try {
                    int idx = Integer.parseInt(token.trim()) - 1;
                    if (idx >= 0 && idx < veggiesList.length) {
                        s.addVeggie(veggiesList[idx]);
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        printLiveCart(s);

        String[] saucesList = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Island", "Vinaigrette"};
        System.out.println(" ── SAUCES & DRESSINGS ──");
        System.out.println(" (Type numbers separated by commas, e.g., 2,4 or enter '0' for none)");
        for (int i = 0; i < saucesList.length; i++) {
            System.out.printf("  [%d] %s\n", i + 1, saucesList[i]);
        }
        System.out.print("\n Selection: ");
        String sauceInput = scanner.nextLine();
        if (!sauceInput.trim().equals("0")) {
            String[] tokens = sauceInput.split(",");
            for (String token : tokens) {
                try {
                    int idx = Integer.parseInt(token.trim()) - 1;
                    if (idx >= 0 && idx < saucesList.length) {
                        s.addSauce(saucesList[idx]);
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        printLiveCart(s);

        System.out.print(" Toast the sandwich? (yes/no): ");
        s.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));
        printLiveCart(s);

        currentOrder.addItem(s);
        System.out.println(" » Success: Your order added to your order tray!");
    }

    private void addDrinkScreen() {
        System.out.println("\n ── SELECT VOLUME ──");
        System.out.println("  [1] Scott's Tot (Small)\n  [2] The Toby (Medium)\n  [3] Did I Stutter?! (Large)");
        System.out.print("\n Selection: ");
        String sizeChoice = scanner.nextLine();
        String size = "Small";
        if (sizeChoice.equals("2")) size = "Medium";
        else if (sizeChoice.equals("3")) size = "Large";

        System.out.println("\n ── SELECT FLAVOR ──");
        System.out.println("  [1] Schrute Farms Beet Juice (Fruit Punch)\n  [2] Ice Cold Coffee (Dr. Pepper) \n  [3] Ryan's Tears (Water)\n  [4] Wine from Robert California's Cellar (Grape Soda)");
        System.out.print("\n Selection: ");
        String flavorChoice = scanner.nextLine();
        String flavor = "Fruit Punch";
        switch (flavorChoice) {
            case "2" -> flavor = "Dr. Pepper";
            case "3" -> flavor = "Water";
            case "4" -> flavor = "Grape Soda";
        }

        currentOrder.addItem(new Drink(size, flavor));
        System.out.println(" » Success: Drink dropped into the dispenser.");
    }

    private void addChipsScreen() {
        System.out.println("\n ── SELECT CHIPS BAG ──");
        System.out.println("  [1] Prison Mike Salted Potato\n  [2] Scranton Strangler Jalapeno\n  [3] Mose's Mesquite BBQ\n  [4] Jan's Baked Apple Chips");
        System.out.print("\n Selection: ");
        String chipChoice = scanner.nextLine();
        String type = "Salted";
        switch (chipChoice) {
            case "2" -> type = "Scranton Strangler Jalapeno";
            case "3" -> type = "BBQ";
            case "4" -> type = "Baked Apple";
        }

        currentOrder.addItem(new Chips(type));
        System.out.println(" » Success: Chips dropped from the vending coil.");
    }

    private boolean checkoutScreen() {
        if (currentOrder.getItemCount() == 0) {
            System.out.println("\n [!] Your tray is empty! Add some items before checking out.");
            return false;
        }

        // ─── PAGE 1: PROCESSING + INVOICE DETAILS ────────────────────────────
        System.out.println("""
            
            ==========================================================
                               PROCESSING PAYMENT...
            ==========================================================
            """);

        System.out.print(currentOrder.getOrderDetails());

        System.out.println("==========================================================");
        System.out.print(" Confirm order and process payment? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("yes")) {
            System.out.println("\n [!] Order cancelled. Returning to the Annex.");
            return false;
        }

        // ─── PAGE 2: FINAL RECEIPT SLIP ────────────────────────────────────────
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime readyTime = now.plusMinutes(10);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

        DateTimeFormatter idFormat = DateTimeFormatter.ofPattern("MMddHHmm");
        String confirmationID = "#" + now.format(idFormat);

        ReceiptManager.saveReceipt(currentOrder, confirmationID, now);

        System.out.println("\n==========================================================");
        System.out.println("            ORDER RECEIVED — CUSTOMER TICKET              ");
        System.out.println("==========================================================");
        System.out.println("  Ticket ID:     " + confirmationID);
        System.out.printf( "  Order Time:    %s%n", now.format(timeFormat));
        System.out.printf( "  Est. Pickup:   %s  (approx. 10 mins)%n", readyTime.format(timeFormat));
        System.out.println("----------------------------------------------------------");
        System.out.println("  Items Purchased:");
        for (String line : currentOrder.getItemNames()) {
            System.out.println("    • " + line);
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf( "  TOTAL PAID:    $%.2f%n", currentOrder.calculateTotal());
        System.out.println("==========================================================");
        System.out.println("    Please present this ticket stub at the counter.       ");
        System.out.println("==========================================================");

        System.out.print("\n [Staff Action] Press ENTER to confirm customer pickup... ");
        scanner.nextLine();

        // ─── PAGE 3: OUTRO SCREEN ──────────────────────────────────────────────
        System.out.println("""
            
            ==========================================================
                             THANKS FOR VISITING!
            ==========================================================
              "And that... is Dallas."
              
              We'll catch you on the flippity flip!
            ==========================================================
            """);
        System.out.print(" Press ENTER to return to the Annex.");
        scanner.nextLine();

        return true;
    }

    private void printInvalidNotice() {
        System.out.println(" [!] Invalid option selection. Please try again.");
    }
}