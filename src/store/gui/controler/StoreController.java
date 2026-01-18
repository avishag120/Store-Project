/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakies – ID 325684678
 */
package store.gui.controler;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import store.Model.cart.CartItem;
import store.Model.products.Product;
import store.Model.products.ProductFactory;
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
import store.Model.products.ProductBuilder;
import store.Model.shipping.ShippingProvider;
import store.Model.shipping.FastShipAdapter;
import store.Model.discount.DiscountStrategy;



public class StoreController {
    /** The main store window (catalog + details). */
    private StoreWindow storeWindow;
    /** Store engine that manages the products list. */
    private StoreEngine engine;
    /** Current shopping cart for this session. */
    private Cart cart = new Cart();
    /** List of all orders created in this session (also loaded from file). */
    private List<Order> orderHistory = new ArrayList<>();
    /** Next order id to use when creating a new order. */
    private int nextOrderId = 1;
    private final ShippingProvider shippingProvider = new FastShipAdapter();


    /**
     * Creates the controller and loads previous orders from the local CSV file (if exists).
     *
     * @param storeWindow the main store window that this controller controls
     */
    public StoreController(StoreWindow storeWindow, StoreEngine engine) {
        this.storeWindow = storeWindow;
        this.engine = engine;
        loadOrdersFromFile();
        }
    /**
     * Searches products by name (case-insensitive) and updates the catalog view.
     * If the text is null/empty, it shows all products.
     *
     * @param text user search input (product name part)
     */
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
    /**
     * Loads products from a CSV file selected by the user and shows them in the catalog.
     * The method clears current products in the engine and then reads the file line by line.
     * Expected CSV header: name,price,stock,description,category,imagePath
     *
     * @param frame parent frame used for dialogs (file chooser + messages)
     */
    public void load(JFrame frame) {
        new Thread(() -> {
            synchronized(engine){
                if(engine.isLoaded()){
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(frame,"Products already loaded");
                        if (storeWindow != null) {
                            storeWindow.showProducts(engine.getAllProducts());
                        }

                    });
                    return;
                }
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter("CSV files", "csv"));
            if(chooser.showOpenDialog(frame)!=JFileChooser.APPROVE_OPTION) return;
            File file=chooser.getSelectedFile();
            try (Scanner sc = new Scanner(file)) {
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
                while(sc.hasNextLine()){
                    String[] parts=sc.nextLine().split(",");
                    if(parts.length<6) continue;

                    String name=parts[0];
                    double price=Double.parseDouble(parts[1]);
                    int stock=Integer.parseInt(parts[2]);
                    String description=parts[3];
                    Category category=Category.valueOf(parts[4]);
                    String imagePath=parts[5];

                    ProductFactory factory = new ProductFactory();

                    ProductBuilder b = new ProductBuilder();
                    b.setName(name);
                    b.setPrice(price);
                    b.setStock(stock);
                    b.setDescription(description);
                    b.setCategory(category);
                    b.setImagePath(imagePath);

                    Product p = null;

                    switch (category) {

                        case CLOTHING:
                            b.setColor(Color.BLUE);
                            b.setSize("M");
                            p = factory.createProduct("clothing", b);
                            break;

                        case BOOKS:
                            b.setColor(Color.WHITE);
                            b.setAuthor("Unknown Author");
                            b.setPage(0);
                            p = factory.createProduct("book", b);
                            break;

                        case ELECTRONICS:
                            b.setColor(Color.BLACK);
                            b.setWarrantyMonths(12);
                            b.setBrand("Unknown Brand");
                            p = factory.createProduct("electronics", b);
                            break;
                    }


                    if (p != null) {
                        engine.addProduct(p);
                    }
                }
                engine.setLoaded(true);
                SwingUtilities.invokeLater(() -> {
                    storeWindow.showProducts(engine.getAllProducts());
                    JOptionPane.showMessageDialog(frame,"Products loaded successfully");
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(frame,e.toString())
                );
            }
        }
        ).start();
    }
    /**
     * Saves all current products to a CSV file chosen by the user.
     * CSV header: name,price,stock,description,category,imagePath
     *
     * @param frame parent frame used for the save dialog
     */
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
    /**
     * Called when the user selects a product card from the catalog.
     * Opens the product details area/window.
     *
     * @param product selected product
     * @param icon product image icon (already loaded by the view)
     */
    public void productSelected(Product product,ImageIcon icon) {
        storeWindow.showProductDetails(product,icon);
    }
    /**
     * Filters the catalog by a selected category and updates the view.
     * If categoryText is "ALL", it shows all products.
     *
     * @param categoryText category name as string (for example: "ALL", "BOOKS", "CLOTHING", ...)
     * @throws IllegalArgumentException if categoryText is not a valid Category name and not "ALL"
     */
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
    /**
     * Adds the given product to the cart in the requested quantity.
     * If there is enough stock, the stock is decreased and the catalog view is refreshed.
     *
     * @param product product to add
     * @param qty quantity to add (must be positive)
     */
    public void addToCart(Product product, int qty) {
        new Thread(() -> {
            synchronized (product) {
                if (product.getStock() >= qty) {
                    cart.addItem(product, qty);
                    product.decreaseStock(qty);
                    SwingUtilities.invokeLater(() -> {
                        storeWindow.showProducts(engine.getAllProducts());
                        storeWindow.showProductDetails(product, null);
                    });
                } else {
                    SwingUtilities.invokeLater(() ->
                                    storeWindow.showOutOfStockMessage()
                    );
                }
            }
        }).start();
        engine.notifyListeners();
    }
    /**
     * Opens the cart window and shows current cart items.
     */
    public void openCart() {
        CartWindow window = new CartWindow();
        window.setController(this);
        window.showCart(cart);
        engine.addListener(() -> SwingUtilities.invokeLater(() -> window.showCart(cart)));
        window.setVisible(true);
    }
    /**
     * Creates an order from the current cart and saves it to the order history file.
     * After checkout, the cart is cleared.
     * If the cart is empty, a message is shown and nothing happens.
     */
    public void checkout() {
        new Thread(() -> {
            if (cart.getItems().isEmpty()) {
                SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(null, "Cart is empty"));
                return;
            }
            List<CartItem> snapshot = new ArrayList<>();
            for (CartItem item : cart.getItems()) {
                snapshot.add(
                        new CartItem(item.getProduct(), item.getQuantity())
                );
            }

            Order order = new Order(
                    nextOrderId++,
                    snapshot,
                    cart.calculateTotal()
            );
            synchronized (orderHistory) {
                orderHistory.add(order);
                appendOrderToFile(order);
                shippingProvider.shipOrder(order);


            }
            cart.clear();
        }).start();


    }
    /**
     * Opens the order history window and displays the orders loaded/created.
     */
    public void openOrderHistory() {
        OrderHistoryWindow window = new OrderHistoryWindow(orderHistory);
        window.setVisible(true);
    }

    /**
     * Appends one order to a local CSV file named "orders_history.csv".
     * If the file does not exist, it creates it and writes the header line.
     *
     * @param order the order to save
     */
    private synchronized  void appendOrderToFile(Order order) {

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
    /**
     * Loads orders from a local CSV file named "orders_history.csv" into memory.
     * Updates {@link #nextOrderId} so new orders will continue after the last saved id.
     * If the file does not exist, nothing happens.
     */
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
    /**
     * Returns all products from the store engine.
     * This is usually used by GUI views that need to refresh or display products.
     *
     * @return list of all products in the store
     */
    public java.util.List<store.Model.products.Product> getAllProducts() {
        return engine.getAllProducts();
    }
    /**
     * Saves the current list of products to the default CSV file ("products.csv").
     *
     * The method overwrites the file and writes all products
     * currently stored in the engine.
     *
     * This method is synchronized to prevent concurrent writes
     * to the file from multiple threads.
     */
    private synchronized void saveToDefaultFile() {
        try (java.io.PrintWriter pw = new java.io.PrintWriter("products.csv")) {
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
            e.printStackTrace();
        }
    }
    /**
     * Adds a new product to the store as a manager action.
     *
     * The operation is synchronized on the engine to ensure
     * thread-safe modification of the shared products list.
     *
     * After adding the product, the updated state is saved
     * to the default file and all listeners are notified
     * to refresh their views.
     *
     * @param p the product to add
     */
    public void managerAddProduct(Product p) {
        synchronized (engine) {
            engine.addProduct(p);
            saveToDefaultFile();
        }
        SwingUtilities.invokeLater(() -> {
            engine.notifyListeners();
        });
    }
    /**
     * Updates the stock amount of an existing product.
     *
     * The update is synchronized on the product instance
     * to prevent concurrent stock modifications.
     *
     * After updating the stock, the new state is saved
     * to the default file and all listeners are notified
     * so all open windows reflect the change immediately.
     *
     * @param p the product to update
     * @param amount the amount to add (positive) or remove (negative)
     */
    public void managerUpdateStock(Product p, int amount) {
        synchronized (p) {
            if (amount > 0) {
                p.increaseStock(amount);
            } else {
                p.decreaseStock(-amount);
            }
            saveToDefaultFile();
        }
        SwingUtilities.invokeLater(() -> {
            engine.notifyListeners();
        });
    }
    /**
     * Sets the store window associated with this controller.
     *
     * This method is used to connect the controller to
     * a specific StoreWindow instance, allowing the controller
     * to refresh the catalog view when data changes.
     *
     * @param storeWindow the StoreWindow to control
     */
    public void setStoreWindow(StoreWindow storeWindow) {
        this.storeWindow = storeWindow;
    }
    public void notifyStockChanged() {
        engine.notifyListeners();
    }
    public StoreEngine getEngine() {
        return engine;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    public double getCartTotalAfterDiscount() {
        double total = cart.calculateTotal();
        return engine.getDiscountStrategy().apply(total);
    }

    public void setDiscountStrategy(DiscountStrategy s) {
        engine.setDiscountStrategy(s);
    }












}
