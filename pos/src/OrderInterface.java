import java.util.ArrayList;

public interface OrderInterface {
    public int getOrderNo();
    public void setOrderNo(int orderNo);
    public String getCustomerName();
    public void setCustomerName(String customerName);
    public ArrayList<Item> getItems();
    public Double getTotalPrice();
    public void setTotalPrice(Double totalPrice);
    public Double calcTotalPrice(ArrayList<Item> list);
}
