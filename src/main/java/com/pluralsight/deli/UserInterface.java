package com.pluralsight.deli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

    public void displayHomeScreen() {
        while (true) {
            System.out.println("\n\n\n\n\n");
            System.out.println("=================================================");
            System.out.println("  MALONE'S DELI                                  ");
            System.out.println("  \"Why waste time say lot word?\"                 ");
            System.out.println("=================================================");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Selection: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                currentOrder = new Order();
                displayOrderScreen();
            } else if (choice.equals("0")) {
                System.out.println("\nThanks for stopping by Malone's Deli!");
                break;
            }
        }
    }

    private void displayOrderScreen() {
        while (true) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("   MAIN SELECTION MENU");
            System.out.println("-------------------------------------------------");
            System.out.println("1) Build Sando");
            System.out.println("2) Breakroom Vending Machines (Drinks & Chips)");
            System.out.println("3) Complete & Pay");
            System.out.println("0) Cancel Order");
            System.out.print("Selection: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) addSandwichScreen();
            else if (choice.equals("2")) displayVendingMachineMenu();
            else if (choice.equals("3")) {
                if (checkoutScreen()) return;
            } else if (choice.equals("0")) {
                System.out.println("\nOrder cancelled.");
                return;
            }
        }
    }

    private void displayVendingMachineMenu() {
        while (true) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("   BREAKROOM VENDING MACHINES");
            System.out.println("-------------------------------------------------");
            System.out.println("1) Purchase a Cold Drink");
            System.out.println("2) Purchase a Bag of Chips");
            System.out.println("0) Back to Main Menu");
            System.out.print("Selection: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) addDrinkScreen();
            else if (choice.equals("2")) addChipsScreen();
            else if (choice.equals("0")) break;
            else System.out.println("Invalid selection.");
        }
    }

    private void printLiveCart(Sandwich s) {
        System.out.println("\n=================================================");
        System.out.println("           What's going on in the Warehouse ?      ");
        System.out.println("=================================================");
        System.out.print(s.getDescription());
        System.out.println("=================================================\n");
    }

    private void addSandwichScreen() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("   SANDWICH CONFIGURATOR");
        System.out.println("-------------------------------------------------");

        int size = 4;
        System.out.println("Select Size:");
        System.out.println("1) 4\" The Little Howard\n2) 8\" The Halpert\n3) 12\" The Big Tuna");
        System.out.print("Selection: ");
        String sizeChoice = scanner.nextLine();
        if (sizeChoice.equals("2")) size = 8;
        else if (sizeChoice.equals("3")) size = 12;

        String bread = "White";
        System.out.println("\nSelect Bread Type:");
        System.out.println("1) White\n2) Wheat\n3) Rye\n4) Wrap");
        System.out.print("Selection: ");
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

        System.out.println("Select Base Premium Meat:");
        for (int i = 0; i < meatsList.length; i++) {
            System.out.printf("%d) %s\n", i + 1, meatsList[i]);
        }
        System.out.println("7) No Meat (The Martin)");
        System.out.print("Selection: ");
        String meatChoice = scanner.nextLine();
        int meatIdx = Integer.parseInt(meatChoice) - 1;

        if (meatIdx >= 0 && meatIdx < meatsList.length) {
            s.addMeat(meatsList[meatIdx]);
        }
        printLiveCart(s);

        System.out.print("Add extra meat? Florida Stanely approved portions, of course. (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\nSelect extra meat type to stack:");
            for (int i = 0; i < meatsList.length; i++) {
                System.out.printf("%d) Extra %s\n", i + 1, meatsList[i]);
            }
            System.out.print("Selection: ");
            int extraMeatIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (extraMeatIdx >= 0 && extraMeatIdx < meatsList.length) {
                s.addExtraMeat(meatsList[extraMeatIdx]);
            }
        }
        printLiveCart(s);

        System.out.println("Select Base Premium Cheese:");
        for (int i = 0; i < cheesesList.length; i++) {
            System.out.printf("%d) %s\n", i + 1, cheesesList[i]);
        }
        System.out.println("5) No Cheese");
        System.out.print("Selection: ");
        String cheeseChoice = scanner.nextLine();
        int cheeseIdx = Integer.parseInt(cheeseChoice) - 1;

        if (cheeseIdx >= 0 && cheeseIdx < cheesesList.length) {
            s.addCheese(cheesesList[cheeseIdx]);
        }
        printLiveCart(s);

        System.out.print("Add extra cheese? Why not make it an Ultrafeast ? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\nSelect extra cheese type to stack:");
            for (int i = 0; i < cheesesList.length; i++) {
                System.out.printf("%d) Extra %s\n", i + 1, cheesesList[i]);
            }
            System.out.print("Selection: ");
            int extraCheeseIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (extraCheeseIdx >= 0 && extraCheeseIdx < cheesesList.length) {
                s.addExtraCheese(cheesesList[extraCheeseIdx]);
            }
        }
        printLiveCart(s);

        String[] veggiesList = {"Lettuce", "Tomatoes", "Onions", "Peppers", "Pickles", "Jalapenos", "Cucumbers"};
        System.out.println("Select Veggies (Type numbers separated by commas, e.g., 1,2,5 or enter '0' for none):");
        for (int i = 0; i < veggiesList.length; i++) {
            System.out.printf("%d) %s\n", i + 1, veggiesList[i]);
        }
        System.out.print("Selection: ");
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
        System.out.println("Select Sauces (Type numbers separated by commas, e.g., 2,4 or enter '0' for none):");
        for (int i = 0; i < saucesList.length; i++) {
            System.out.printf("%d) %s\n", i + 1, saucesList[i]);
        }
        System.out.print("Selection: ");
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

        System.out.print("Toasted? ON THE JOB ! >:0 (yes/no): ");
        s.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));
        printLiveCart(s);

        currentOrder.addItem(s);
        System.out.println("Sando added to order tray!");
    }

    private void addDrinkScreen() {
        System.out.println("\nSelect Volume:");
        System.out.println("1) Scott's Tot (Small)\n2) The Toby (Medium)\n3) Did I Stutter?! (Large)");
        System.out.print("Selection: ");
        String sizeChoice = scanner.nextLine();
        String size = "Small";
        if (sizeChoice.equals("2")) size = "Medium";
        else if (sizeChoice.equals("3")) size = "Large";

        System.out.println("\nSelect Flavor:");
        System.out.println("1) Beet Juice\n2) Ice Cold Coffee \n3) Oscar's Tears\n4) Wine from Robert California's Cellar");
        System.out.print("Selection: ");
        String flavorChoice = scanner.nextLine();
        String flavor = "Grape Soda";
        switch (flavorChoice) {
            case "2" -> flavor = "Orange Soda";
            case "3" -> flavor = "Root Beer";
            case "4" -> flavor = "Celcius Energy Drink";
        }

        currentOrder.addItem(new Drink(size, flavor));
        System.out.println("Drink vended successfully.");
    }

    private void addChipsScreen() {
        System.out.println("\nSelect Chips Bag:");
        System.out.println("1) Prison Mike Salted\n2) Scranton Strangler Jalapeno\n3) Mose's Mesquite BBQ\n4) Jan's Baked Apple Chips");
        System.out.print("Selection: ");
        String chipChoice = scanner.nextLine();
        String type = "Salted";
        switch (chipChoice) {
            case "2" -> type = "Scranton Strangler Jalapeno";
            case "3" -> type = "BBQ";
            case "4" -> type = "Baked Apple";
        }

        currentOrder.addItem(new Chips(type));
        System.out.println("Chips dropped from the coil.");
    }

    private boolean checkoutScreen() {
        if (currentOrder.getItemCount() == 0) {
            System.out.println("\nYour tray is empty! Add some items before checking out.");
            return false;
        }

        // 1. Clear terminal workspace using explicit page breaks
        System.out.println("\n\n\n\n\n");
        System.out.println("-------------------------------------------------");
        System.out.println("   PROCESSING PAYMENT...                         ");
        System.out.println("-------------------------------------------------\n");

        // 2. Automatically save receipt in background (Ensure ReceiptManager does not have a print statement inside it)
        ReceiptManager.saveReceipt(currentOrder);

        // 3. Print final customer invoice panel
        System.out.print(currentOrder.getOrderDetails());

        // 4. Generate timestamp calculations
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime readyTime = now.plusMinutes(10);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

        System.out.println("=================================================");
        System.out.println("  ORDER RECEIVED!                                ");
        System.out.println("=================================================");
        System.out.printf("  Order Time:   %s\n", now.format(timeFormat));
        System.out.printf("  Estimated Ready Time: %s (In 10 mins)\n", readyTime.format(timeFormat));
        System.out.println("-------------------------------------------------");
        System.out.println("  We are assembling your order right now.        ");
        System.out.println("  \"And that... is Dallas.\" See you at counter!  ");
        System.out.println("=================================================");

        // 5. Block the loop from progressing automatically back to the main menu
        System.out.println("\nThank you for choosing Malone's Deli ! We'll catch you on the flippity flip !");
        System.out.print("Press ENTER to return to the Annex.");
        scanner.nextLine();

        return true;
    }
}