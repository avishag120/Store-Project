/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.discount;

public class PercentageDiscount implements DiscountStrategy{
    private final double percent;

    public PercentageDiscount(double percent) {
        if (percent < 0) percent = 0;
        if (percent > 100) percent = 100;
        this.percent = percent;
    }

    @Override
    public double apply(double total) {
        return total * (1.0 - (percent / 100.0));
    }

    @Override
    public String getName() {
        return "PercentageDiscount(" + percent + "%)";
    }
}
