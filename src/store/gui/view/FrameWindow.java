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
        detailsPanel.setPreferredSize(new Dimension(180, 180));
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


        searchButton.addActionListener(e ->
                controller.search(searchField.getText())
        );

        loadButton.addActionListener(e ->
                controller.load(this)
        );

        saveButton.addActionListener(e ->
                controller.save(this)
        );

        searchPanel.add(new JLabel("Search product:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(loadButton);
        searchPanel.add(saveButton);

        add(searchPanel,BorderLayout.NORTH);
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameWindow frame = new FrameWindow();
            frame.setVisible(true);
        });
    }
}
