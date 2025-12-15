/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;

public interface StockManageable {
    	int getStock();
    	boolean increaseStock(int amount);
    	boolean decreaseStock(int amount);

}
