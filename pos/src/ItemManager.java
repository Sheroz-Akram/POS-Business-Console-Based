import java.util.ArrayList;
import java.util.Scanner;

public class ItemManager {
    // Display Items Menu
    public static int itemMenu() {
        int option = 0;
        while (true) {
            System.out.println("1. Add a New Item");
            System.out.println("2. Remove an Item");
            System.out.println("3. Update an Item");
            System.out.println("4. Display All Items");
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
    public static int itemSearchOptionMenu() {
        int option = 0;
        while (true) {
            System.out.println("1. Search By ID");
            System.out.println("2. Search By Name");
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

    // search an item by ID
    public static ArrayList<Item> searchItemByID(int id, ArrayList<Item> itemList) {
        ArrayList<Item> returnList = new ArrayList<Item>();
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId() == id) {
                returnList.add(itemList.get(i));
            }
        }
        return returnList;
    }

    // search an item by Name
    public static ArrayList<Item> searchItemByName(String Name, ArrayList<Item> itemList) {
        ArrayList<Item> returnList = new ArrayList<Item>();
        for (int i = 0; i < itemList.size(); i++) {
            if ((itemList.get(i).getName().toLowerCase()).contains(Name.toLowerCase())) {
                returnList.add(itemList.get(i));
            }
        }
        return returnList;
    }

    // Display all the Items
    public static void itemsDisplay(ArrayList<Item> itemList) {
        System.out.println();
        System.out.println("SR: name (quantity) - $price");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ": " + itemList.get(i).toString());
        }
        System.out.println();
    }

    // Search an Item
    public static int itemSearch(ArrayList<Item> itemList) {

        // But First Search an Item
        int searchOption = itemSearchOptionMenu();
        ArrayList<Item> resultList = new ArrayList<Item>();
        Scanner in = new Scanner(System.in);

        // Perform Operation
        if (searchOption == 1) {
            System.out.print("Enter Item ID to Search: ");
            int searchID = in.nextInt();
            resultList = searchItemByID(searchID, itemList);
        } else {
            System.out.print("Enter Item Name to Search: ");
            String searchName = in.nextLine();
            resultList = searchItemByName(searchName, itemList);
        }

        // Check if Item Found or Not
        // Display All Items
        if (resultList.size() == 0) {
            return -1;
        }

        // Items Found Now Display them
        else {
            itemsDisplay(resultList);
            System.out.print("Select Item: ");
            int selectedItem = in.nextInt();
            if (selectedItem < resultList.size() + 1 && selectedItem > 0) {
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.get(i).getId() == resultList.get(selectedItem - 1).getId()) {
                        return i;
                    }
                }
            } else {
                System.out.println("Invalid Item Selected!");
            }
        }
        return -1;
    }
}
