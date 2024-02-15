import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // This Array List will store all our Program Data
    static private ArrayList<Order> orderList;
    static private ArrayList<Item> itemList;

    // Display the Main Menu
    public static int mainMenu() {
        int option = 0;
        while (true) {
            System.out.println("1. Display Orders Menu");
            System.out.println("2. Display Items Menu");
            System.out.println("3. Save and Close");
            System.out.print("Select Option: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();

            if (option < 4 && option > 0) {
                break;
            }
            System.out.println("\nInvalid Option! Try Again.\n");
        }
        return option;
    }

    // Our Program Will Start Here
    public static void main(String[] args) {

        // First we Try to Get All the Files Data
        try {

            // Get all The Data from System Files
            itemList = FileHandler.readItemsDataFromCSV("Items.csv");
            orderList = FileHandler.readOrdersDataFromCSV("Orders.csv");

        }
        // A Exception might Occur Due to File Not Present At First
        catch (Exception e) {

            // So Inilize the Lists with Zero Data as No Files are present
            itemList = new ArrayList<Item>();
            orderList = new ArrayList<Order>();
        }

        // Our Main Program Loop
        while (true) {

            // Ask the User to Select Option from Main Menu
            System.out.println();
            System.out.println("*** MAIN MENU ***");
            int mainOption = mainMenu();

            // Do Operation According to the Main Option Selected
            if (mainOption == 1) {

                // The Orders Menu is Selected
                while (true) {
                    System.out.println();
                    System.out.println("*** ORDER MENU ***");
                    int subMenuOption = OrderManager.orderMenu();

                    // Add A New Order
                    if (subMenuOption == 1) {

                        System.out.println();
                        System.out.println("*** NEW ORDER ***");

                        // Get The Basic Details
                        Scanner in = new Scanner(System.in);
                        int orderNum = orderList.size() + 1;
                        System.out.print("Enter Customer Name: ");
                        String customerName = in.nextLine();
                        ArrayList<Item> userItems = new ArrayList<Item>();

                        // Add Items to the Customer
                        System.out.println("\nAdd Items to Order: \n");
                        int addItem = 1;
                        while (addItem == 1) {

                            // Search an Item
                            int itemID = ItemManager.itemSearch(itemList);

                            // Add Item to the User Cart
                            if (itemID >= 0) {
                                System.out.print("Enter Item Quantity: ");
                                int itemQuantity = in.nextInt();

                                // Check IF Even Stock is Availible
                                if (itemList.get(itemID).getQuantity() - itemQuantity < 0) {
                                    System.out.println("No Enough Quantity!\n");
                                    continue;
                                }

                                // If Stock is Availible Store the Item
                                Item newItem = new Item(itemList.get(itemID).getId(), itemList.get(itemID).getName(),
                                        itemQuantity,
                                        itemList.get(itemID).getPrice());
                                itemList.get(itemID).setQuantity(itemList.get(itemID).getQuantity() - itemQuantity);
                                userItems.add(newItem);
                            } else {
                                System.out.println("Item Not Found!\n");
                                continue;
                            }

                            // Ask If We Want to Enter Item
                            System.out.print("Do You Want to Add an Other Item: ");
                            addItem = in.nextInt();
                            System.out.println();

                        }

                        System.out.println();
                        System.out.println("*** ORDER DETAILS ***");

                        // Display the Order Details
                        Order newOrder = new Order(orderNum, customerName, userItems);
                        OrderManager.displaySingleOrder(newOrder);

                        // Store the Order
                        orderList.add(newOrder);

                        System.out.println("Press Enter key to continue...");
                        Scanner s = new Scanner(System.in);
                        s.nextLine();
                        System.out.println();

                    }

                    // Remove an Order fromt the Order List
                    else if (subMenuOption == 2) {
                        System.out.println();
                        System.out.println("*** REMOVE ORDER ***");

                        // Search the Order
                        int orderNum = OrderManager.orderSearch(orderList);

                        // Remove the Order if Exists
                        if (orderNum >= 0) {
                            orderList.remove(orderNum);
                        } else {
                            System.out.println("Order Does Not Found!");
                        }
                        System.out.println();
                    }

                    // Detail View an Order
                    else if (subMenuOption == 3) {
                        System.out.println();
                        System.out.println("*** DETAIL ORDER VIEW ***");

                        // Search the Order
                        int orderNum = OrderManager.orderSearch(orderList);

                        // Display the Order if Exists
                        if (orderNum >= 0) {
                            System.out.println();
                            System.out.println("*** ORDER DETAIL ***");
                            OrderManager.displaySingleOrder(orderList.get(orderNum));
                        } else {
                            System.out.println("Order Does Not Found!");
                        }
                        System.out.println();
                    }

                    // Display Last 10 Orders
                    else if (subMenuOption == 4) {
                        System.out.println();
                        System.out.println("*** LAST 10 ORDERS ***");
                        int j = 10;
                        System.out.println();

                        // Start from the Last Index and display last 10 orders
                        System.out.println("SR: Customer - Total Price - Time");
                        for (int i = orderList.size() - 1; i >= 0 && j > 0; i--) {
                            System.out.println((j - 9) + ": " + orderList.get(i).getCustomerName() + " - "
                                    + orderList.get(i).getTotalPrice() + " - " + orderList.get(i).toDateAndTime());
                        }
                        System.out.println();
                    }

                    // Break the Menu
                    else if (subMenuOption == 5) {
                        break;
                    }

                    // The Save State of the Program
                    try {
                        FileHandler.writeItemsDataToCSV(itemList, "Items.csv");
                        FileHandler.writeOrdersDataToCSV(orderList, "Orders.csv");
                    } catch (Exception e) {
                        System.out.println("Something Went Wrong Reading the Files!");
                    }
                }
            }

            // The Item Menu it Selected
            else if (mainOption == 2) {

                while (true) {

                    // User Select A Option from Item Menu
                    System.out.println();
                    System.out.println("*** ITEM MENU ***");
                    int subMenuOption = ItemManager.itemMenu();

                    // Add a New Item to the List
                    if (subMenuOption == 1) {

                        System.out.println();
                        System.out.println("*** ADD NEW ITEM ***");
                        System.out.println();

                        // Get all the Item Details Needed
                        int ItemId = 0;
                        if (itemList.size() == 0) {
                            ItemId = 1;
                        } else {
                            ItemId = itemList.get(itemList.size() - 1).getId() + 1;
                        }
                        Scanner in = new Scanner(System.in);
                        System.out.print("Enter Item Name: ");
                        String ItemName = in.nextLine();
                        System.out.print("Enter Item Quantity: ");
                        int Quantity = in.nextInt();
                        System.out.print("Enter Item Price: ");
                        double itemPrice = in.nextDouble();
                        System.out.print("New Item is Added");
                        System.out.println();

                        // Store the New Item Data
                        Item newItem = new Item(ItemId, ItemName, Quantity, itemPrice);
                        itemList.add(newItem);

                    }
                    // Remove a Item from list
                    else if (subMenuOption == 2) {

                        System.out.println();
                        System.out.println("*** REMOVE ITEM ***");
                        System.out.println();

                        // Remove a Item

                        // Search a Item at First
                        int itemId = ItemManager.itemSearch(itemList);
                        if (itemId >= 0) {
                            itemList.remove(itemId);
                            System.out.println("Item is Removed!");
                        } else {
                            System.out.println("Item not Found!");
                        }
                        System.out.println();

                    }

                    // Update an Item
                    else if (subMenuOption == 3) {
                        System.out.println();
                        System.out.println("*** Update ITEM ***");
                        System.out.println();

                        // Update an Item
                        int itemId = ItemManager.itemSearch(itemList);
                        if (itemId >= 0) {

                            // Get all the Item Details Needed
                            Scanner in = new Scanner(System.in);
                            System.out.print("Enter Item Name: ");
                            String ItemName = in.nextLine();
                            System.out.print("Enter Item Quantity: ");
                            int Quantity = in.nextInt();
                            System.out.print("Enter Item Price: ");
                            double itemPrice = in.nextDouble();
                            System.out.print("New Item is Added");

                            // Set the New Item Details
                            itemList.get(itemId).setName(ItemName);
                            itemList.get(itemId).setPrice(itemPrice);
                            itemList.get(itemId).setQuantity(Quantity);

                        } else {
                            System.out.println("Item not Found!");
                        }
                        System.out.println();

                    }

                    else if (subMenuOption == 4) {
                        System.out.println();
                        System.out.println("*** DISPLAY ITEM ***");
                        System.out.println();
                        if (itemList.size() == 0) {
                            System.out.println("No Items to Display!");
                        } else {
                            ItemManager.itemsDisplay(itemList);
                        }
                        System.out.println();
                    }

                    else if (subMenuOption == 5) {
                        System.out.println();
                        break;
                    }

                    // The Save State of the Program
                    try {
                        FileHandler.writeItemsDataToCSV(itemList, "Items.csv");
                        FileHandler.writeOrdersDataToCSV(orderList, "Orders.csv");
                    } catch (Exception e) {
                        System.out.println("Something Went Wrong Reading the Files!");
                    }
                }
            }

            // Save and Exit from the Program
            else if (mainOption == 3) {
                // Break the Loop
                break;
            } else {
                System.out.println("\nInvalid Option is Selected!\n");
            }
        }

        // The Save State of the Program
        try {
            FileHandler.writeItemsDataToCSV(itemList, "Items.csv");
            FileHandler.writeOrdersDataToCSV(orderList, "Orders.csv");
        } catch (Exception e) {
            System.out.println("Something Went Wrong Reading the Files!");
        }

    }
}