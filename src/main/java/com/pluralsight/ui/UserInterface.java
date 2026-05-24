package com.pluralsight.deli;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void displayHomeScreen() {
        while (true) {
            System.out.println("\n=== WELCOME TO JnJ's SANDO BANDO ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                this.currentOrder = new Order();
                displayOrderScreen();
            } else if (choice.equals("0")) {
                System.out.println("Catch you on the flip side! Closing system...");
                break;
            } else {
                System.out.println("Invalid option. Give it another shot.");
            }
        }
    }

    private void displayOrderScreen() {
        while (true) {
            System.out.println("\n--- ORDER MENU ---");
            System.out.println("1) Add Custom Sando");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout / View Cart");
            System.out.println("0) Cancel Order");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addSandwichScreen();
                    break;
                case "2":
                    addDrinkScreen();
                    break;
                case "3":
                    addChipsScreen();
                    break;
                case "4":
                    if (checkoutScreen()) return; // If successfully checked out, return to main menu
                    break;
                case "0":
                    System.out.println("Order cancelled. Returning to main menu.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addSandwichScreen() {
        System.out.println("\n--- BUILD YOUR SANDO ---");
        System.out.print("Select size (4, 8, 12): ");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Select bread (White, Wheat, Rye, Wrap): ");
        String bread = scanner.nextLine();

        Sandwich sandwich = new Sandwich(size, bread);

        // Simple Meat Entry
        System.out.print("Add meat? (Steak, Ham, Salami, Roast Beef, Turkey, Chicken) or 'none': ");
        String meat = scanner.nextLine();
        if (!meat.equalsIgnoreCase("none")) {
            sandwich.addMeat(meat);
            System.out.print("Extra meat? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                sandwich.setExtraMeat(true);
            }
        }

        // Simple Cheese Entry
        System.out.print("Add cheese? (American, Provolone, Cheddar, Swiss) or 'none': ");
        String cheese = scanner.nextLine();
        if (!cheese.equalsIgnoreCase("none")) {
            sandwich.addCheese(cheese);
            System.out.print("Extra cheese? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                sandwich.setExtraCheese(true);
            }
        }

        // Quick inclusion of free veggies
        System.out.print("Add standard veggies? (Lettuce, Tomato, Onions, etc. Separated by commas) or 'none': ");
        String veggies = scanner.nextLine();
        if (!veggies.equalsIgnoreCase("none")) {
            sandwich.addRegularTopping(veggies);
        }

        System.out.print("Would you like it toasted? (yes/no): ");
        sandwich.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));

        currentOrder.addItem(sandwich);
        System.out.println("Sando added to your bando bag!");
    }

    private void addDrinkScreen() {
        System.out.println("\n--- SELECT DRINK ---");
        System.out.print("Select size (Small, Medium, Large): ");
        String size = scanner.nextLine();
        System.out.print("Select flavor (Cola, Lemonade, Root Beer, Water): ");
        String flavor = scanner.nextLine();

        currentOrder.addItem(new Drink(size, flavor));
        System.out.println("Drink added!");
    }

    private void addChipsScreen() {
        System.out.println("\n--- SELECT CHIPS ---");
        System.out.print("Select variety (Regular, BBQ, Sour Cream, Baked): ");
        String type = scanner.nextLine();

        currentOrder.addItem(new Chips(type));
        System.out.println("Chips added!");
    }

    private boolean checkoutScreen() {
        System.out.println("\n" + currentOrder.getOrderDetails());
        if (currentOrder.getItemCount() == 0) {
            System.out.println("Your cart is currently empty.");
            return false;
        }

        System.out.print("Confirm purchase? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            ReceiptManager.saveReceipt(currentOrder);
            System.out.println("Transaction finalized. Enjoy your meal!");
            return true;
        } else {
            System.out.println("Back to order menu.");
            return false;
        }
    }
}