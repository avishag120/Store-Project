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

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

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

        searchPanel.add(new JLabel("Search product:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(new JLabel("Category:"));
        searchPanel.add(categoryBox);
        searchPanel.add(loadButton);
        searchPanel.add(saveButton);

        add(searchPanel,BorderLayout.NORTH);
        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> controller.openCart());
        searchPanel.add(cartButton);

        detailsPanel.setController(controller);


    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameWindow frame = new FrameWindow();
            frame.setVisible(true);
        });
    }
}
