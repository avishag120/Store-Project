/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.gui.controler;
import store.Model.engine.StoreEngine;
import store.gui.view.FrameWindow;
import store.gui.view.ManagerWindow;
import javax.swing.*;
/**
 * MainController is responsible for opening
 * the main application windows (customer and manager).
 *
 * It connects the StoreEngine with the different GUI entry points.
 */
public class MainController {
    /** Shared store engine used by all windows. */
    private StoreEngine engine;
    /** Main store controller shared between customer and manager windows. */
    private StoreController storeController;

    /**
     * Creates the main controller of the application.
     *
     * @param engine the shared StoreEngine instance
     */
    public MainController(StoreEngine engine) {
        this.engine = engine;
        this.storeController = new StoreController(null, engine);
    }
    /**
     * Opens a new customer window.
     * The window is created on the Swing Event Dispatch Thread.
     */
    public void openCustomer() {
        SwingUtilities.invokeLater(() -> {
            FrameWindow window = new FrameWindow(engine, storeController);
            window.setVisible(true);
        });
    }
    /**
     * Opens a new manager window.
     * The window is created on the Swing Event Dispatch Thread.
     */
    public void openManager() {
        SwingUtilities.invokeLater(() -> {
            ManagerWindow window = new ManagerWindow(storeController);
            window.setVisible(true);
        });
    }

}

