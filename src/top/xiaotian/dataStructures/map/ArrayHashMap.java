package top.xiaotian.dataStructures.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组实现哈希表（未解决哈希冲突）
 * @param <K>
 * @param <V>
 */
public class ArrayHashMap<K, V> implements Map<K, V> {

    private final List<Pair<K, V>> buckets;
    private static final int CAPACITY = 100;
    private int size;

    public ArrayHashMap() {
        buckets = new ArrayList<>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            buckets.add(null);
        }
        size = 0;
    }

    private int hashFunc(K key) {
        return key.hashCode() % CAPACITY;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        int index = hashFunc(key);
        buckets.add(index, pair);
        size++;
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        Pair<K, V> kvPair = buckets.get(index);
        buckets.set(index, null);
        size--;
        return kvPair.getV();
    }

    @Override
    public boolean contains(K key) {
        int index = hashFunc(key);
        return buckets.get(index) != null;
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);
        return buckets.get(index).getV();
    }

    @Override
    public void set(K key, V newValue) {
        int index = hashFunc(key);
        buckets.get(index).setV(newValue);
    }
}
