package store.gui.view;

import store.Model.products.Category;
import store.Model.products.Product;
import store.gui.controler.StoreController;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StoreWindow extends JPanel {
    private ProductDetailsPanel detailsPanel;
    private StoreController controller;


    public StoreWindow(ProductDetailsPanel detailsPanel) {
        this.detailsPanel = detailsPanel;
        setLayout(new GridLayout(2,6,10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    public void setController(StoreController controller) {
        this.controller = controller;
    }
    // מציג תוצאת חיפוש דוגמא
    public void showSearchResult(List<Product> products) {
        removeAll();
        for (Product p : products) {
            JButton btn = new JButton(p.getDisplayName());

            btn.addActionListener(e ->
                    controller.productSelected(p)
            );

            add(btn);
        }

        revalidate();
        repaint();
    }

    public void showSaveMessage(String path) {
        removeAll();
        add(new JLabel("Saved to file:" +path));
        revalidate();
        repaint();
    }

    public void showProductDetails(Product p) {
        detailsPanel.showProduct(p);
    }
    public void showAllProducts(List<Product> products) {
        removeAll();

        for (Product p : products) {
            JButton btn = new JButton(p.getDisplayName());
            btn.addActionListener(e ->
                    controller.productSelected(p)
            );
            add(btn);
        }

        revalidate();
        repaint();
    }
    public void showLoadMessage(String path) {
        removeAll();
        add(new JLabel("Loaded from file: " + path));
        revalidate();
        repaint();
    }



}
