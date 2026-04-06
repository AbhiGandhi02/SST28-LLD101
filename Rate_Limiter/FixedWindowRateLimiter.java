package Rate_Limiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {

    private final RateLimitConfig config;
    private final ConcurrentHashMap<String, WindowData> windows;

    public FixedWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
        this.windows = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long currentWindowStart = (now / config.getWindowSizeMs()) * config.getWindowSizeMs();

        windows.compute(key, (k, existing) -> {
            if (existing == null || existing.windowStart != currentWindowStart) {
                return new WindowData(currentWindowStart, 0);
            }
            return existing;
        });

        WindowData window = windows.get(key);

        if (window.counter.incrementAndGet() <= config.getMaxRequests()) {
            return true;
        }

        window.counter.decrementAndGet();
        return false;
    }

    private static class WindowData {
        final long windowStart;
        final AtomicInteger counter;

        WindowData(long windowStart, int initialCount) {
            this.windowStart = windowStart;
            this.counter = new AtomicInteger(initialCount);
        }
    }
}
