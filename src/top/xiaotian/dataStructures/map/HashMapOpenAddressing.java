package top.xiaotian.dataStructures.map;

/**
 * 开放地址法解决哈希冲突
 *
 * @param <K>
 * @param <V>
 */
public class HashMapOpenAddressing<K, V> implements Map<K, V> {
    private int size; // 键值对数量
    private int capacity; // 哈希表容量
    private double loadThreshold; // 触发扩容的负载因子阈值
    private int extendRatio; // 扩容倍数
    private Object[] buckets; // 桶数组
    private Object TOMBSTONE; // 删除标记

    /* 构造方法 */
    public HashMapOpenAddressing() {
        size = 0;
        capacity = 4;
        loadThreshold = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new Pair[capacity];
        /**
         * 解决 开放寻址哈希表中直接删除元素导致的问题：删除元素会在数组内产生一个空桶，而当查询元素时，线性探测到该空桶就会返回，
         * 因此在该空桶之下的元素都无法再被访问到，程序可能误判这些元素不存在。
         * 使用TOMBSTONE标记删除的桶，在实际意义上和空桶一致，区别在于线性探测到 TOMBSTONE 时可以继续遍历
         */
        TOMBSTONE = new Pair<>(-1, -1);
    }

    /* 哈希函数 */
    private int hashFunc(K key) {
        return key.hashCode() % capacity;
    }

    /* 负载因子 */
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

    // 要么返回是一个没有哈希冲突的空桶，要么返回的是一个已删除的桶，再要么就是相同key的桶
    private int findBucket(K key) {
        int index = hashFunc(key);
        int firstTombstone = -1;
        // 线性探测，当遇到空桶时跳出
        while (buckets[index] != null) {
            // 若遇到 key ，返回对应的桶索引
            if (((Pair) buckets[index]).getK().equals(key)) {
                // 若之前遇到了删除标记，则将键值对移动至删除标记索引处
                if (firstTombstone != -1) {
                    buckets[firstTombstone] = buckets[index];
                    buckets[index] = TOMBSTONE;
                    return firstTombstone; // 返回移动后的桶索引
                }
                return index; // 返回桶索引
            }
            // 记录遇到的首个删除标记（其他元素会存储在这个下标上）
            if (firstTombstone == -1 && buckets[index] == TOMBSTONE) {
                firstTombstone = index;
            }
            // 计算桶索引，越过尾部则返回头部
            index = (index + 1) % capacity;
        }
        // 若 key 不存在，则返回添加点的索引
        return firstTombstone == -1 ? index : firstTombstone;
    }

    @Override
    public void add(K key, V value) {
        // 当负载因子超过阈值时，执行扩容
        if (loadFactor() > loadThreshold) {
            extend();
        }
        // 搜索 key 对应的桶索引
        int index = findBucket(key);
        // 若找到键值对，则覆盖 val 并返回（根据 findBucket 结果的语义，该下标的桶既不是空桶也不是已删除桶，就只能是key完全一样的桶的）
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            ((Pair) buckets[index]).setV(value);
            return;
        }
        // 若键值对不存在，则添加该键值对
        buckets[index] = new Pair<>(key, value);
        size++;
    }

    private void extend() {
        // 暂存原哈希表
        Pair[] bucketsTmp = (Pair[]) buckets;
        // 初始化扩容后的新哈希表
        capacity *= extendRatio;
        buckets = new Pair[capacity];
        size = 0;
        // 将键值对从原哈希表搬运至新哈希表
        for (Pair pair : bucketsTmp) {
            if (pair != null && pair != TOMBSTONE) {
                add((K) pair.getK(), (V) pair.getV());
            }
        }
    }

    @Override
    public V remove(K key) {
        // 搜索 key 对应的桶索引
        int index = findBucket(key);
        // 若找到键值对，则用删除标记覆盖它
        V deleteValue = null;
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            deleteValue = (V) ((Pair) buckets[index]).getV();
            buckets[index] = TOMBSTONE;
            size--;
        }
        // 没有找到键值对，无法删除
        return deleteValue;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        // 搜索 key 对应的桶索引
        int index = findBucket(key);
        // 若找到键值对，则返回对应 val
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            return (V) ((Pair) buckets[index]).getV();
        }
        // 若键值对不存在，则返回 null
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        // 搜索 key 对应的桶索引
        int index = findBucket(key);
        // 若找到键值对，则覆盖
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            ((Pair) buckets[index]).setV(newValue);
        }
    }
}
