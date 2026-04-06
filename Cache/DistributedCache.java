package Cache;

import java.util.ArrayList;
import java.util.List;

public class DistributedCache {

    private final List<CacheNode> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database database;
    private final int numberOfNodes;

    /**
     * Creates a distributed cache.
     *
     * @param numberOfNodes        
     * @param capacityPerNode      
     * @param distributionStrategy 
     * @param evictionPolicyFactory 
     * @param database             
     */
    public DistributedCache(int numberOfNodes,
                            int capacityPerNode,
                            DistributionStrategy distributionStrategy,
                            EvictionPolicyFactory evictionPolicyFactory,
                            Database database) {
        this.numberOfNodes = numberOfNodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        this.nodes = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            EvictionPolicy<String> evictionPolicy = evictionPolicyFactory.create();
            nodes.add(new CacheNode("Node-" + i, capacityPerNode, evictionPolicy));
        }

        System.out.println("Distributed Cache initialized with " + numberOfNodes
                + " nodes, capacity " + capacityPerNode + " each.");
    }

    public String get(String key) {
        CacheNode node = getResponsibleNode(key);
        String value = node.get(key);

        if (value == null) {
            value = database.get(key);
            if (value != null) {
                node.put(key, value);
                System.out.println("  → Loaded from DB into " + node.getNodeId());
            } else {
                System.out.println("  → Key not found in DB either.");
            }
        }

        return value;
    }

    public void put(String key, String value) {
        CacheNode node = getResponsibleNode(key);
        node.put(key, value);
        database.put(key, value);
        System.out.println("  → Also written to DB.");
    }

    private CacheNode getResponsibleNode(String key) {
        int index = distributionStrategy.getNodeIndex(key, numberOfNodes);
        return nodes.get(index);
    }
}
