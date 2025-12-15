/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.engine;
import store.Model.cart.Cart;
import store.Model.cart.CartItem;
import store.Model.core.Customer;
import store.Model.orders.Order;
import store.Model.products.Product;

import java.util.ArrayList;
import java.util.List;
/**
 * The main engine of the store system.
 * This class manages products, customers and orders.
 */
public class StoreEngine {
    /** List of all products in the store. */
    private List<Product> products;

    /** List of all orders that were created. */
    private List<Order> allOrders;

    /** List of registered customers. */
    private List<Customer> customers;

    /** Counter used for creating new order IDs. */
    private int nextOrderId;
    /**
     * Creates a new store engine with empty lists.
     */
    public StoreEngine() {
        products = new ArrayList<>();
        allOrders = new ArrayList<>();
        customers = new ArrayList<>();
        nextOrderId = 1;
    }
    /**
     * Adds a product to the store.
     *
     * @param p the product to add
     */
    public void addProduct(Product p) {
        products.add(p);
    }
    /**
     * Returns all products in the store.
     *
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return products;
    }
    /**
     * Registers a new customer, unless a customer with the same username exists.
     *
     * @param c the customer to register
     * @return true if registered, false if already exists
     */
    public boolean registerCustomer(Customer c) {
        for(Customer existing: customers) {
            if (existing.equals(c)){
                return false;
            }
        }
        customers.add(c);
        return true;
    }
    /**
     * Creates a new order from the items in a customer's cart.
     *
     * @param cart the cart to create an order from
     * @return the created order
     */
    public Order createOrderFromCart(Cart cart) {
        double total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        int idForThisOrder = nextOrderId;
        nextOrderId++;
        List<CartItem> itemsCopy = new ArrayList<>(cart.getItems());

        Order order = new Order(idForThisOrder, itemsCopy, total);
        allOrders.add(order);
        return order;


    }

}
