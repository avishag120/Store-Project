/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;

import java.awt.*;
/**
 * A product that represents a clothing item in the store.
 * Adds a size field that is specific to clothing.
 */

public class ClothingProduct extends Product {
    /** The size of the clothing item (S, M, L, etc.). */
    private String size;
    /**
     * Creates a new clothing product.
     *
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description short description
     * @param category product category
     * @param color product color
     * @param size clothing size
     */

    public  ClothingProduct(String name, double price, int stock, String description, Category category, Color color, String size,String imagePath) {
        super(name, price, stock, description, category, color,imagePath);
        this.size = size;

    }

    /**
     * Returns the clothing details as text.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nsize:" + size;
    }
    /**
     * Returns a short display description.
     */

    @Override
    public String getDisplayDetails(){
        return super.getDisplayDetails()+
                ",size:" +size;
    }

}
