public interface Transport {
    void deliver(Notification n, String formattedBody);
}
