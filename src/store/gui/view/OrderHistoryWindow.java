package store.gui.view;

import store.Model.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderHistoryWindow extends JFrame {

    public OrderHistoryWindow(List<Order> orders) {
        setTitle("Order History");
        setSize(400, 500);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        if (orders.isEmpty()) {
            textArea.setText("No orders yet.");
        } else {
            for (Order order : orders) {
                textArea.append(order.toString());
                textArea.append("\n----------------------\n");
            }
        }

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
