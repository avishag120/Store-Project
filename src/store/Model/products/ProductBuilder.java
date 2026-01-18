/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;

import java.awt.*;

public class ProductBuilder implements ProductPlan {
    private String name;
    private double price;
    private int stock;
    private String description;
    private Category category;
    private Color color;
    private String imagePath;

    private String size;
    private int warrantyMonths;
    private String brand;
    private String author;
    private int page;

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean setPrice(double price) {
        if(price>0){
            this.price = price;
            return true;
        }
        return false;
    }
    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPage(int page) {
        this.page = page;
    }

    String getName() { return name; }
    double getPrice() { return price; }
    int getStock() { return stock; }
    String getDescription() { return description; }
    Category getCategory() { return category; }
    Color getColor() { return color; }
    String getImagePath() { return imagePath; }

    String getSize() { return size; }
    int getWarrantyMonths() { return warrantyMonths; }
    String getBrand() { return brand; }
    String getAuthor() { return author; }
    int getPage() { return page; }
}


