# JnJ's Sando Bando - Point of Sale (POS) Application

Welcome to **JnJ's Sando Bando**, a custom terminal-based Point of Sale (POS) application engineered to take our sando shop operations to the next level. This system transitions our business from manual paper tickets to a streamlined, automated checkout process, allowing customers to fully customize their sandos, select sides, calculate accurate totals, and automatically generate receipts.

## Application Features

- **Dynamic Sando Customization**: Supports 4", 8", and 12" sando configurations across multiple bread choices (White, Wheat, Rye, Wrap).
- **Intelligent Upcharging**: Automatically computes accurate pricing dynamically across regular toppings and premium modifications like extra meats and cheeses.
- **Flexible Order Bundling**: Allows seamless custom additions of flavored drinks (Small, Medium, Large) and chips directly to the order.
- **Persistent Storage**: Exports structured order manifests on final customer confirmation directly to an isolated `receipts` folder using system timestamps (`yyyyMMdd-HHmmss.txt`).

## Application Architecture

The architecture utilizes robust Object-Oriented Design (OOD) principles to enforce consistency and decoupling across data objects:
- **`OrderItem`**: An interface standardizing components for calculation and display formats.
- **`Sandwich`, `Drink`, `Chips`**: Polymorphic data components encapsulating separate business logic and item prices.
- **`Order`**: An entity class holding collections of items to manage aggregate checkouts.

### Class Diagram

Below is the system design architecture for the shop.

![JnJsSandoBando.drawio.png](../../Diagrams/JnJsSandoBando.drawio.png)

