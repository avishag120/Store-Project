package store.gui.controler;
import javax.swing.*;
import java.io.File;

import store.Model.cart.CartItem;
import store.Model.products.Product;
import store.gui.view.CartWindow;
import store.gui.view.StoreWindow;
import store.Model.engine.StoreEngine;
import java.util.Scanner;
import store.Model.products.Category;
import store.Model.cart.Cart;
import store.Model.orders.Order;
import java.util.ArrayList;
import java.util.List;
import store.gui.view.OrderHistoryWindow;





public class StoreController {
    private StoreWindow storeWindow;
    private StoreEngine engine;
    private Cart cart = new Cart();
    private List<Order> orderHistory = new ArrayList<>();
    private int nextOrderId = 1;


    public StoreController(StoreWindow storeWindow) {

        this.storeWindow = storeWindow;
        this.engine = new StoreEngine();
        loadOrdersFromFile();
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
        chooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter(
                        "CSV files", "csv"
                )
        );

        int result = chooser.showSaveDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        try (java.io.PrintWriter pw = new java.io.PrintWriter(file)) {

            pw.println("name,price,stock,description,category,imagePath");

            for (Product p : engine.getAllProducts()) {
                pw.println(
                        p.getDisplayName() + "," +
                                p.getPrice() + "," +
                                p.getStock() + "," +
                                p.getDescription().replace(",", " ") + "," +
                                p.getCategory() + "," +
                                p.getImagePath()
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Error saving products"
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
    public void addToCart(Product product, int qty) {
        if (product.getStock() >= qty) {
            cart.addItem(product, qty);
            product.decreaseStock(qty);
            storeWindow.showProducts(engine.getAllProducts()); // רענון תצוגה
        } else {
            JOptionPane.showMessageDialog(null, "Not enough stock");
        }
    }
    public void openCart() {
        CartWindow window = new CartWindow();
        window.setController(this);
        window.showCart(cart);
        window.setVisible(true);
    }
    public void checkout() {
        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty");
            return;
        }
        Order order = new Order(
                nextOrderId++,
                new ArrayList<>(cart.getItems()),
                cart.calculateTotal()
        );

        orderHistory.add(order);
        appendOrderToFile(order);
        cart.clear();

        JOptionPane.showMessageDialog(null, "Order completed successfully");
    }
    public void openOrderHistory() {
        OrderHistoryWindow window = new OrderHistoryWindow(orderHistory);
        window.setVisible(true);
    }
    public void refreshProducts() {
        storeWindow.showProducts(engine.getAllProducts());
    }
    private void appendOrderToFile(Order order) {

        File file = new File("orders_history.csv");

        boolean writeHeader = !file.exists();

        try (java.io.FileWriter fw = new java.io.FileWriter(file, true);
             java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {

            if (writeHeader) {
                pw.println("orderId,totalAmount,items");
            }

            StringBuilder itemsText = new StringBuilder();
            for (store.Model.cart.CartItem item : order.getItems()) {
                itemsText.append(item.getProduct().getDisplayName())
                        .append(" x")
                        .append(item.getQuantity())
                        .append("; ");
            }

            pw.println(
                    order.getOrderID() + "," +
                            order.getTotalAmount() + "," +
                            "\"" + itemsText.toString() + "\""
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadOrdersFromFile() {
        File file = new File("orders_history.csv");
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {

            if (sc.hasNextLine()) sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",", 3);
                if (parts.length < 3) continue;

                int orderId = Integer.parseInt(parts[0]);
                double total = Double.parseDouble(parts[1]);
                String itemsText = parts[2].replace("\"", "");

                Order order = new Order(orderId, itemsText, total);
                order.setItemsText(itemsText);
                orderHistory.add(order);
                nextOrderId = Math.max(nextOrderId, orderId + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }











}
