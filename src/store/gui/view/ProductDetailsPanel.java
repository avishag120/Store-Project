package store.gui.view;

import store.Model.products.Product;

import javax.swing.*;
import java.awt.*;

public class ProductDetailsPanel extends JPanel {

    private JLabel nameLabel=new JLabel();
    private JLabel priceLabel=new JLabel();
    private JLabel stockLabel=new JLabel();
    private JTextArea descriptionArea=new JTextArea();
    private JLabel imageLabel=new JLabel();
    private JButton addToCartButton=new JButton("Add to cart");


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
    public void showProduct(Product p) {

        nameLabel.setText("Name: " + p.getDisplayName());
        priceLabel.setText("Price: " + p.getPrice() + " ₪");
        stockLabel.setText("In stock: " + p.getStock());

        descriptionArea.setText(p.getDescription());

        // טעינת תמונה לפי הנתיב של המוצר
        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getClassLoader().getResource(p.getImagePath())
            );
            imageLabel.setIcon(icon);
        } catch (Exception e) {
            imageLabel.setIcon(null);
        }

        // פעולה מינימלית ל-Add to Cart
        addToCartButton.addActionListener(ev ->
                JOptionPane.showMessageDialog(
                        this,
                        "Product added to cart"
                )
        );
    }

}
