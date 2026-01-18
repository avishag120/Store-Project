/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store;
import store.Model.engine.StoreEngine;
import store.gui.controler.MainController;
import store.gui.view.MainWindow;
import javax.swing.SwingUtilities;

/**
 * MainTest is the entry point of the store application.
 *
 * It initializes the StoreEngine and MainController
 * and launches the main GUI window.
 */
public class MainTest {
    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        StoreEngine engine = StoreEngine.getInstance();
        MainController controller = new MainController(engine);
        SwingUtilities.invokeLater(() -> {
            new MainWindow(controller).setVisible(true);
        });

    }
}

