public class TruncatingFormatter implements MessageFormatter {
    private final int maxLength;

    public TruncatingFormatter(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String formatBody(Notification n) {
        String body = n.body;
        if (body != null && body.length() > maxLength) {
            return body.substring(0, maxLength);
        }
        return body;
    }
}
