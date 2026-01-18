/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.reports;
import store.Model.engine.StoreEngine;
import store.Model.products.Product;
public class InventoryReport extends Report{
    private final StoreEngine engine;

    public InventoryReport(StoreEngine engine, ReportWriter writer) {
        super(writer);
        this.engine = engine;
    }

    @Override
    public void generate() {
        StringBuilder sb = new StringBuilder("Inventory Report\n");
        for (Product p : engine.getAllProducts()) {
            sb.append(p.getDisplayName())
                    .append(", stock=").append(p.getStock())
                    .append("\n");
        }
        writer.write(sb.toString());
    }
}
