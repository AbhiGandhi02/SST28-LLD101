package Rate_Limiter;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        RateLimitConfig config = new RateLimitConfig(5, 1, TimeUnit.MINUTES);

        System.out.println("========== DEMO 1: Fixed Window Counter ==========\n");
        runDemo(RateLimiterFactory.create(RateLimiterFactory.Algorithm.FIXED_WINDOW, config));

        System.out.println("\n========== DEMO 2: Sliding Window Counter ==========\n");
        runDemo(RateLimiterFactory.create(RateLimiterFactory.Algorithm.SLIDING_WINDOW, config));
    }

    private static void runDemo(RateLimiter rateLimiter) {
        ExternalResource externalResource = new PaidTranslationService();
        ExternalResourceGateway gateway = new ExternalResourceGateway(rateLimiter, externalResource);
        InternalService service = new InternalService(gateway);

        String tenant = "T1";

        System.out.println("--- Requests that do NOT need external call ---");
        System.out.println("Result: " + service.handleRequest(tenant, "local_data_1"));
        System.out.println("Result: " + service.handleRequest(tenant, "local_data_2"));
        System.out.println();

        System.out.println("--- Requests that DO need external call (limit=5/min) ---");
        for (int i = 1; i <= 7; i++) {
            String result = service.handleRequest(tenant, "translate:hello_" + i);
            System.out.println("Request " + i + " → " + result);
            System.out.println();
        }
    }
}
