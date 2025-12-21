/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.orders;

import store.Model.cart.CartItem;
import store.Model.core.Persistable;
import store.Model.products.Product;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Order  implements Persistable {
    private int orderID;
    private List<CartItem> items;
    private double totalAmount;
    private LocalDateTime createdAt;
    private String itemsText;



    /** Current status of the order (NEW, PAID, SHIPPED, DELIVERED). */

    private OrderStatus status;
    /**
     * Creates a new order with an ID, list of items and total price.
     *
     * @param orderID the order ID
     * @param items list of items
     * @param totalAmount total price of the order
     */
   public Order(int orderID, List<CartItem> items,double totalAmount) {
       this.orderID = orderID;
       this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
       this.createdAt = LocalDateTime.now();

   }
    public Order(int orderID, String itemsText, double totalAmount) {
        this.orderID = orderID;
        this.itemsText = itemsText;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
        this.createdAt = LocalDateTime.now();
    }
    /**
     * Saves the order to a file.
     * (Empty for now, will be used later.)
     */
    @Override
    public void saveToFile(String path) {}
    /**
     * Moves the order to PAID state.
     *
     * @return true if updated
     */
    public boolean pay(){
       if(status == OrderStatus.NEW){
           this.status = OrderStatus.PAID;
           return true;
       }
       return false;

    }
    /**
     * Moves the order to SHIPPED state.
     *
     * @return true if updated
     */
    public boolean ship(){
       if(pay()){
           this.status = OrderStatus.SHIPPED;
           return true;
       }
       return false;
    }
    /**
     * Moves the order to DELIVERED state.
     *
     * @return true if updated
     */
    public boolean deliver(){
       if(ship()){
           this.status = OrderStatus.DELIVERED;
           return true;
       }
       return false;
    }
    /**
     * Two orders are equal if they have the same ID.
     *
     * @param o other object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || this.getClass() != o.getClass()) return false;
       Order order = (Order) o;
       return this.orderID == order.orderID;
    }
    /**
     * Returns the order as a readable text.
     *
     * @return order info
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Status: ").append(status).append("\n");
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        sb.append("Created At: ")
                .append(createdAt.format(formatter))
                .append("\n");

        sb.append("Total Amount: ")
                .append(String.format("%.2f", totalAmount))
                .append("\n");
        sb.append("Items:\n");

        for (CartItem item : items) {
            sb.append("   - ")
                    .append(item.getProduct().getDisplayName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append("\n");
        }

        return sb.toString();
    }
    public int getOrderID() {
        return orderID;
    }
    public List<CartItem> getItems() {
        return items;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public String getItemsText() {
        return itemsText;
    }
    public void setItemsText(String itemsText) {
        this.itemsText = itemsText;
    }








}
