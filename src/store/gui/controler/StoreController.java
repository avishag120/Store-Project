package store.gui.controler;
import javax.swing.*;
import java.io.File;
import store.Model.products.Product;
import store.gui.view.StoreWindow;
import store.Model.engine.StoreEngine;
import java.util.Scanner;
import store.Model.products.Category;


public class StoreController {
    private StoreWindow storeWindow;
    private StoreEngine engine;

    public StoreController(StoreWindow storeWindow) {

        this.storeWindow = storeWindow;
        this.engine = new StoreEngine();
        }
    public void search(String text) {
        if (text == null || text.isEmpty()) {
            storeWindow.showProducts(engine.getAllProducts());
            return;
        }
        java.util.List<Product> result = new java.util.ArrayList<>();
        for (Product p : engine.getAllProducts()) {
            if (p.getDisplayName().toLowerCase()
                    .contains(text.toLowerCase())) {
                result.add(p);
            }
        }

        storeWindow.showProducts(result);
    }
    public void load(JFrame frame) {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter(
                        "CSV files", "csv"
                )
        );
        int result = chooser.showOpenDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        engine.clearProducts();
        try (Scanner sc = new Scanner(file)) {

            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    continue;
                }
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int stock = Integer.parseInt(parts[2]);
                String description = parts[3];
                Category category = Category.valueOf(parts[4]);
                String imagePath = parts[5];

                Product p = null;

                switch (category) {

                    case CLOTHING:
                        p = new store.Model.products.ClothingProduct(
                                name,
                                price,
                                stock,
                                description,
                                category,
                                java.awt.Color.BLUE,
                                "M",
                                imagePath
                        );
                        break;

                    case BOOKS:
                        p = new store.Model.products.BookProduct(
                                name,
                                price,
                                stock,
                                description,
                                category,
                                java.awt.Color.WHITE,
                                "Unknown Author",
                                0,
                                imagePath
                        );
                        break;

                    case ELECTRONICS:
                        p = new store.Model.products.ElectronicsProduct(
                                name,
                                price,
                                stock,
                                description,
                                category,
                                java.awt.Color.BLACK,
                                12,
                                "Unknown Brand",
                                imagePath
                        );
                        break;
                }

                if (p != null) {
                    engine.addProduct(p);
                }
            }

            // הצגת המוצרים בקטלוג
            storeWindow.showProducts(engine.getAllProducts());

            JOptionPane.showMessageDialog(
                    frame,
                    "Products loaded successfully"
            );

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    frame,
                    e.toString() );
        }
    }
    public void save(JFrame frame) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            JOptionPane.showMessageDialog(
                    frame,
                    "Saved to: " + file.getName()
            );
        }
    }
    public void productSelected(Product product,ImageIcon icon) {
        storeWindow.showProductDetails(product,icon);
    }
    public void filterByCategory(String categoryText) {

        if (categoryText.equals("ALL")) {
            storeWindow.showProducts(engine.getAllProducts());
            return;
        }

        Category category = Category.valueOf(categoryText);

        java.util.List<Product> filtered = new java.util.ArrayList<>();

        for (Product p : engine.getAllProducts()) {
            if (p.getCategory() == category) {
                filtered.add(p);
            }
        }

        storeWindow.showProducts(filtered);
    }

}
