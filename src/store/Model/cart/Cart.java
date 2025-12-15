/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.cart;

import store.Model.products.Product;

import java.util.ArrayList;
import java.util.List;
/**
 * A shopping cart that holds items the customer wants to buy.
 * The cart stores a list of CartItem objects and allows adding,
 * removing and calculating the total price.
 */
public class Cart {

   private List<CartItem> items;
    /**
     * Creates an empty cart.
     */

   public Cart()

   {
       this.items = new ArrayList<>();
   }
    /**
     * Returns the list of cart items.
     *
     * @return list of cart items
     */

   public List<CartItem> getItems() {
       return items;
   }

    /**
     * Finds a cart item by product.
     *
     * @param p the product to search for
     * @return the matching CartItem, or null if not found
     */
    private CartItem findItem(Product p) {
        for (CartItem item : items) {
            if (item.getProduct().equals(p)) {
                return item;
            }
        }
        return null;
    }
    /**
     * Adds a product to the cart.
     *
     * @param p the product to add
     * @param quantity how many units to add
     * @return true if added successfully, false otherwise
     */

    public boolean addItem(Product p, int quantity) {
        if (quantity <= 0) return false;
        if (p.getStock() < quantity) return false;

        CartItem item = findItem(p);

        if (item == null) {
            items.add(new CartItem(p, quantity));
            return true;
        }

        item.setQuantity(item.getQuantity() + quantity);
        return true;
    }
    /**
     * Removes a product from the cart.
     *
     * @param p the product to remove
     * @return true if removed, false otherwise
     */

    public boolean removeItem(Product p) {
       CartItem item = findItem(p);
       if(item == null){return false;}
       items.remove(item);
       return true;
    }
    /**
     * Calculates the total price of all cart items.
     *
     * @return the total price
     */
    public double calculateTotal() {
       double sum = 0;
        for (CartItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }
    /**
     * Removes all items from the cart.
     */
    public void clear() {
        items.clear();
    }


   }
//}
