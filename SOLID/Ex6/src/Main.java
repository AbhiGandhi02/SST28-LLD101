public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new NotificationSender(
                new NoOpValidator(),
                new TruncatingFormatter(40),
                new EmailTransport(audit));

        NotificationSender sms = new NotificationSender(
                new NoOpValidator(),
                new IdentityFormatter(),
                new SmsTransport(audit));

        NotificationSender wa = new NotificationSender(
                new WaPhoneNumberValidator(),
                new IdentityFormatter(),
                new WhatsAppTransport(audit));

        email.send(n);
        sms.send(n);
        try {
            wa.send(n);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
