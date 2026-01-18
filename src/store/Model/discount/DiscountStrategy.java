/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.discount;

public interface DiscountStrategy {
    double apply(double total);
    String getName();
}
