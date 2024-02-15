import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * The FileHandler class provides utility methods to write data to CSV files and
 * clear file data
 * 
 */
public class FileHandler {

    /**
     * Writes a list of Item objects to a CSV file with the specified file name.
     * If the file already exists, all the previous data will be deleted.
     * 
     * @param items    - the list of Item objects to write to the file
     * @param fileName - the name of the file to write the data to
     * @throws IOException - if there was an error writing to the file
     */

    public static void writeItemsDataToCSV(ArrayList<Item> items, String fileName) throws IOException {

        // First we Clear all the Data in the File
        clearFileData(fileName);

        // Write the new data to the file
        FileWriter writer = new FileWriter(fileName, true);

        // Write the header row
        writer.write("id,name,quantity,price,\n");

        // Write the data rows
        for (Item item : items) {
            writer.write(item.getId() + "," + item.getName() + "," + item.getQuantity() + "," + item.getPrice() + "\n");
        }

        writer.close();
    }

    /**
     * Clears the contents of a file with the specified file name.
     * 
     * @param fileName - the name of the file to clear
     * @throws IOException - if there was an error writing to the file
     */

    public static void clearFileData(String fileName) throws IOException {

        // Clear any previous data in the file
        FileWriter clearWriter = new FileWriter(fileName, false);
        clearWriter.write("");
        clearWriter.close();

    }

    /**
     * Reads the contents of a CSV file with the specified file name and returns an
     * ArrayList of Item objects.
     * 
     * @param fileName - the name of the file to read from
     * @return an ArrayList of Item objects read from the file
     * @throws IOException - if there was an error reading from the file
     */
    public static ArrayList<Item> readItemsDataFromCSV(String fileName) throws IOException {

        ArrayList<Item> items = new ArrayList<Item>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine(); // read the header row

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int quantity = Integer.parseInt(fields[2]);
                double price = Double.parseDouble(fields[3]);
                Item item = new Item(id, name, quantity, price);
                items.add(item);
            }
        }

        return items;
    }

    /**
     * Writes a list of Ordered Item objects to a CSV file with the specified file
     * name.
     * If the file already exists, previous data will be removed
     * 
     * @param items    - the list of Ordered Items objects to write to the file
     * @param fileName - the name of the file to write the data to
     * @throws IOException - if there was an error writing to the file
     */

    public static void writeOrderedItemsDataToCSV(int orderNo, ArrayList<Item> items, String fileName)
            throws IOException {

        // Write the new data to the file
        FileWriter writer = new FileWriter(fileName, true);

        // Write the data rows
        for (Item item : items) {
            writer.write(orderNo + "," + item.getId() + "," + item.getName() + "," + item.getQuantity() + ","
                    + item.getPrice() + "\n");
        }

        writer.close();
    }

    /**
     * Reads the contents of a CSV file with the specified order number and return
     * the data of that order num in a list
     * 
     * @param orderNo  - the order number of which items we want to get from file
     * @param fileName - the name of the file to read from
     * 
     * @return an ArrayList of Item objects read from the file
     * @throws IOException - if there was an error reading from the file
     */
    public static ArrayList<Item> readOrderNoItemsDataFromCSV(int orderNo, String fileName) throws IOException {

        ArrayList<Item> items = new ArrayList<Item>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = "";

            while ((line = reader.readLine()) != null) {

                // Grab All the Data of Each Item
                String[] fields = line.split(",");
                int checkOrderNum = Integer.parseInt(fields[0]);

                // If We Find the Item of a Specific Order Number
                // Then we Store It
                if (checkOrderNum == orderNo) {
                    
                    int id = Integer.parseInt(fields[1]);
                    String name = fields[2];
                    int quantity = Integer.parseInt(fields[3]);
                    double price = Double.parseDouble(fields[4]);
                    Item item = new Item(id, name, quantity, price);
                    items.add(item);
                }
            }
        }

        return items;
    }

    /**
     * Writes a list of Orders objects to a CSV file with the specified file name.
     * If the file already exists, previous data will be removed
     * 
     * @param orders   - the list of Orders that we want to save in the specific
     *                 file
     * @param fileName - the name of the file to write the data to
     * 
     * @throws IOException - if there was an error writing to the file
     */

    public static void writeOrdersDataToCSV(ArrayList<Order> orders, String fileName)
            throws IOException {

        // Clear the Previous File Data
        clearFileData(fileName);

        // Write the new data to the file
        FileWriter writer = new FileWriter(fileName, true);

        clearFileData("OrderItems.csv");

        // Write the header row
        writer.write("orderno,customer,price,minute,hour,date,month,year,day,\n");

        // Write the data rows
        for (Order order : orders) {
            writer.write(order.getOrderNo() + "," + order.getCustomerName() + "," + order.getTotalPrice() + ","
                    + order.getMinute() +
                    "," + order.getHour() + "," + order.getDay() + "," + order.getMonth() + "," + order.getYear() + ","
                    + order.getDayName() + "\n");
            // Now we Save All the Items Data On to a Specific File
            writeOrderedItemsDataToCSV(order.getOrderNo(), order.getItems(), "OrderItems.csv");
        }

        writer.close();
    }

    /**
     * Reads the contents of a CSV file with the specified file name
     * 
     * @param fileName - the name of the file to read from
     * 
     * @return an ArrayList of Order objects read from the file
     * @throws IOException - if there was an error reading from the file
     */
    public static ArrayList<Order> readOrdersDataFromCSV(String fileName) throws IOException {

        ArrayList<Order> orders = new ArrayList<Order>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine(); // read the header row

            while ((line = reader.readLine()) != null) {

                // Grab All the Data of Each Item
                String[] fields = line.split(",");

                // Get the Order Data
                int orderNo = Integer.parseInt(fields[0]);
                String customerName = fields[1];
                double totalPrice = Double.parseDouble(fields[2]);

                // Now We Get the Items Details
                int minute = Integer.parseInt(fields[3]);
                int hour = Integer.parseInt(fields[4]);
                int day = Integer.parseInt(fields[5]);
                int month = Integer.parseInt(fields[6]);
                int year = Integer.parseInt(fields[7]);
                String dayName = fields[8];

                // Now We Get All the Order Items Detail
                ArrayList<Item> orderList = readOrderNoItemsDataFromCSV(orderNo, "OrderItems.csv");

                // Save This Data to an Order List
                Order order = new Order(orderNo, customerName, orderList , hour, minute, day, month, year, dayName);
                order.setTotalPrice(totalPrice);

                orders.add(order);
            }
        }

        return orders;
    }
}
