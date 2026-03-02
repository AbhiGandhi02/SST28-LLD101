package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * TODO (student):
 *  1) Make it a proper lazy, thread-safe singleton (private ctor)
 *  2) Block reflection-based multiple construction
 *  3) Preserve singleton on serialization (readResolve)
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    // ─── STEP 1: Private constructor + reflection guard ───
    private MetricsRegistry() {
        // If someone tries to create a second instance via reflection, block it
        if (Holder.INSTANCE != null) {
            throw new RuntimeException("Use getInstance() — reflection attack blocked!");
        }
    }

    // ─── STEP 2: Thread-safe lazy init via static holder ───
    // The JVM guarantees this inner class is loaded only when getInstance() is
    // first called.
    // Class loading is thread-safe, so no synchronized/volatile needed!
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // ─── STEP 3: Serialization protection ───
    // When Java deserializes, it creates a NEW object. readResolve() tells Java:
    // "Don't use that new object, use the existing singleton instead."
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    // ─── Business methods (unchanged) ───

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
