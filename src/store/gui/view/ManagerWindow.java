/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.gui.view;
import store.Model.products.Category;
import store.Model.products.ProductBuilder;
import store.Model.products.ProductFactory;
import store.gui.controler.StoreController;
import store.Model.products.Product;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import store.Model.reports.*;
import store.Model.discount.NoDiscount;
import store.Model.discount.PercentageDiscount;



/**
 * ManagerWindow represents the manager interface of the store.
 *
 * This window allows the manager to:
 * - Add new products
 * - Update stock of existing products
 * - Load products from file
 * - Save the current store state to file
 *
 * All actions are executed via the StoreController
 * and affect all active users in the system.
 */
public class ManagerWindow extends JFrame {

    /** Controller that handles all manager operations. */
    private final StoreController controller;
    private static ManagerWindow instance=null;

    /**
     * Constructs the manager window.
     *
     * Initializes the manager GUI and connects all buttons
     * to the StoreController in order to perform management actions
     * such as adding products, updating stock, and loading/saving data.
     *
     * @param controller the store controller used to execute manager actions
     */
    private ManagerWindow(StoreController controller) {
        this.controller = controller;

        setTitle("Store - Manager");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        JButton addProductBtn = new JButton("Add Product");
        JButton updateStockBtn = new JButton("Update Stock");
        JButton loadBtn = new JButton("Load Products");
        JButton saveBtn = new JButton("Save Products");
        JButton inventoryReportBtn = new JButton("Inventory Report");
        JButton salesReportBtn = new JButton("Sales Report");

        /* Add Product*/
        addProductBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Product name:");
            if (name == null) return;
            double price = Double.parseDouble(
                    JOptionPane.showInputDialog(this, "Price:")
            );
            int stock = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Initial stock:")
            );
            ProductFactory factory = new ProductFactory();

            ProductBuilder b = new ProductBuilder();
            b.setName(name);
            b.setPrice(price);
            b.setStock(stock);
            b.setDescription("Added by manager");
            b.setCategory(Category.BOOKS);
            b.setColor(Color.WHITE);
            b.setAuthor("Admin");
            b.setPage(0);
            b.setImagePath("default.png");

            Product p = factory.createProduct("book", b);


            controller.managerAddProduct(p);
            JOptionPane.showMessageDialog(this, "Product added successfully");
        });

        /* Update Stock */
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
        inventoryReportBtn.addActionListener(e -> {
            ReportWriter writer = new ConsoleWriter();
            Report report = new InventoryReport(controller.getEngine(), writer);
            report.generate();
            JOptionPane.showMessageDialog(this, "Inventory report generated (console)");
        });

        salesReportBtn.addActionListener(e -> {
            ReportWriter writer = new CsvFileWriter("sales_report.csv");
            Report report = new SalesReport(controller.getOrderHistory(), writer);
            report.generate();
            JOptionPane.showMessageDialog(this, "Sales report saved to sales_report.csv");
        });
        JButton discountBtn = new JButton("Set Discount");
        discountBtn.addActionListener(e -> {
            String[] options = {"No Discount", "Percentage"};
            String choice = (String) JOptionPane.showInputDialog(
                    this, "Choose discount type:", "Discount",
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]
            );
            if (choice == null) return;

            if (choice.equals("No Discount")) {
                controller.setDiscountStrategy(new NoDiscount());
                JOptionPane.showMessageDialog(this, "Discount set: No Discount");
            } else {
                String txt = JOptionPane.showInputDialog(this, "Percent (0-100):");
                if (txt == null) return;
                double p = Double.parseDouble(txt);
                controller.setDiscountStrategy(new PercentageDiscount(p));
                JOptionPane.showMessageDialog(this, "Discount set: " + p + "%");
            }
        });



        /* Load / Save */
        loadBtn.addActionListener(e -> controller.load(this));
        saveBtn.addActionListener(e -> controller.save(this));
        panel.add(addProductBtn);
        panel.add(updateStockBtn);
        panel.add(loadBtn);
        panel.add(saveBtn);
        panel.add(inventoryReportBtn);
        panel.add(salesReportBtn);
        panel.add(discountBtn);
        add(panel);
    }
    public static ManagerWindow getInstance(StoreController controller) {
        if(instance==null)
            synchronized (ManagerWindow.class){
                if(instance==null){
                    instance=new ManagerWindow(controller);
                }
            }

        return instance;


    }


}
