package top.xiaotian.dataStructures.trie.practice;

import java.util.TreeMap;

/**
 * 键值映射
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class MapSum {

    private static class Node {
        // isWord可以省略，可以使用value=0表示为单词结尾的语义（对sum求和是没有影响的）
        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private final Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.next.get(c) == null) {
                curr.next.put(c, new Node());
            }
            curr = curr.next.get(c);
        }
        curr.value = val;
    }

    public int sum(String prefix) {
        // 定位到前缀最后一个字符所在的位置
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // 不包含当前前缀，直接返回0
            if (curr.next.get(c) == null) {
                return 0;
            }
            curr = curr.next.get(c);
        }
        return sum(curr);
    }

    // 遍历curr这个节点的所有子树，将值相加返回
    private int sum(Node node) {
        // 递归终止条件：可以不写，如果size为0，就不会走下边的循环
        if (node.next.size() == 0) {
            return node.value;
        }

        int res = node.value;
        for (char c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }
}
