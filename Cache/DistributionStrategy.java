package Cache;

public interface DistributionStrategy {

    int getNodeIndex(String key, int totalNodes);
}
