package Cache;

public class Main {

    public static void main(String[] args) {

        Database database = new InMemoryDatabase();
        database.put("user:1", "Alice");
        database.put("user:2", "Bob");
        database.put("user:3", "Charlie");
        database.put("user:4", "Diana");
        database.put("user:5", "Eve");

        DistributedCache cache = new DistributedCache(
                3,                                   
                2,                                   
                new ModuloDistributionStrategy(),     
                () -> new LRUEvictionPolicy<>(),      
                database                              
        );

        System.out.println("\n========== SCENARIO 1: put + get (cache hit) ==========");
        cache.put("product:A", "Laptop");
        System.out.println();
        String val = cache.get("product:A");
        System.out.println("Got: " + val);

        System.out.println("\n========== SCENARIO 2: get (cache miss → DB fetch) ==========");
        val = cache.get("user:1");
        System.out.println("Got: " + val);

        System.out.println("\n========== SCENARIO 3: get (key not in cache or DB) ==========");
        val = cache.get("user:999");
        System.out.println("Got: " + val);

        System.out.println("\n========== SCENARIO 4: Eviction demo ==========");
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        cache.put("key5", "value5");
        cache.put("key6", "value6");
        cache.put("key7", "value7");

        System.out.println("\n========== SCENARIO 5: Re-fetch evicted key ==========");
        System.out.println("Fetching key1 (may have been evicted):");
        val = cache.get("key1");
        System.out.println("Got: " + val);
    }
}
