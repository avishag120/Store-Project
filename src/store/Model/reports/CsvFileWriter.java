/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.reports;
import java.io.PrintWriter;

public class CsvFileWriter implements ReportWriter{
    private final String fileName;

    public CsvFileWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(String content) {
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
