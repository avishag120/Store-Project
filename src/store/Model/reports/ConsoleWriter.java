/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.reports;

public class ConsoleWriter implements ReportWriter{
    @Override
    public void write(String content) {
        System.out.println(content);
    }
}
