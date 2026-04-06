package Rate_Limiter;

public class RateLimitedException extends RuntimeException {

    public RateLimitedException(String key) {
        super("Rate limit exceeded for key: " + key);
    }
}
