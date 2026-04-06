package Cache;

import java.util.LinkedHashMap;
import java.util.Iterator;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private final LinkedHashMap<K, Boolean> accessOrder;

    public LRUEvictionPolicy() {
        this.accessOrder = new LinkedHashMap<>(16, 0.75f, true);
    }

    @Override
    public void keyAccessed(K key) {
        accessOrder.put(key, Boolean.TRUE);
    }

    @Override
    public K evict() {
        Iterator<K> iterator = accessOrder.keySet().iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        K evictKey = iterator.next();
        iterator.remove();
        return evictKey;
    }

    @Override
    public void remove(K key) {
        accessOrder.remove(key);
    }
}
