/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakies – ID 325684678
 */
package store.gui.view;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import store.Model.cart.Cart;
import store.Model.cart.CartItem;
import store.Model.products.Product;
import store.gui.controler.StoreController;

/**
 * A GUI window that displays the user's shopping cart.
 * <p>
 * The window shows each cart item as a row with:
 * image, product name, price, quantity controls (+ / -) and remove button.
 * It also shows the total cart price and a checkout button.
 */
public class CartWindow extends JFrame {
    /** Panel that contains all cart item rows. */
    private JPanel itemsPanel;
    /** Label that shows the total cart price. */
    private JLabel totalLabel;
    /** Button that performs checkout and closes the window. */
    private JButton checkoutButton;
    /** Controller used to call actions like checkout and refresh products. */
    private StoreController controller;
    private JLabel discountLabel;



    /**
     * Builds the shopping cart window UI (layout + buttons).
     * The cart content itself is displayed using {@link #showCart(Cart)}.
     */
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
        discountLabel = new JLabel("");
        discountLabel.setForeground(Color.GRAY);
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            controller.checkout();
            dispose();
        });

        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
        bottomLeft.add(totalLabel);
        bottomLeft.add(discountLabel);
        bottomPanel.add(bottomLeft, BorderLayout.WEST);

    }
    /**
     * Displays the current cart items in the window.
     * <p>
     * For each item it creates a row with:
     * image, name, price, quantity controls (+ / -) and remove.
     * After updating the items list, it also updates the total label.
     *
     * @param cart the cart to display
     */
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
            minus.addActionListener(e -> {
                if (item.getQuantity() <= 1) {
                    cart.removeItem(p);
                    p.increaseStock(1);
                } else {
                    item.decrease();
                    p.increaseStock(1);
                }
                controller.notifyStockChanged();
                showCart(cart);
            });
            plus.addActionListener(e -> {
                if (p.getStock() > 0) {
                    item.increase();
                    p.decreaseStock(1);
                    controller.notifyStockChanged();
                    showCart(cart);
                } else {
                    JOptionPane.showMessageDialog(this, "No more stock");
                }
            });
            remove.addActionListener(e -> {
                int qty = item.getQuantity();
                cart.removeItem(p);
                p.increaseStock(qty);
                controller.notifyStockChanged();
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
        totalLabel.setText(
                String.format("Total: %.2f ₪",controller.getCartTotalAfterDiscount())

        );
        String discountName = controller.getEngine()
                .getDiscountStrategy()
                .getName();

        if (discountName.equals("NoDiscount")) {
            discountLabel.setText("No discount applied");
        } else {
            discountLabel.setText("Discount applied: " + discountName);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }
    /**
     * Sets the controller for this window.
     * The controller is used for actions like checkout and refreshing the products list.
     *
     * @param controller store controller instance
     */
    public void setController(StoreController controller) {
        this.controller = controller;
    }





}
