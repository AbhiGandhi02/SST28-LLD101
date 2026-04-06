package Rate_Limiter;

public interface RateLimiter {

    boolean allowRequest(String key);
}
