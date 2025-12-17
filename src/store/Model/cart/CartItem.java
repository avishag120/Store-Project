/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.cart;
import store.Model.products.Product;
import javax.swing.*;
/**
 * Represents a single item inside the shopping cart.
 * Holds a product and the quantity chosen by the customer.
 */
public class CartItem {
    /** The product inside this cart item. */
    private Product product;
    /** How many units of the product are in the cart. */
    private int quantity;
    /**
     * Creates a new cart item with a product and a quantity.
     *
     * @param product the product
     * @param quantity amount of this product
     */

    public  CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    /**
     * Sets a new quantity for the product.
     *
     * @param q new quantity
     * @return true if updated, false if invalid
     */

    public boolean setQuantity(int q) {
        if(q < 0){ return false;}
        this.quantity = q;
        return true;

    }
    /**
     * Calculates the total price for this item.
     *
     * @return price * quantity
     */
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }
    /**
     * Two CartItems are equal if they have the same product.
     *
     * @param o other object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o){
        if(this== o){return true;}
        if(o == null || this.getClass()!= o.getClass()){return false;}
        CartItem other = (CartItem) o;
        return this.product.equals(other.product);
    }
    /**
     * Returns the product of this cart item.
     *
     * @return the product
     */

    public Product getProduct() {
        return product;
    }

    /**
     * Returns the quantity of this cart item.
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;}

    public void increase() {
        quantity++;
    }

    public void decrease() {
        if (quantity > 1) {
            quantity--;
        }
    }
}
