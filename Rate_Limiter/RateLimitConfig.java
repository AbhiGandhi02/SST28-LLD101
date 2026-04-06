package Rate_Limiter;

import java.util.concurrent.TimeUnit;

public class RateLimitConfig {

    private final int maxRequests;
    private final long windowSizeMs;

    public RateLimitConfig(int maxRequests, long windowSize, TimeUnit timeUnit) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = timeUnit.toMillis(windowSize);
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMs() {
        return windowSizeMs;
    }
}
