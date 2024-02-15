import java.util.ArrayList;

public class Order extends DateTime implements OrderInterface {

    private int orderNo; // unique identifier of the order
    private String customerName; // customer name of the order
    private ArrayList<Item> items; // the items of the order
    private double totalPrice;

    /**
     * Constructs that use will use to store the Order Details such as OrderNo,
     * Customer Name, Items that were orderd, Current Date and Time
     *
     * @param orderNo      the unique ID of the Order (must be positive)
     * @param customerName the customerName of the Order (must not be null or empty)
     * @param items        all the items that were ordered (must have atleat on item
     *                     in list)
     * 
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Order(int orderNo, String customerName, ArrayList<Item> items) {
        if (orderNo < 0) {
            throw new IllegalArgumentException("Invalid Order Number!");
        }
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer Name must not be null or empty");
        }
        if (items.size() == 0) {
            throw new IllegalArgumentException("Please Enter atleast one item for order");
        }

        this.orderNo = orderNo;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = calcTotalPrice(items);
    }


    /**
     * Constructs that use will use to store the Order Details such as OrderNo,
     * Customer Name, Items that were orderd, user also puts the Time and Date Details in it
     *
     * @param orderNo      the unique ID of the Order (must be positive)
     * @param customerName the customerName of the Order (must not be null or empty)
     * @param items        all the items that were ordered (must have atleat on item
     *                     in list)
     * @param hour    the current hour (0-23)
     * @param minute  the current minute (0-59)
     * @param day     the current day of the month (1-31)
     * @param month   the current month (1-12)
     * @param year    the current year (e.g., 2023)
     * @param dayName the current day name of the week (e.g., "Tuesday")
     * 
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Order(int orderNo, String customerName, ArrayList<Item> items, int hour, int minute, int day, int month, int year, String dayName) {
        super(hour, minute, day, month, year, dayName);
        
        if (orderNo < 0) {
            throw new IllegalArgumentException("Invalid Order Number!");
        }
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer Name must not be null or empty");
        }
        if (items.size() == 0) {
            throw new IllegalArgumentException("Please Enter atleast one item for order");
        }
        
        this.orderNo = orderNo;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = calcTotalPrice(items);
    }

    /**
     * Returns the order number of the order
     * 
     * @return the order number of the order
     */
    public int getOrderNo() {
        return orderNo;
    }

    /**
     * Sets the order number of the order
     * 
     * @param orderNo the order number of the order
     * @throws IllegalArgumentException if the order number is negative
     */
    public void setOrderNo(int orderNo) {
        if (orderNo < 0) {
            throw new IllegalArgumentException("Invalid Order Number!");
        }
        this.orderNo = orderNo;
    }

    /**
     * Returns the customer name of the order
     * 
     * @return the customer name of the order
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name of the order
     * 
     * @param customerName the customer name of the order
     * @throws IllegalArgumentException if the customer name is null or empty
     */
    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer Name must not be null or empty");
        }
        this.customerName = customerName;
    }

    /**
     * Sets the total price of the order
     * 
     * @param totalprice the total price of the order
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Returns the items of the order
     * 
     * @return the items of the order
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Funtion that Return the Total Price of all the Item in the Order
     * 
     * @return the Total Price Customer have to Pay
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Funtion that Calculate the Total Price of all the Item in the Order
     * 
     * @param items the items of the order
     * @return the Total Price Customer have to Pay
     */
    public Double calcTotalPrice(ArrayList<Item> list) {
        Double totalItemsPrice = 0.0;
        for (int i = 0; i < list.size(); i++) {
            totalItemsPrice += list.get(i).getTotal();
        }
        return totalItemsPrice;
    }

    /**
     * Sets the items of the order
     * 
     * @param items the items of the order
     * @throws IllegalArgumentException if the items list is empty
     */
    public void setItems(ArrayList<Item> items) {
        if (items.size() == 0) {
            throw new IllegalArgumentException("Please Enter atleast one item for order");
        }
        this.items = items;
    }


    /**
     * Returns a string representation of the Order in the format "Customer - Total Price - Time And Date".
     *
     * @return a string representation of the item
     */
    @Override
    public String toString() {
        return String.format("%s - %f - $s", customerName, totalPrice, super.toString());
    }


    /**
     * Display Date and Time
     *
     * @return a string representation of the Date and Time
     */
    public String toDateAndTime() {
        return super.toString();
    }


}