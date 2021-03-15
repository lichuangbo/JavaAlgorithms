package top.xiaotian.dataStructures.map.practice;

import java.util.LinkedList;

/**
 * 706. 设计哈希映射
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/15
 */
class MyHashMap {
    private class Node {
        private int key;
        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Node>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Node node = getNode(key);
        if (node != null) {
            node.setValue(value);
            return;
        }
        data[h].offerLast(new Node(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Node node = getNode(key);
        if (node != null) {
            data[h].remove(node);
        }
    }

    private Node getNode(int key) {
        int h = hash(key);
        for (Object o : data[h]) {
            Node node = (Node) o;
            if (node.getKey() == key) {
                return node;
            }
        }
        return null;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

