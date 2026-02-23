public class SmsTransport implements Transport {
    private final AuditLog audit;

    public SmsTransport(AuditLog audit) {
        this.audit = audit;
    }

    @Override
    public void deliver(Notification n, String formattedBody) {
        System.out.println("SMS -> to=" + n.phone + " body=" + formattedBody);
        audit.add("sms sent");
    }
}
