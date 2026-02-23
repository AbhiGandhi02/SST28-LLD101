public class NoOpValidator implements DeliveryValidator {
    @Override
    public void validate(Notification n) {
        // no-op, always valid
    }
}
