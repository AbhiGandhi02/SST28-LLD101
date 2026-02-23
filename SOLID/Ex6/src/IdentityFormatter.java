public class IdentityFormatter implements MessageFormatter {
    @Override
    public String formatBody(Notification n) {
        return n.body;
    }
}
