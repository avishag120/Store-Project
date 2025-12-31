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
    private List<Product> products= new ArrayList<>();
    private boolean loaded=false;

    /** List of all orders that were created. */
    private List<Order> allOrders;

    /** Counter used for creating new order IDs. */
    private int nextOrderId;
    /**
     * Creates a new store engine with empty lists.
     */
    private List<Runnable> listeners = new ArrayList<>();
    private List<java.util.function.Consumer<Product>> productListeners = new ArrayList<>();

    public StoreEngine() {
        products = new ArrayList<>();
        allOrders = new ArrayList<>();
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
    /**
     * Removes all products from the store.
     * Used by the controller before loading products from file.
     */
    public void clearProducts() {
        products.clear();
        loaded=false;
    }
    public boolean isLoaded(){
        return loaded;
    }
    public void setLoaded(boolean loaded){
        this.loaded=loaded;
    }
    public synchronized void addListener(Runnable r) {
        listeners.add(r);
    }

    public synchronized void notifyListeners() {
        for (Runnable r : listeners) {
            r.run();
        }
    }

    public synchronized void addProductListener(java.util.function.Consumer<Product> l) {
        productListeners.add(l);
    }

    public synchronized void notifyProductListeners(Product changedProduct) {
        for (var l : productListeners) {
            l.accept(changedProduct);
        }
    }


}
