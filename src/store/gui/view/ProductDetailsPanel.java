/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.gui.view;
import store.Model.products.Product;
import store.gui.controler.StoreController;
import javax.swing.*;
import java.awt.*;
/**
 * ProductDetailsPanel is a panel that displays detailed
 * information about a selected product.
 *
 * It shows the product image, name, price, stock,
 * description and allows the user to add the product
 * to the shopping cart.
 */
public class ProductDetailsPanel extends JPanel {
    /** Label for displaying the product name. */
    private JLabel nameLabel=new JLabel();

    /** Label for displaying the product price. */
    private JLabel priceLabel=new JLabel();


    /** Label for displaying the product stock. */
    private JLabel stockLabel=new JLabel();

    /** Text area for displaying the product description. */
    private JTextArea descriptionArea=new JTextArea();

    /** Label for displaying the product image. */
    private JLabel imageLabel=new JLabel();

    /** Button used to add the product to the cart. */
    private JButton addToCartButton=new JButton("Add to cart");

    /** Label that shows a confirmation message when a product is added. */
    private JLabel addedLabel = new JLabel("✔ Added to cart");

    /** Reference to the store controller. */
    private StoreController controller;
    private JLabel statusLabel = new JLabel();
    private Product currentProduct;

    /**
     * Creates a new ProductDetailsPanel and initializes
     * all UI components.
     */


    public ProductDetailsPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        content.add(imageLabel);
        content.add(nameLabel);
        content.add(priceLabel);
        content.add(stockLabel);
        content.add(Box.createVerticalStrut(8));
        content.add(new JScrollPane(descriptionArea));
        content.add(Box.createVerticalStrut(8));
        content.add(addToCartButton);
        addedLabel.setForeground(new Color(0, 140, 0));
        addedLabel.setVisible(false);
        addedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setForeground(Color.RED);
        statusLabel.setVisible(false);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(Box.createVerticalStrut(6));
        content.add(addedLabel);
        content.add(statusLabel);
        add(content);
    }
    /**
     * Displays the details of the selected product.
     *
     * @param p the product to display
     * @param icon the product image icon
     */
    public void showProduct(Product p, ImageIcon icon) {
        this.currentProduct = p;
        imageLabel.setIcon(icon);
        nameLabel.setText("Name: " + p.getDisplayName());
        priceLabel.setText("Price: " + p.getPrice() + " ₪");
        stockLabel.setText("In stock: " + p.getStock());
        descriptionArea.setText(p.getDescription());
        statusLabel.setVisible(false);
        statusLabel.setText("");
        for (var l : addToCartButton.getActionListeners()) {
            addToCartButton.removeActionListener(l);
        }
        addToCartButton.addActionListener(e -> {
            if (p.getStock() <= 0) {
                showOutOfStockMessage();
                return;
            }
            controller.addToCart(p, 1);
            showAddedMessage();
        });
    }
    /**
     * Shows a temporary confirmation message
     * after adding a product to the cart.
     */
    private void showAddedMessage() {
        addedLabel.setVisible(true);

        Timer timer = new Timer(1200, e -> addedLabel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
    /**
     * Sets the store controller for this panel.
     *
     * @param controller the StoreController instance
     */
    public void setController(StoreController controller) {
        this.controller = controller;
    }
    public void setStatusMessage(String msg) {
        statusLabel.setText(msg);
        statusLabel.setVisible(true);
    }
    public void showOutOfStockMessage() {
        setStatusMessage("Cannot be added – product is out of stock");
        statusLabel.setVisible(true);
    }
//    public void refreshIfShowing(Product changedProduct) {
//        if (currentProduct == changedProduct) {
//            stockLabel.setText("In stock: " + currentProduct.getStock());
//        }
//    }
public void refreshIfShowing() {
    if (currentProduct != null) {
        stockLabel.setText("In stock: " + currentProduct.getStock());
    }
}




}
