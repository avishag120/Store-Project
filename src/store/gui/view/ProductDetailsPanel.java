package store.gui.view;

import store.Model.products.Product;
import store.gui.controler.StoreController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProductDetailsPanel extends JPanel {

    private JLabel nameLabel=new JLabel();
    private JLabel priceLabel=new JLabel();
    private JLabel stockLabel=new JLabel();
    private JTextArea descriptionArea=new JTextArea();
    private JLabel imageLabel=new JLabel();
    private JButton addToCartButton=new JButton("Add to cart");
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

            JOptionPane.showMessageDialog(
                    this,
                    "Product added to cart successfully"
            );
        });
    }
    public void setController(StoreController controller) {
        this.controller = controller;
    }


}
