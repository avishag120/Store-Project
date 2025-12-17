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
        setLayout(new GridLayout(0, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    public void setController(StoreController controller) {
        this.controller = controller;
    }
    public void showProductDetails(Product p) {
        detailsPanel.showProduct(p);
    }
    public void showProducts(List<Product> products) {
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




}
