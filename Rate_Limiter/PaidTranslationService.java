package Rate_Limiter;

public class PaidTranslationService implements ExternalResource {

    @Override
    public String call(String request) {
        System.out.println("  [ExternalAPI] Translating: " + request);
        return "translated_" + request;
    }
}
