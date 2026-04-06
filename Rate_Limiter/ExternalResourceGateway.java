package Rate_Limiter;

public class ExternalResourceGateway {

    private final RateLimiter rateLimiter;
    private final ExternalResource externalResource;

    public ExternalResourceGateway(RateLimiter rateLimiter, ExternalResource externalResource) {
        this.rateLimiter = rateLimiter;
        this.externalResource = externalResource;
    }

    public String execute(String rateLimitKey, String request) {
        if (!rateLimiter.allowRequest(rateLimitKey)) {
            throw new RateLimitedException(rateLimitKey);
        }
        return externalResource.call(request);
    }
}
