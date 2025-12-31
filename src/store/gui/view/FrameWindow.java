/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakies – ID 325684678
 */
package store.gui.view;
import store.Model.engine.StoreEngine;
import store.gui.controler.StoreController;
import javax.swing.*;
import java.awt.*;
/**
 * The main window of the online store application.
 * This class is responsible for building the main GUI layout,
 * including the product catalog, search and filter controls,
 * cart access, and order history access.
 *
 * This class belongs to the View layer and does not contain
 * any business logic.
 */
public class FrameWindow extends JFrame {
    private  StoreController controller;
    /**
     * Constructs the main application window.
     * Initializes all GUI components, layouts and event listeners,
     * and connects the View to the StoreController.
     */
    public FrameWindow(StoreEngine engine, StoreController controller) {
        setTitle("Online Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());
        // Product details panel
        ProductDetailsPanel detailsPanel = new ProductDetailsPanel();
        detailsPanel.setPreferredSize(new Dimension(250, 600));
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightWrapper.add(detailsPanel);
        add(rightWrapper, BorderLayout.EAST);
        //Store catalog panel (center)
        StoreWindow storePanel = new StoreWindow(detailsPanel);
        add(storePanel, BorderLayout.CENTER);
        this.controller = controller;
        controller.setStoreWindow(storePanel);
        storePanel.setController(controller);
        detailsPanel.setController(controller);
        // search, filter, buttons
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 1));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Search by product name
        JComboBox<String> searchField = new JComboBox<>();
        searchField.addItem("ALL");
        JComboBox<String> categoryBox = new JComboBox<>(
                new String[]{"ALL", "CLOTHING", "BOOKS", "ELECTRONICS"}
        );
        // Load & Save buttons
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        searchField.addActionListener(e -> {
            String selected = (String) searchField.getSelectedItem();
            if (selected == null || selected.equals("ALL")) {
                controller.search("");
            } else {
                controller.search(selected);
            }
        });
        //Category filter
        categoryBox.addActionListener(e -> {
            String selected = (String) categoryBox.getSelectedItem();
            controller.filterByCategory(selected);
        });
        loadButton.addActionListener(e -> {
            controller.load(this);
            Timer t = new Timer(500, ev -> {
                searchField.removeAllItems();
                searchField.addItem("ALL");

                for (store.Model.products.Product p : controller.getAllProducts()) {
                    searchField.addItem(p.getDisplayName());
                }
            });
            t.setRepeats(false);
            t.start();
        });

        saveButton.addActionListener(e ->
                controller.save(this)
        );
        row1.add(new JLabel("Search product:"));
        row1.add(searchField);
        row1.add(new JLabel("Category:"));
        row1.add(categoryBox);
        row2.add(loadButton);
        row2.add(saveButton);
        //  Cart & order history
        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> controller.openCart());
        JButton historyButton = new JButton("Order History");
        historyButton.addActionListener(e -> controller.openOrderHistory());
        searchPanel.add(historyButton);
        row2.add(cartButton);
        row2.add(historyButton);
        searchPanel.add(row1);
        searchPanel.add(row2);
        add(searchPanel,BorderLayout.NORTH);
        detailsPanel.setController(controller);

        engine.addListener(() -> {
            SwingUtilities.invokeLater(() -> {
                storePanel.showProducts(engine.getAllProducts());

            });
        });

//        engine.addProductListener(changedProduct -> {
//            SwingUtilities.invokeLater(() -> {
//                detailsPanel.refreshIfShowing(changedProduct);
//            });
//        });
//    }
//    public StoreController getController() {
//        return controller;
//    }

}}
