import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager {
    // Order Menu
    public static int orderMenu() {
        int option = 0;
        while (true) {
            System.out.println("1. Add a New Order");
            System.out.println("2. Remove an Order");
            System.out.println("3. Detail View an Order");
            System.out.println("4. Display Last 10 Orders");
            System.out.println("5. Go Back");
            System.out.print("Select Option: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();

            if (option < 6 && option > 0) {
                break;
            }
            System.out.println("\nInvalid Option! Try Again.\n");
        }
        return option;
    }


    // Give a Search Option
    public static int orderSearchOptionMenu() {
        int option = 0;
        while (true) {
            System.out.println("1. Search By ID");
            System.out.println("2. Search By Customer Name");
            System.out.print("Select Option: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();

            if (option < 3 && option > 0) {
                break;
            }
            System.out.println("\nInvalid Option! Try Again.\n");
        }
        return option;
    }

    // search an order by ID
    public static ArrayList<Order> searchOrderByID(int orderNum, ArrayList<Order> orderList) {
        ArrayList<Order> returnList = new ArrayList<Order>();
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNo() == orderNum) {
                returnList.add(orderList.get(i));
            }
        }
        return returnList;
    }

    // search an order by Name
    public static ArrayList<Order> searchOrderByName(String customerName, ArrayList<Order> orderList) {
        ArrayList<Order> returnList = new ArrayList<Order>();
        for (int i = 0; i < orderList.size(); i++) {
            if ((orderList.get(i).getCustomerName().toLowerCase()).contains(customerName.toLowerCase())) {
                returnList.add(orderList.get(i));
            }
        }
        return returnList;
    }

    // Display all the Orders
    public static void ordersDisplay(ArrayList<Order> orderList) {
        System.out.println();
        System.out.println("SR: Customer - Total Price - Time");
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println((i + 1) + ": " + orderList.get(i).toString());
        }
        System.out.println();
    }

    // Search an order
    public static int orderSearch(ArrayList<Order> orderList) {

        // But First Search an Item
        int searchOption = orderSearchOptionMenu();
        ArrayList<Order> resultList = new ArrayList<Order>();
        Scanner in = new Scanner(System.in);

        // Perform Operation
        if (searchOption == 1) {
            System.out.print("Enter Order No to Search: ");
            int searchID = in.nextInt();
            resultList = searchOrderByID(searchID, orderList);
        } else {
            System.out.print("Enter Customer Name to Search: ");
            String searchName = in.nextLine();
            resultList = searchOrderByName(searchName, orderList);
        }

        // Check if Item Found or Not
        // Display All Items
        if (resultList.size() == 0) {
            return -1;
        }

        // Items Found Now Display them
        else {
            ordersDisplay(resultList);
            System.out.print("Select Order: ");
            int selectedItem = in.nextInt();
            if (selectedItem < resultList.size() + 1 && selectedItem > 0) {
                for (int i = 0; i < orderList.size(); i++) {
                    if (orderList.get(i).getOrderNo() == resultList.get(selectedItem - 1).getOrderNo()) {
                        return i;
                    }
                }
            } else {
                System.out.println("Invalid Item Selected!");
            }
        }
        return -1;
    }

    // Display an Order in Proper Format
    public static void displaySingleOrder(Order order){
        System.out.println("Order Number: " + order.getOrderNo());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Date And Time: " + order.toDateAndTime());
        System.out.println();
        System.out.println("Name\t\tQty\tPrice\tTotal");
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println(order.getItems().get(i).getName() + "\t\t\t" + order.getItems().get(i).getQuantity() + "\t" + order.getItems().get(i).getPrice() + "\t" + order.getItems().get(i).getTotal());
        }
        System.out.println();
        System.out.println("Total Payable: " + order.getTotalPrice());
        System.out.println();

    }
}
