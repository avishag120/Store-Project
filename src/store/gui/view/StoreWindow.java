package store.gui.view;

import store.Model.products.Category;
import store.Model.products.Product;

import javax.swing.*;
import java.awt.*;

public class StoreWindow extends JPanel {
    private ProductDetailsPanel detailsPanel;


    public StoreWindow(ProductDetailsPanel detailsPanel) {
        this.detailsPanel = detailsPanel;
        setLayout(new GridLayout(2,6,10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        showAllProducts();

    }
    public void showAllProducts() {
        removeAll();

        JButton productButton = new JButton("Product 1");

        productButton.addActionListener(e -> {
            JFrame detailsFrame = new JFrame("Product Details");
            detailsFrame.add(new ProductDetailsPanel());
            detailsFrame.setSize(300, 200);
            detailsFrame.setVisible(true);
        });

        add(productButton);

        revalidate();
        repaint();
    }


    // מציג תוצאת חיפוש דוגמא
    public void showSearchResult(String text) {
        removeAll();
        add(new JLabel("Result: " + text));
        revalidate();
        repaint();
    }
    public void showLoadMessage(String path) {
        removeAll();
        add(new JLabel("Loaded from file:"));
        add(new JLabel(path));
        revalidate();
        repaint();
    }

    public void showSaveMessage(String path) {
        removeAll();
        add(new JLabel("Saved to file:"));
        add(new JLabel(path));
        revalidate();
        repaint();
    }
    private void openProductDetails() {
        JFrame detailsFrame = new JFrame("Product Details");
        ProductDetailsPanel panel = new ProductDetailsPanel();

        // מוצר זמני – רק כדי שה־GUI יעבוד
        Product product = new Product(
                "Product 1",
                100,
                20,
                "Nice product",
                Category.CLOTHING,
                Color.BLUE
        ) {};

        panel.showProduct(product);

        detailsFrame.add(panel);
        detailsFrame.pack();
        detailsFrame.setLocationRelativeTo(this);
        detailsFrame.setVisible(true);
    }
    public void showProductDetails(Product p) {
        detailsPanel.showProduct(p);
    }




}
