package Cache;

@FunctionalInterface
public interface EvictionPolicyFactory {
    EvictionPolicy<String> create();
}
