/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.core;
import store.Model.cart.Cart;
import store.Model.orders.Order;
import store.Model.products.Product;
import store.Model.engine.StoreEngine;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a customer in the store system.
 * A customer has a cart, an order history, and a link to the store engine.
 */
public class Customer extends User {
    private Cart cart;
    /** List of all orders made by this customer. */
    private List<Order> orderHistory;
    private StoreEngine engine;
    /**
     * Creates a new customer with username, email, cart and order history.
     *
     * @param username the customer's username
     * @param email the customer's email
     * @param cart the customer's cart
     * @param orderHistory list of previous orders
     * @param engine the store engine used for creating orders
     */
    public Customer(String username, String email, Cart cart , List<Order> orderHistory, StoreEngine engine) {
        super(username,email);
        this.cart = cart;
        this.orderHistory = new ArrayList<>(orderHistory);
        this.engine = engine;
    }
    /**
     * Adds a product to the customer's cart.
     *
     * @param p the product
     * @param quantity how many units to add
     * @return true if added, false otherwise
     */
    public boolean addToCart(Product p, int quantity) {
        if (  quantity <=0 ){return false;}
        if( p.getStock() < quantity ){ return false;}
        return cart.addItem(p,quantity);


    }
    /**
     * Removes a product from the cart.
     *
     * @param p the product to remove
     * @return true if removed, false otherwise
     */
    public boolean removeFromCart(Product p) {
        if ( p.getStock() <= 0 ){return false;}
        return cart.removeItem(p);

    }
    /**
     * Creates an order from the cart and clears the cart afterwards.
     *
     * @return true if the checkout succeeded
     */
    public boolean checkout() {
        Order order = engine.createOrderFromCart(cart);
        if (order == null) return false;
        orderHistory.add(order);
        cart.clear();
        return true;
    }


}
