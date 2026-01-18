/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.shipping;

import store.Model.orders.Order;

public class FastShipAdapter implements ShippingProvider{
    private final FastShipAPI fastShip = new FastShipAPI();

    @Override
    public void shipOrder(Order order) {
        if (order == null) return;
        String orderId = String.valueOf(order.getOrderID());
        String payload = "total=" + order.getTotalAmount() + ", items=" + order.getItems().size();
        fastShip.executeDelivery(orderId, payload);
    }
}
