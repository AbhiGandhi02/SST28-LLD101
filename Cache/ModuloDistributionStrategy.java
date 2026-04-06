package Cache;

public class ModuloDistributionStrategy implements DistributionStrategy {

    @Override
    public int getNodeIndex(String key, int totalNodes) {
        if (totalNodes <= 0) {
            throw new IllegalArgumentException("totalNodes must be > 0");
        }
        return Math.abs(key.hashCode()) % totalNodes;
    }
}
