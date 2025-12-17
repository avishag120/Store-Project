package store.gui.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import store.Model.products.Product;
import store.gui.controler.StoreController;

public class ProductCard extends  JPanel {
    private Product product;

    public ProductCard(Product product, StoreController controller) {
        this.product = product;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(120,120));
        JLabel nameLabel = new JLabel(product.getDisplayName(), SwingConstants.CENTER);
        add(nameLabel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.productSelected(product);
            }
        });
    }
}
