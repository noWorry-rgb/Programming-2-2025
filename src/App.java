import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public String storeName = "Slice-o-Heaven";
    public String storeAddress = "123 Pizza Street";
    public String storeEmail = "order@sliceoheaven.com";
    public String storePhone = "+1-800-PIZZA";
    
    private final String[] pizzaMenu = {
        "1. Pepperoni ($12.99)", 
        "2. Margherita ($11.99)", 
        "3. BBQ Chicken ($13.99)"
    };
    
    private final String[] sidesMenu = {
        "1. Garlic Bread ($3.50)", 
        "2. Salad ($4.00)", 
        "3. Fries ($2.50)"
    };
    
    private final String[] drinksMenu = {
        "1. Cola ($2.00)", 
        "2. Lemonade ($1.50)", 
        "3. Water ($1.00)"
    };
    
    private String orderID;
    private double orderTotal;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        App pizzeria = new App();
        pizzeria.startOrdering();
    }

    public void startOrdering() {
        System.out.println("=== Welcome to " + storeName + " ===");
        System.out.println("Address: " + storeAddress + "\n");
        
        String pizzaType = selectPizza();
        ArrayList<String> sides = selectSides();
        ArrayList<String> drinks = selectDrinks();
        
        generateOrderID();
        calculateTotal(pizzaType, sides, drinks);
        
        confirmOrder(pizzaType, sides, drinks);
    }

    private String selectPizza() {
        System.out.println("\n== Pizza Selection ==");
        for(String item : pizzaMenu) {
            System.out.println(item);
        }
        
        while(true) {
            System.out.print("\nSelect a pizza (1-3): ");
            int choice = scanner.nextInt();
            if(choice >=1 && choice <=3) {
                return pizzaMenu[choice-1].split("\\.")[1].trim();
            }
            System.out.println("Invalid selection!");
        }
    }

    private ArrayList<String> selectSides() {
        ArrayList<String> selected = new ArrayList<>();
        System.out.println("\n== Sides Selection (0 to finish) ==");
        for(String item : sidesMenu) {
            System.out.println(item);
        }
        
        while(true) {
            System.out.print("Select a side (1-3): ");
            int choice = scanner.nextInt();
            if(choice == 0) break;
            if(choice >=1 && choice <=3) {
                selected.add(sidesMenu[choice-1].split("\\.")[1].trim());
                continue;
            }
            System.out.println("Invalid selection!");
        }
        return selected;
    }

    private ArrayList<String> selectDrinks() {
        ArrayList<String> selected = new ArrayList<>();
        System.out.println("\n== Drinks Selection (0 to finish) ==");
        for(String item : drinksMenu) {
            System.out.println(item);
        }
        
        while(true) {
            System.out.print("Select a drink (1-3): ");
            int choice = scanner.nextInt();
            if(choice == 0) break;
            if(choice >=1 && choice <=3) {
                selected.add(drinksMenu[choice-1].split("\\.")[1].trim());
                continue;
            }
            System.out.println("Invalid selection!");
        }
        return selected;
    }

    private void generateOrderID() {
        orderID = "ORD-" + System.currentTimeMillis()%10000;
    }

    private void calculateTotal(String pizza, ArrayList<String> sides, ArrayList<String> drinks) {
        double total = 0;
        
        if(pizza.contains("Pepperoni")) total += 12.99;
        else if(pizza.contains("Margherita")) total += 11.99;
        else if(pizza.contains("BBQ Chicken")) total += 13.99;
        
        for(String side : sides) {
            if(side.contains("Garlic Bread")) total += 3.50;
            else if(side.contains("Salad")) total += 4.00;
            else if(side.contains("Fries")) total += 2.50;
        }
        
        for(String drink : drinks) {
            if(drink.contains("Cola")) total += 2.00;
            else if(drink.contains("Lemonade")) total += 1.50;
            else if(drink.contains("Water")) total += 1.00;
        }
        
        orderTotal = total;
    }

    private void confirmOrder(String pizza, ArrayList<String> sides, ArrayList<String> drinks) {
        System.out.println("\n=== Order Summary ===");
        System.out.println("Pizza: " + pizza);
        System.out.println("Sides: " + String.join(", ", sides));
        System.out.println("Drinks: " + String.join(", ", drinks));
        System.out.printf("Total: $%.2f%n", orderTotal);
        
        System.out.print("\nConfirm order? (Y/N): ");
        if(scanner.next().equalsIgnoreCase("Y")) {
            makePizza(pizza);
            printReceipt();
        } else {
            System.out.println("Order cancelled.");
        }
    }

    private void makePizza(String pizzaType) {
        System.out.println("\nMaking " + pizzaType + "...");
        try {
            Thread.sleep(3000);
            System.out.println("Pizza is ready!");
        } catch (InterruptedException e) {
            System.out.println("Preparation interrupted!");
        }
    }

    private void printReceipt() {
        System.out.println("\n===== " + storeName + " Receipt =====");
        System.out.println("Order ID: " + orderID);
        System.out.printf("Total: $%.2f%n", orderTotal);
        System.out.println("Thank you for your order!");
    }
}