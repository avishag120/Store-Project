/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;

import java.awt.*;
/**
 * A product that represents an electronic item in the store.
 * Adds brand and warranty information that are specific to electronics.
 */
public class ElectronicsProduct extends Product{
    private int warrantMonths;
    private String brand;
    /**
     * Creates a new electronics product.
     *
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description short description
     * @param category product category
     * @param color product color
     * @param warrantMonths warranty length in months
     * @param brand brand name
     */
    public  ElectronicsProduct(String name, double price, int stock, String description, Category category, Color color,int warrantMonths, String brand) {
        super(name, price, stock, description, category, color);
        this.warrantMonths = warrantMonths;
        this.brand = brand;
    }
    /**
     * Returns the electronics details as text.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nwarrantMonths:" + warrantMonths +
                 "\nbrand:" +brand;
    }
    /**
     * Returns a short display description.
     */
    @Override
    public String getDisplayDetails(){
        return super.getDisplayDetails()+
                ",warrantMonths:" + warrantMonths +
                ",brand:" +brand;
    }
}
