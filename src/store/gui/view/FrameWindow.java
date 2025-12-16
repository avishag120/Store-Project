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

        StoreWindow storePanel = new StoreWindow();
        StoreController controller = new StoreController(storePanel);
        add(storePanel, BorderLayout.CENTER);


        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> controller.search(searchField.getText()));
        searchPanel.add(new JLabel("Search product:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");

        loadButton.addActionListener(e ->
                controller.load(this)
        );

        saveButton.addActionListener(e ->
                controller.save(this)
        );

        searchPanel.add(loadButton);
        searchPanel.add(saveButton);




    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameWindow frame = new FrameWindow();
            frame.setVisible(true);
        });
    }
}
