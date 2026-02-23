public class NotificationSender {
    private final DeliveryValidator validator;
    private final MessageFormatter formatter;
    private final Transport transport;

    public NotificationSender(DeliveryValidator validator, MessageFormatter formatter, Transport transport) {
        this.validator = validator;
        this.formatter = formatter;
        this.transport = transport;
    }

    public void send(Notification n) {
        validator.validate(n);
        String formattedBody = formatter.formatBody(n);
        transport.deliver(n, formattedBody);
    }
}
