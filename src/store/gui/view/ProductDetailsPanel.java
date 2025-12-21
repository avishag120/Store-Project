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

public class ProductDetailsPanel extends JPanel {

    private JLabel nameLabel=new JLabel();
    private JLabel priceLabel=new JLabel();
    private JLabel stockLabel=new JLabel();
    private JTextArea descriptionArea=new JTextArea();
    private JLabel imageLabel=new JLabel();
    private JButton addToCartButton=new JButton("Add to cart");
    private JLabel addedLabel = new JLabel("✔ Added to cart");
    private StoreController controller;

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
        content.add(Box.createVerticalStrut(6));
        content.add(addedLabel);
        add(content);
    }
    public void showProduct(Product p, ImageIcon icon) {
        imageLabel.setIcon(icon);
        nameLabel.setText("Name: " + p.getDisplayName());
        priceLabel.setText("Price: " + p.getPrice() + " ₪");
        stockLabel.setText("In stock: " + p.getStock());
        descriptionArea.setText(p.getDescription());
        for (var l : addToCartButton.getActionListeners()) {
            addToCartButton.removeActionListener(l);
        }
        addToCartButton.addActionListener(e -> {
            controller.addToCart(p, 1);
            showAddedMessage();
        });
    }
    private void showAddedMessage() {
        addedLabel.setVisible(true);

        Timer timer = new Timer(1200, e -> addedLabel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
    public void setController(StoreController controller) {
        this.controller = controller;
    }


}
