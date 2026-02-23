public class EmailTransport implements Transport {
    private final AuditLog audit;

    public EmailTransport(AuditLog audit) {
        this.audit = audit;
    }

    @Override
    public void deliver(Notification n, String formattedBody) {
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + formattedBody);
        audit.add("email sent");
    }
}
