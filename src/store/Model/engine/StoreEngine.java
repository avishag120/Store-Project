/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.engine;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import store.Model.cart.Cart;
import store.Model.cart.CartItem;
import store.Model.orders.Order;
import store.Model.products.*;
import store.Model.discount.DiscountStrategy;
import store.Model.discount.NoDiscount;


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
    private DiscountStrategy discountStrategy = new NoDiscount();
    static private StoreEngine instance=null;

    private StoreEngine() {
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
        notifyListeners();
    }
    /**
     * Returns all products in the store.
     *
     * @return list of products
     */
    public synchronized List<Product> getAllProducts() {
        return new ArrayList<>(products);
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
    public boolean isLoaded(){
        return loaded;
    }
    /**
     * Sets the loaded state of the product list.
     *
     * This flag is used to prevent loading products multiple times
     * from a file during runtime.
     *
     * @param loaded true if products were loaded successfully
     */
    public void setLoaded(boolean loaded){
        this.loaded=loaded;
    }
    /**
     * Registers a listener that will be notified
     * when the product list or stock changes.
     *
     * Listeners are typically GUI components that need
     * to refresh their display when the store data changes.
     *
     * @param r a Runnable listener to be executed on updates
     */
    public synchronized void addListener(Runnable r) {
        listeners.add(r);
    }
    /**
     * Notifies all registered listeners about a change
     * in the store state.
     *
     * This method is called after actions such as:
     * - Adding a product
     * - Updating stock
     * - Loading products from file
     */
    public synchronized void notifyListeners() {
        for (Runnable r : listeners) {
            r.run();
        }
    }
    /**
     * Loads products from the default CSV file ("products.csv").
     *
     * The method clears the current product list and reads
     * products line by line from the file.
     *
     * Expected CSV format:
     * name,price,stock,description,category,imagePath
     *
     * If the file does not exist, the method exits silently.
     */
    public static StoreEngine getInstance(){
        if(instance==null)
            synchronized (StoreEngine.class){
                if(instance==null){
                    instance=new StoreEngine();
                }
            }

        return instance;


    }
    public synchronized DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public synchronized void setDiscountStrategy(DiscountStrategy discountStrategy) {
        if (discountStrategy == null) return;
        this.discountStrategy = discountStrategy;
        notifyListeners();
    }



}
