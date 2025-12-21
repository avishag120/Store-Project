package store.gui.view;

import store.Model.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderHistoryWindow extends JFrame {
    private JTextArea textArea;


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
