package top.xiaotian.dataStructures.map;

/**
 * 基于链表的映射实现（无序映射）
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class LinkedListMap<K, V> implements Map<K, V> {
    private class Node {
        public K k;
        public V v;
        public Node next;

        public Node() {
            this(null, null, null);
        }

        public Node(K k) {
            this(k, null, null);
        }

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public String toString() {
            return k.toString() + ":" + v.toString();
        }
    }
    private final Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 没有考虑哈希冲突问题，在查找键时也只是按照键值来检索
     * 理论上并不算是哈希表
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            // 在链表头加入元素
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // 执行更新操作
            node.v = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.k.equals(key)) {
                break;
            }
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.v;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) == null;
    }

    private Node getNode(K key) {
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.k.equals(key)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.v;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.v = newValue;
    }
}
