public class MaxLengthConstraint implements ExportConstraint {
    private final int maxLength;
    private final String errorMessage;

    public MaxLengthConstraint(int maxLength, String errorMessage) {
        this.maxLength = maxLength;
        this.errorMessage = errorMessage;
    }

    @Override
    public void validate(ExportRequest req) {
        if (req != null && req.body != null && req.body.length() > maxLength) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
