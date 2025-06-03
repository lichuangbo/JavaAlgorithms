package top.xiaotian.algorithms.tree;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 填充每个节点的下一个右侧节点指针 问题
 */
public class Connect {

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：root = [1,2,3,4,5,6,7]
     * 输出：[1,#,2,3,#,4,5,6,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
     * 示例 2:
     *
     * 输入：root = []
     * 输出：[]
     *
     *
     * 提示：
     *
     * 树中节点的数量在 [0, 212 - 1] 范围内
     * -1000 <= node.val <= 1000
     *
     *
     * 进阶：
     *
     * 你只能使用常量级额外空间。
     * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     */

    /**
     * 层序遍历
     * 不是最优解，没有利用上题目中的完美二叉树提示，没用next引用
     * 时间 O(n)  每个节点访问一次 pollFirst peekFirst offerLast方法都是O(1)
     * 空间 O(n)  申请的队列存储的每层节点数，完美二叉树每层节点数=2^(h-1)，最底层节点数大致相当于n/2
     */
    public Node connect(Node root) {
        if (root == null) return root;

        Deque<Node> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node tmpNode = queue.pollFirst();
                // 最后一个节点，如果仍然执行peekFirst，并不会返回一个null，而是下一层节点的第一个，这样就造成了连接错乱
                if (i != size - 1) {
                    tmpNode.next = queue.peekFirst();
                }
                if (tmpNode.left != null) queue.offerLast(tmpNode.left);
                if (tmpNode.right != null) queue.offerLast(tmpNode.right);
            }
        }
        return root;
    }

    public Node connect1(Node root) {
        if (root == null) return root;

        Deque<Node> deque = new ArrayDeque<>();
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node tmp = deque.pollFirst();
                // prev不为null，说明当前节点不是该层第一个节点，需要连接
                if (prev != null) {
                    prev.next = tmp;
                }
                // 移动prev
                prev = tmp;
                if (tmp.left != null) deque.offerLast(tmp.left);
                if (tmp.right != null) deque.offerLast(tmp.right);
            }
        }
        return root;
    }

    /**
     * 时间 O(n)
     * 空间 O(1)
     */
    public Node connect2(Node root) {
        if (root == null) return root;

        // 始终指向每层的首节点
        Node levelStart = root;
        while (levelStart != null) {
            // curr从首节点开始遍历每一层
            Node curr = levelStart;
            while (curr != null) {
                // 直接修改左右子节点
                if (curr.left != null) {
                    curr.left.next = curr.right;
                }
                if (curr.right != null && curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            levelStart = levelStart.left;
        }
        return root;
    }

    // 递归
    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        // 当前层要做的事情：修改左右子节点
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        connect3(root.left);
        connect3(root.right);
        return root;
    }

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     * 给定一个二叉树：
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL 。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [1,2,3,4,5,null,7]
     * 输出：[1,#,2,3,#,4,5,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
     * 示例 2：
     *
     * 输入：root = []
     * 输出：[]
     *
     *
     * 提示：
     *
     * 树中的节点数在范围 [0, 6000] 内
     * -100 <= Node.val <= 100
     * 进阶：
     *
     * 你只能使用常量级额外空间。
     * 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。
     */
    // 和上一题不同的是，它是二叉树，116的递归思路走不通，因为不确定上层节点是否有左子节点；但是层序遍历是一样适用的

    /**
     * 时间 O(n)
     * 空间 O(1) 每层创建一个head，常量级
     */
    public Node connectII(Node root) {
        if (root == null)   return root;

        // levelStart仍然指向每层首节点
        Node levelStart = root;
        while (levelStart != null) {
            // 第一层next关系已经处理好了
            // 之后的每层创建一个虚拟头结点，由tail引用指向链表末尾
            Node dummyHead = new Node(-1);
            Node tail = dummyHead;
            Node curr = levelStart;
            while (curr != null) {
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }
                curr = curr.next;
            }
            levelStart = dummyHead.next;
        }
        return root;
    }
}
