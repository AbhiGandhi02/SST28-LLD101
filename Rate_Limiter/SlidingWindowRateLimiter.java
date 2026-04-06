package Rate_Limiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter implements RateLimiter {

    private final RateLimitConfig config;
    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Long>> requestLogs;

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
        this.requestLogs = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long windowStart = now - config.getWindowSizeMs();

        ConcurrentLinkedDeque<Long> timestamps = requestLogs.computeIfAbsent(
                key, k -> new ConcurrentLinkedDeque<>());

        while (!timestamps.isEmpty() && timestamps.peekFirst() <= windowStart) {
            timestamps.pollFirst();
        }

        if (timestamps.size() < config.getMaxRequests()) {
            timestamps.addLast(now);
            return true;
        }

        return false;
    }
}
