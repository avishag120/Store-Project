/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.shipping;

public class FastShipAPI {
    public void executeDelivery(String orderId, String payload) {
        System.out.println("[FastShip] Delivery executed. orderId=" + orderId + " payload=" + payload);
    }
}
