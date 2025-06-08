package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 *
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 * @author lichuangbo
 * @date 2022/9/26
 */
public class InsertIntoBST {

    /**
     * 递归
     * 时间   O(logn) 数据处理规模减半，最坏情况下退化成链表，时间退化为O(n)
     * 空间   O(h)    树高相关，最差O(n)
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    // 迭代
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        // 使用parent来记录需要操作的节点，p引用用来寻找它应该在的节点位置
        /**
         *          4                          4
         *        /   \                      /   \
         *       2     7        =>          2     7 <-parent所指向位置
         *     /   \                      /  \   /
         *    1     3                    1    3 p
         */
        TreeNode parent = root, p = root;
        while (p != null) {
            parent = p;
            if (p.val < val) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        // 拿到了父节点，但是不清楚应该往左子树放还是右子树放，需要比较一次
        if (parent.val < val) {
            parent.right = new TreeNode(val);
        } else if (parent.val > val) {
            parent.left = new TreeNode(val);
        }
        return root;
    }
}
