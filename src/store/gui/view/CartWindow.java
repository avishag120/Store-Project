/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.gui.view;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import store.Model.cart.Cart;
import store.Model.cart.CartItem;
import store.Model.products.Product;
import store.gui.controler.StoreController;


public class CartWindow extends JFrame {
    private JPanel itemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private StoreController controller;



    public CartWindow() {
        setTitle("Shopping Cart");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        add(scrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total: 0 ₪");
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            controller.checkout();
            dispose();
        });

        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    public void showCart(Cart cart) {
        itemsPanel.removeAll();
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            JPanel row = new JPanel(new BorderLayout(10, 10));
            row.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
            JLabel imageLabel = new JLabel();
            imageLabel.setPreferredSize(new Dimension(70, 70));
            String imagePath = "/images/" + p.getImagePath();
            URL url = getClass().getResource(imagePath);

            if (url != null) {
                Image img = new ImageIcon(url).getImage()
                        .getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            }
            row.add(imageLabel, BorderLayout.WEST);
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            JLabel name = new JLabel(p.getDisplayName());
            name.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel price = new JLabel("Price: " + p.getPrice() + " ₪");
            info.add(name);
            info.add(price);
            row.add(info, BorderLayout.CENTER);
            JPanel qtyPanel = new JPanel(new FlowLayout());
            JButton minus = new JButton("-");
            JLabel qtyLabel = new JLabel(String.valueOf(item.getQuantity()));
            JButton plus = new JButton("+");
            JButton remove = new JButton("Remove");
            remove.addActionListener(e -> {
                int qty = item.getQuantity();
                cart.removeItem(p);
                p.increaseStock(qty);
                showCart(cart);
            });
            minus.addActionListener(e -> {
                if (item.getQuantity() <= 1) {
                    cart.removeItem(p);
                    p.increaseStock(1);
                } else {
                    item.decrease();
                    p.increaseStock(1);
                }
                controller.refreshProducts();
                showCart(cart);
            });
            plus.addActionListener(e -> {
                if (p.getStock() > 0) {
                    item.increase();
                    p.decreaseStock(1);
                    controller.refreshProducts();
                    showCart(cart);
                } else {
                    JOptionPane.showMessageDialog(this, "No more stock");
                }
            });
            remove.addActionListener(e -> {
                int qty = item.getQuantity();
                cart.removeItem(p);
                p.increaseStock(qty);
                controller.refreshProducts(); // ← גם כאן
                showCart(cart);
            });
            qtyPanel.add(minus);
            qtyPanel.add(qtyLabel);
            qtyPanel.add(plus);
            qtyPanel.add(remove);
            row.add(qtyPanel, BorderLayout.EAST);
            itemsPanel.add(row);
            itemsPanel.add(Box.createVerticalStrut(8));
        }
        totalLabel.setText("Total: " + cart.calculateTotal() + " ₪");
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }
    public void setController(StoreController controller) {
        this.controller = controller;
    }





}
