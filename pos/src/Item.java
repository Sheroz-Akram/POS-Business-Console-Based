/**
 * Represents an item with a unique ID, name, quantity, and price.
 */
public class Item implements ItemInterface {

    private int id; // The Id of the item
    private String name; // name of the item
    private int quantity; // the number of availible stocks
    private double price; // the price of the item

    /**
     * Constructs an Item object with the specified ID, name, quantity, and price.
     *
     * @param id       the unique ID of the item (must be positive)
     * @param name     the name of the item (must not be null or empty)
     * @param quantity the quantity of the item (must be non-negative)
     * @param price    the price of the item (must be non-negative)
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Item(int id, String name, int quantity, double price) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the ID of the item.
     *
     * @return the ID of the item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the item.
     *
     * @param id the new ID of the item (must be positive)
     * @throws IllegalArgumentException if the ID is invalid
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new name of the item (must not be null or empty)
     * @throws IllegalArgumentException if the name is invalid
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        this.name = name;
    }

    /**
     * Returns the quantity of the item.
     *
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity the new quantity of the item (must be non-negative)
     * @throws IllegalArgumentException if the quantity is invalid
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        this.quantity = quantity;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price the new price of the item (must be non-negative)
     * @throws IllegalArgumentException if the price is invalid
     */
    public void setPrice(double price) {
        if (price < 0) {

            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = price;
    }

    /**
     * Returns a string representation of the item in the format "name (quantity) - $price".
     *
     * @return a string representation of the item
     */
    @Override
    public String toString() {
        return String.format("%s (%d) - $%.2f", name, quantity, price);
    }

    /**
     * Returns a double with the Total Price (Quantity * Unit Price)
     *
     * @return an double with the Total Price (Quantity * Unit Price)
     */
    public double getTotal() {
        return quantity * price;
    }
}