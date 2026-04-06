package Rate_Limiter;

public class InternalService {

    private final ExternalResourceGateway gateway;

    public InternalService(ExternalResourceGateway gateway) {
        this.gateway = gateway;
    }

    public String handleRequest(String customerId, String data) {
        boolean needsExternalCall = shouldCallExternalResource(data);

        if (!needsExternalCall) {
            System.out.println("[Service] No external call needed for: " + data);
            return "processed_locally:" + data;
        }

        System.out.println("[Service] External call needed for: " + data);
        try {
            String result = gateway.execute(customerId, data);
            return "processed_externally:" + result;
        } catch (RateLimitedException e) {
            System.out.println("[Service] DENIED — " + e.getMessage());
            return "rate_limited";
        }
    }

    private boolean shouldCallExternalResource(String data) {
        return data.startsWith("translate:");
    }
}
