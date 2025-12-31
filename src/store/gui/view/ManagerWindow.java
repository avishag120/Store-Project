package store.gui.view;

import store.gui.controler.StoreController;
import store.Model.products.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ManagerWindow extends JFrame {

    private final StoreController controller;

    public ManagerWindow(StoreController controller) {
        this.controller = controller;

        setTitle("Store - Manager");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton addProductBtn = new JButton("Add Product");
        JButton updateStockBtn = new JButton("Update Stock");
        JButton loadBtn = new JButton("Load Products");
        JButton saveBtn = new JButton("Save Products");

        /* ---------- Add Product ---------- */
        addProductBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog(this, "Product name:");
            if (name == null) return;

            double price = Double.parseDouble(
                    JOptionPane.showInputDialog(this, "Price:")
            );

            int stock = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Initial stock:")
            );

            Product p = new store.Model.products.BookProduct(
                    name,
                    price,
                    stock,
                    "Added by manager",
                    store.Model.products.Category.BOOKS,
                    Color.WHITE,
                    "Admin",
                    0,
                    "book.png"
            );

            controller.managerAddProduct(p);
            JOptionPane.showMessageDialog(this, "Product added successfully");
        });

        /* ---------- Update Stock ---------- */
        updateStockBtn.addActionListener(e -> {

            List<Product> products = controller.getAllProducts();
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No products available");
                return;
            }

            String[] names = products.stream()
                    .map(Product::getDisplayName)
                    .toArray(String[]::new);

            String selectedName = (String) JOptionPane.showInputDialog(
                    this,
                    "Select product:",
                    "Update Stock",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    names,
                    names[0]
            );

            if (selectedName == null) return;

            Product selected = products.stream()
                    .filter(p -> p.getDisplayName().equals(selectedName))
                    .findFirst()
                    .orElse(null);

            if (selected == null) return;

            int amount = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Change amount (+ / -):")
            );

            controller.managerUpdateStock(selected, amount);
            JOptionPane.showMessageDialog(this, "Stock updated");
        });

        /* ---------- Load / Save ---------- */
        loadBtn.addActionListener(e -> controller.load(this));
        saveBtn.addActionListener(e -> controller.save(this));

        panel.add(addProductBtn);
        panel.add(updateStockBtn);
        panel.add(loadBtn);
        panel.add(saveBtn);

        add(panel);
    }
}
