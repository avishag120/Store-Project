/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.reports;

public abstract class Report {
    protected ReportWriter writer;

    public Report(ReportWriter writer) {
        this.writer = writer;
    }

    public abstract void generate();
}
