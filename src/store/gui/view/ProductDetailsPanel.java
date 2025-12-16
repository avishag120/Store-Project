package store.gui.view;

import store.Model.products.Product;

import javax.swing.*;
import java.awt.*;

public class ProductDetailsPanel extends JPanel {

    private JLabel nameLabel=new JLabel();
    private JLabel priceLabel=new JLabel();
    private JLabel stockLabel=new JLabel();
    private JTextArea descriptionArea=new JTextArea();

    public ProductDetailsPanel() {
        setLayout(new BorderLayout(10, 10));
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);


        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(nameLabel);
        topPanel.add(priceLabel);
        topPanel.add(stockLabel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
    }

    public void showProduct(Product p) {
        nameLabel.setText("Name: " + p.getDisplayName());
        priceLabel.setText("Price: " + p.getPrice());
        stockLabel.setText("Stock: " + p.getStock());
        descriptionArea.setText(p.getDescription());
    }
}
