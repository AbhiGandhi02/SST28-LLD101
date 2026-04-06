package Cache;

public interface EvictionPolicy<K> {

    void keyAccessed(K key);

    K evict();

    void remove(K key);
}
