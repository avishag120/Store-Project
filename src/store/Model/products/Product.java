/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakies – ID 325684678
 */
package store.Model.products;
import store.Model.core.*;
import java.awt.Color;
/**
 * Base class for all products in the store.
 * Contains common fields such as name, price, stock and description.
 * Other product types extend this class.
 */
public abstract class Product implements Persistable,StockManageable,PricedItem,StoreEntity{
    private String name;
    private double price;
    private int stock;
    private String description;
    private Category category;
    private Color color;
    private String imagePath;


    public  Product(String name, double price, int stock, String description, Category category, Color color,String imagePath) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.color = color;
        this.imagePath=imagePath;
    }
    /**
     * Returns the product price.
     */
    @Override
    public double getPrice() {
        return this.price;
    }
    /**
     * Sets a new price if it is positive.
     *
     * @param price new price
     * @return true if the price was updated
     */
    @Override
    public boolean setPrice(double price) {
        if(price>0){
            this.price = price;
            return true;
        }
        return false;
    }
    /**
     * Returns how many items are in stock.
     */
    @Override
    public int getStock() {
        return this.stock;
    }// StockManagea
    /**
     * Adds items to stock.
     *
     * @return true if amount was valid
     */
    @Override
    public boolean increaseStock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            return true;
        }
        return false;
    }// StockManagea
    /**
     * Removes items from stock, if possible.
     *
     * @return true if stock was updated
     */
    @Override
    public boolean decreaseStock(int amount) {
        if (amount > 0 && this.stock - amount >= 0) {
            this.stock -= amount;
            return true;
        }
        return false;
    }// StockManagea
    /**
     * Returns the product name for display.
     */
    @Override
    public String getDisplayName(){
        return name;
    }
    /**
     * Returns product info in a simple display format.
     */
    @Override
    public String getDisplayDetails(){
        return   "Price: " + price +
                ", Stock: " + stock +
                ", Description: " + description +
                ", Category: " + category +
                ", Color: " + color;
    }
    /**
     * Empty for now. Will be used later in the course.
     */
    @Override
    public void saveToFile(String path) {

    }
    /**
     * Returns product details as text.
     */
    @Override
    public String toString(){
        return "<html>"
                + "<b>Name:</b> " + name + "<br>"
                + "<b>Price:</b> " + price + " ₪<br>"
                + "<b>Stock:</b> " + stock + "<br>"
                + "<b>Description:</b> " + description + "<br>"
                + "<b>Category:</b> " + category
                + "</html>";
    }
    /**
     * Checks if two products are the same.
     * Products are equal if they have the same name and category.
     */
        @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return this.name.equals(other.name) &&
                this.category == other.category;
    }
    public String getDescription(){
            return description;
    }
    public String getImagePath(){
            return imagePath;
    }
    public Category getCategory() {
        return category;
    }





}
