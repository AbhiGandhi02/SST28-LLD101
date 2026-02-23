public class WhatsAppTransport implements Transport {
    private final AuditLog audit;

    public WhatsAppTransport(AuditLog audit) {
        this.audit = audit;
    }

    @Override
    public void deliver(Notification n, String formattedBody) {
        System.out.println("WA -> to=" + n.phone + " body=" + formattedBody);
        audit.add("wa sent");
    }
}
