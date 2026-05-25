package com.pluralsight.deli;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

    public void displayHomeScreen() {
        while (true) {
            System.out.println("\n=== WELCOME TO JnJ's SANDO BANDO ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                currentOrder = new Order();
                displayOrderScreen();
            } else if (choice.equals("0")) {
                System.out.println("Catch you on the flip side!");
                break;
            }
        }
    }

    private void displayOrderScreen() {
        while (true) {
            System.out.println("\n--- ORDER MENU ---");
            System.out.println("1) Add Sando\n2) Add Drink\n3) Add Chips\n4) Checkout\n0) Cancel Order");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) addSandwichScreen();
            else if (choice.equals("2")) addDrinkScreen();
            else if (choice.equals("3")) addChipsScreen();
            else if (choice.equals("4")) {
                if (checkoutScreen()) return;
            } else if (choice.equals("0")) {
                System.out.println("Order cancelled.");
                return;
            }
        }
    }

    private void addSandwichScreen() {
        System.out.print("Size (4, 8, 12): ");
        int size = Integer.parseInt(scanner.nextLine());
        System.out.print("Bread (White, Wheat, Rye, Wrap): ");
        String bread = scanner.nextLine();
        Sandwich s = new Sandwich(size, bread);

        System.out.print("Meat (or 'none'): ");
        String m = scanner.nextLine();
        if (!m.equalsIgnoreCase("none")) {
            s.addMeat(m);
            System.out.print("Extra meat? (yes/no): ");
            s.setExtraMeat(scanner.nextLine().equalsIgnoreCase("yes"));
        }

        System.out.print("Cheese (or 'none'): ");
        String ch = scanner.nextLine();
        if (!ch.equalsIgnoreCase("none")) {
            s.addCheese(ch);
            System.out.print("Extra cheese? (yes/no): ");
            s.setExtraCheese(scanner.nextLine().equalsIgnoreCase("yes"));
        }

        System.out.print("Veggies/Sauces (or 'none'): ");
        String v = scanner.nextLine();
        if (!v.equalsIgnoreCase("none")) s.addRegularTopping(v);

        System.out.print("Toasted? (yes/no): ");
        s.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));

        currentOrder.addItem(s);
        System.out.println("Sando added!");
    }

    private void addDrinkScreen() {
        System.out.print("Size (Small, Medium, Large): ");
        String s = scanner.nextLine();
        System.out.print("Flavor: ");
        currentOrder.addItem(new Drink(s, scanner.nextLine()));
        System.out.println("Drink added!");
    }

    private void addChipsScreen() {
        System.out.print("Type: ");
        currentOrder.addItem(new Chips(scanner.nextLine()));
        System.out.println("Chips added!");
    }

    private boolean checkoutScreen() {
        System.out.println(currentOrder.getOrderDetails());
        if (currentOrder.getItemCount() == 0) return false;
        System.out.print("Confirm purchase? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            ReceiptManager.saveReceipt(currentOrder);
            return true;
        }
        return false;
    }
}