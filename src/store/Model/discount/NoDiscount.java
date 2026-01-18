/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.discount;

public class NoDiscount implements DiscountStrategy{
    @Override
    public double apply(double total) {
        return total;
    }

    @Override
    public String getName() {
        return "NoDiscount";
    }
}
