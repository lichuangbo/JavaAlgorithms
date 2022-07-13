package top.xiaotian.dataStructures.map.practice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class LRUCache {

  private class Node {

    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  // 双向链表：为了支持在O(1)时间复杂度内删除节点
  private class DoubleList {
    // 虚拟头结点
    Node dummyHead;
    // 虚拟尾结点
    Node dummyTail;
    int size;

    public DoubleList() {
      dummyHead = new Node(0, 0);
      dummyTail = new Node(0, 0);
      dummyHead.next = dummyTail;
      dummyTail.prev = dummyHead;
      size = 0;
    }

    // 链表尾部是最近使用的，头部是最久未使用的
    public void addLast(Node node) {
      // node节点与尾结点连接
      node.prev = dummyTail.prev;
      dummyTail.prev.next = node;

      // node节点与虚拟尾结点连接
      node.next = dummyTail;
      dummyTail.prev = node;

      size++;
    }

    public void remove(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;

      size--;
    }

    public Node removeFirst() {
      if (dummyHead.next == dummyTail) {
        return null;
      }
      Node first = dummyHead.next;
      remove(first);
      return first;
    }

    public int size() {
      return size;
    }
  }

  // 使用map是为了O(1)时间内查找到删除的节点
  private Map<Integer, Node> map;
  private DoubleList cache;
  private int cap;

  public LRUCache(int capacity) {
    this.cap = capacity;
    map = new HashMap<>();
    cache = new DoubleList();
  }

  /* 将某个 key 提升为最近使用的 */
  private void makeRecently(int key) {
    Node x = map.get(key);
    cache.remove(x);
    cache.addLast(x);
  }

  /* 添加最近使用的元素 */
  private void addRecently(int key, int val) {
    Node x = new Node(key, val);
    cache.addLast(x);
    map.put(key, x);
  }

  /* 删除某一个 key */
  private void deleteKey(int key) {
    Node x = map.get(key);
    cache.remove(x);
    map.remove(key);
  }

  /* 删除最久未使用的元素 */
  private void removeLeastRecently() {
    Node deletedNode = cache.removeFirst();
    int deletedKey = deletedNode.key;
    map.remove(deletedKey);
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    // 将该数据提升为最近使用的
    makeRecently(key);
    return map.get(key).value;
  }

  public void put(int key, int value) {
    // 存在该键，不能建档的将key提升为最近使用的，因为他的值可能已经变化了，需要先删除原数据，后添加新数据到最近使用
    if (map.containsKey(key)) {
      deleteKey(key);
      addRecently(key, value);
      return;
    }

    // 现有容量已经达到最大容量，先删除最久未使用的元素，后添加新数据到最近使用；没达到最大容量，直接添加
    if (cap == cache.size()) {
      removeLeastRecently();
    }
    addRecently(key, value);
  }

  //////////////////////////////////////////////////
  class LRUCache1 {

    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache1(int capacity) {
      this.cap = capacity;
    }

    public int get(int key) {
      if (!cache.containsKey(key)) {
        return -1;
      }
      // 将 key 变为最近使用
      makeRecently(key);
      return cache.get(key);
    }

    public void put(int key, int val) {
      if (cache.containsKey(key)) {
        // 修改 key 的值
        cache.put(key, val);
        // 将 key 变为最近使用
        makeRecently(key);
        return;
      }

      if (cache.size() >= this.cap) {
        // 链表头部就是最久未使用的 key
        int oldestKey = cache.keySet().iterator().next();
        cache.remove(oldestKey);
      }
      // 将新的 key 添加链表尾部
      cache.put(key, val);
    }

    private void makeRecently(int key) {
      int val = cache.get(key);
      // 删除 key，重新插入到队尾
      cache.remove(key);
      cache.put(key, val);
    }
  }
}
