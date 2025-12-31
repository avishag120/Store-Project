/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.gui.view;
import store.Model.products.Product;
import store.gui.controler.StoreController;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
/**
 * StoreWindow represents the main product catalog view.
 *
 * This panel displays all available products in a grid layout.
 * Each product is shown as a button with an image and name.
 * When a product is selected, its details are shown
 * in the ProductDetailsPanel.
 */
public class StoreWindow extends JPanel {

    /** Panel used to display details of the selected product. */
    private ProductDetailsPanel detailsPanel;

    /** Reference to the store controller. */
    private StoreController controller;

    /**
     * Creates a new StoreWindow.
     *
     * @param detailsPanel the panel used to display product details
     */
    public StoreWindow(ProductDetailsPanel detailsPanel) {
        this.detailsPanel = detailsPanel;
        setLayout(new GridLayout(0, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    /**
     * Sets the controller for this window.
     *
     * @param controller the StoreController instance
     */
    public void setController(StoreController controller) {
        this.controller = controller;
    }
    /**
     * Displays the details of the selected product
     * in the ProductDetailsPanel.
     *
     * @param p the selected product
     * @param icon the product image icon
     */
    public void showProductDetails(Product p,ImageIcon icon) {
        detailsPanel.showProduct(p,icon);
    }
    /**
     * Displays the given list of products in a grid view.
     *
     * Each product is shown as a button with its name,
     * image and tooltip text.
     *
     * @param products the list of products to display
     */
    public void showProducts(List<Product> products) {
        removeAll();
        for (Product p : products) {
            JButton btn = new JButton(p.getDisplayName());
            btn.setToolTipText("Name: "+p.getDisplayName()+" | Price: "+p.getPrice()+" | Stock: "+p.getStock());
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            String imagePath = "/images/" + p.getImagePath();
            URL imageURL = getClass().getResource(imagePath);
            if (imageURL == null) {
                imageURL = getClass().getResource("/images/default.png");
            }
            ImageIcon scaledIcon= null;
            if(imageURL != null){
                ImageIcon icon = new ImageIcon(imageURL);
                int imageWidth = 120;
                int imageHeight = 120;
                Image image = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(image);
                btn.setIcon(scaledIcon);

            }
            else {
                System.out.println("Image not found: " + imagePath);
            }
            final ImageIcon scaledIconForListener= scaledIcon;
            btn.addActionListener(e ->
                    controller.productSelected(p,scaledIconForListener)
            );
            add(btn);
        }
        revalidate();
        repaint();
    }
    public void showOutOfStockMessage() {
        detailsPanel.showOutOfStockMessage();
    }





}
