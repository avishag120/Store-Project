package store.gui.view;
import store.gui.controler.StoreController;
import javax.swing.*;
import java.awt.*;

public class FrameWindow extends JFrame {

    public FrameWindow() {
        setTitle("Online Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLayout(new BorderLayout());

        ProductDetailsPanel detailsPanel = new ProductDetailsPanel();
        detailsPanel.setPreferredSize(new Dimension(250, 600));
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightWrapper.add(detailsPanel);

        add(rightWrapper, BorderLayout.EAST);
        StoreWindow storePanel = new StoreWindow(detailsPanel);
        add(storePanel, BorderLayout.CENTER);

        StoreController controller = new StoreController(storePanel);
        storePanel.setController(controller);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 1));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");

        JComboBox<String> categoryBox = new JComboBox<>(
                new String[]{"ALL", "CLOTHING", "BOOKS", "ELECTRONICS"}
        );


        searchButton.addActionListener(e ->
                controller.search(searchField.getText())
        );
        categoryBox.addActionListener(e -> {
            String selected = (String) categoryBox.getSelectedItem();
            controller.filterByCategory(selected);
        });
        loadButton.addActionListener(e ->
                controller.load(this)
        );

        saveButton.addActionListener(e ->
                controller.save(this)
        );

        row1.add(new JLabel("Search product:"));
        row1.add(searchField);
        row1.add(searchButton);
        row1.add(new JLabel("Category:"));
        row1.add(categoryBox);

        row2.add(loadButton);
        row2.add(saveButton);
        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> controller.openCart());

        JButton historyButton = new JButton("Order History");
        historyButton.addActionListener(e -> controller.openOrderHistory());

        row2.add(cartButton);
        row2.add(historyButton);
        searchPanel.add(row1);
        searchPanel.add(row2);

        add(searchPanel,BorderLayout.NORTH);

        detailsPanel.setController(controller);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameWindow frame = new FrameWindow();
            frame.setVisible(true);
        });
    }
}
