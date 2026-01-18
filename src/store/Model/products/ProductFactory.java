/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;

import java.awt.*;

public class ProductFactory {
    public  Product createProduct(String type, ProductBuilder b) {
        switch (type.toLowerCase()) {

            case "clothing":
                return new ClothingProduct(
                        b.getName(),
                        b.getPrice(),
                        b.getStock(),
                        b.getDescription(),
                        b.getCategory(),
                        b.getColor(),
                        b.getSize(),
                        b.getImagePath()
                );

            case "electronics":
                return new ElectronicsProduct(
                        b.getName(),
                        b.getPrice(),
                        b.getStock(),
                        b.getDescription(),
                        b.getCategory(),
                        b.getColor(),
                        b.getWarrantyMonths(),
                        b.getBrand(),
                        b.getImagePath()
                );

            case "book":
                return new BookProduct(
                        b.getName(),
                        b.getPrice(),
                        b.getStock(),
                        b.getDescription(),
                        b.getCategory(),
                        b.getColor(),
                        b.getAuthor(),
                        b.getPage(),
                        b.getImagePath()
                );

            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }

    }
}
