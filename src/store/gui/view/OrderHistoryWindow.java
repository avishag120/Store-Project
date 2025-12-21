package store.gui.view;

import store.Model.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * OrderHistoryWindow represents a window that displays
 * the history of all orders made by the user.
 *
 * The window shows basic information for each order,
 * such as order ID, total price and the list of items.
 */

public class OrderHistoryWindow extends JFrame {
    /** Text area used to display the orders information. */
    private JTextArea textArea;

    /**
     * Creates a new OrderHistoryWindow.
     *
     * @param orders a list of orders to display in the window
     */
    public OrderHistoryWindow(List<Order> orders) {
        setTitle("Order History");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        showOrders(orders);}
    /**
     * Displays the given list of orders in the text area.
     *
     * If the list is empty, a message is shown instead.
     *
     * @param orders a list of orders to display
     */
    public void showOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            textArea.setText("No orders yet.");
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (Order order : orders) {

            sb.append("Order ID: ")
                    .append(order.getOrderID())
                    .append("\n");

            sb.append("Total: ")
                    .append(order.getTotalAmount())
                    .append(" ₪\n");

            sb.append("Items: ")
                    .append(order.getItemsText())
                    .append("\n");

            sb.append("----------------------------------\n");
        }
        textArea.setText(sb.toString());
    }

}
