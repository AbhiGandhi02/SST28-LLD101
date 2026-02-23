public class Exporter {
    private final FormatEncoder encoder;
    private final ExportConstraint constraint;

    public Exporter(FormatEncoder encoder) {
        this.encoder = encoder;
        this.constraint = req -> {
        }; // no-op default constraint
    }

    public Exporter(FormatEncoder encoder, ExportConstraint constraint) {
        this.encoder = encoder;
        this.constraint = constraint;
    }

    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("ExportRequest cannot be null");
        }
        if (req.title == null || req.body == null) {
            throw new IllegalArgumentException("ExportRequest fields cannot be null");
        }

        constraint.validate(req);
        return encoder.encode(req);
    }
}
