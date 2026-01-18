/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.shipping;

import store.Model.orders.Order;

public interface ShippingProvider {
    void shipOrder(Order order);
}
