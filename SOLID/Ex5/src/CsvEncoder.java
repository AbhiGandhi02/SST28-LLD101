import java.nio.charset.StandardCharsets;

public class CsvEncoder implements FormatEncoder {
    @Override
    public ExportResult encode(ExportRequest req) {
        String body = req.body;
        // Proper CSV quoting instead of replacing newlines (fixes the data loss issue)
        if (body.contains(",") || body.contains("\n") || body.contains("\"")) {
            body = "\"" + body.replace("\"", "\"\"") + "\"";
        }

        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
