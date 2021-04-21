package top.xiaotian.dataStructures.trie;

import java.util.TreeMap;

/**
 * Trie字典树实现
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class Trie {
    private static class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private final Node root;
    private int size;

    public Trie() {
        // trie根节点不存储任何元素
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 以curr节点为根的 所有子节点中（map的键值）不包含 c字符，就插入一个子节点（以c为键，新节点为值）
            if (curr.next.get(c) == null) {
                curr.next.put(c, new Node());
            }
            // 更新curr（不管是新创建的还是原来就有，都更新），好去处理下一个字符
            curr = curr.next.get(c);
        }
        if (!curr.isWord) {// 这里的判断是为了维护size字段（只有他是新的单词结尾，才自增）
            // 不一定就是叶子节点，这里标记他为一个单词的结尾：panda 和 pan
            curr.isWord = true;
            size++;
        }
    }

    // 添加--递归写法
    public void addR(String word) {
        addR(root, word, 0);
    }

    private void addR(Node node, String word, int index) {
        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size++;
            }
            return;
        }

        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        addR(node.next.get(c), word, index + 1);
    }

    public boolean contains(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.next.get(c) == null) {
                return false;
            }
            curr = curr.next.get(c);
        }
        return curr.isWord;
    }

    // 搜索--递归写法
    public boolean containsR(String word) {
        return containsR(root, word, 0);
    }

    private boolean containsR(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        return containsR(node.next.get(c), word, index + 1);
    }

    // 查询是否在trie中有单词以prefix为前缀的单词
    public boolean isPrefix (String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.next.get(c) == null) {
                return false;
            }
            curr = curr.next.get(c);
        }
        return true;
    }

    // 前缀搜索--递归写法
    public boolean isPrefixR(String prefix) {
        return isPrefixR(root, prefix, 0);
    }

    private boolean isPrefixR(Node node, String prefix, int index) {
        if (index == prefix.length()) {
            return true;
        }

        char c = prefix.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        return isPrefixR(node.next.get(c), prefix, index + 1);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("panda");
        trie.add("pan");
    }
}
