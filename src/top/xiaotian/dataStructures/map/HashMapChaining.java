package top.xiaotian.dataStructures.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 链式地址法解决哈希冲突
 * @param <K>
 * @param <V>
 */
public class HashMapChaining<K, V> implements Map<K, V> {

    private int size; // 键值对数量
    private int capacity; // 哈希表容量
    private double loadThreshold; // 触发扩容的负载因子阈值
    private int extendRatio; // 扩容倍数
    private List<List<Pair<K, V>>> buckets; // 桶数组

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThreshold = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int hashFunc(K key) {
        return key.hashCode() % capacity;
    }

    private double loadFactor() {
        return (double) size / capacity;
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
        // 当负载因子超过阈值时，执行扩容
        if (loadFactor() > loadThreshold) {
            extend();
        }
        int index = hashFunc(key);
        List<Pair<K, V>> bucket = buckets.get(index);
        // 遍历桶，若遇到指定 key ，则更新对应 val 并返回
        for (Pair<K, V> pair : bucket) {
            if (pair.getK().equals(key)) {
                pair.setV(value);
                return;
            }
        }
        // 若无该 key ，则将键值对添加至尾部
        Pair<K, V> pair = new Pair<>(key, value);
        bucket.add(pair);
        size++;
    }

    private void extend() {
        // 暂存原哈希表
        List<List<Pair<K, V>>> bucketsTmp = buckets;
        // 初始化扩容后的新哈希表
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        // 更新键值对数量为0，因为扩容操作中迁移数据会继续自增size
        size = 0;
        // 将键值对从原哈希表搬运至新哈希表
        for (List<Pair<K, V>> bucket : bucketsTmp) {
            for (Pair<K, V> pair : bucket) {
                add(pair.getK(), pair.getV());
            }
        }
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        List<Pair<K, V>> bucket = buckets.get(index);
        // 遍历桶，从中删除键值对
        for (Pair<K, V> pair : bucket) {
            if (pair.getK().equals(key)) {
                bucket.remove(pair);
                size--;
                return pair.getV();
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        int index = hashFunc(key);
        List<Pair<K, V>> bucket = buckets.get(index);
        // 遍历桶，若遇到指定 key ，表示存在
        for (Pair<K, V> pair : bucket) {
            if (pair.getK().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);
        List<Pair<K, V>> bucket = buckets.get(index);
        // 遍历桶，若找到 key ，则返回对应 val
        for (Pair<K, V> pair : bucket) {
            if (pair.getK().equals(key)) {
                return pair.getV();
            }
        }
        // 若未找到 key ，则返回 null
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        int index = hashFunc(key);
        List<Pair<K, V>> bucket = buckets.get(index);
        // 遍历桶，若找到 key ，则返回对应 val
        for (Pair<K, V> pair : bucket) {
            if (pair.getK().equals(key)) {
                pair.setV(newValue);
                return;
            }
        }
    }
}
