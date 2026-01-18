package store.Model.reports;

import store.Model.orders.Order;

/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
import java.util.List;

public class SalesReport extends Report {
    private final List<Order> orders;

    public SalesReport(List<Order> orders, ReportWriter writer) {
        super(writer);
        this.orders = orders;
    }

    @Override
    public void generate() {
        StringBuilder sb = new StringBuilder("Sales Report\n");
        for (Order o : orders) {
            sb.append("Order ")
                    .append(o.getOrderID())
                    .append(", total=").append(o.getTotalAmount())
                    .append("\n");
        }
        writer.write(sb.toString());
    }
}
