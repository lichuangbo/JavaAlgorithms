package top.xiaotian.dataStructures.map;

/**
 * 基于二分搜索树的映射实现（有序映射）
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        public K k;
        public V v;
        public Node left;
        public Node right;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int size;

    public BSTMap() {
        root = null;
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

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K k, V v) {
        if (node == null) {
            size++;
            return new Node(k, v);
        }

        if (k.compareTo(node.k) < 0) {
            node.left = add(node.left, k, v);
        } else if (k.compareTo(node.k) > 0){
            node.right = add(node.right, k, v);
        } else {
            node.v = v;
        }
        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.k) == 0) {
            return node;
        } else if (key.compareTo(node.k) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.v;
        }
        return null;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, K k) {
        if (node == null) {
            return null;
        }

        if (k.compareTo(node.k) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.k) > 0) {
            node.right = remove(node.right, k);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right) ;
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        Node node = getNode(root, key);
        return node != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.v;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null) {
            node.v = newValue;
        } else {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }
}
