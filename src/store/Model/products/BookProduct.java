/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;
import java.awt.*;
/**
 * A product that represents a book in the store.
 * Adds book-specific fields such as author and number of pages.
 */
public class BookProduct extends Product {
    /** The name of the book's author. */
    private String author;
    /** How many pages the book has. */
    private int page;
    /**
     * Creates a new book product.
     *
     * @param name product name
     * @param price product price
     * @param stock how many items are in stock
     * @param description short description of the book
     * @param category product category
     * @param color product color
     * @param author the book's author
     * @param page number of pages in the book
     */

    public BookProduct(String name, double price, int stock, String description, Category category, Color color,String author, int page) {
        super(name, price, stock, description, category, color);
        this.author = author;
        this.page = page;
    }

    /**
     * Returns the book details as text.
     */
   @Override
    public String toString() {
        return super.toString() +   "\nAuthor: " + author +
                "\nPages: " + page;
    }
    /**
     * Returns a short description for display.
     */
    @Override
    public String getDisplayDetails(){
        return super.getDisplayDetails()+  ", Author: " + author +
                ", Pages: " + page;
    }



}
