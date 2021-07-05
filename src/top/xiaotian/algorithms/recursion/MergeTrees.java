package top.xiaotian.algorithms.recursion;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 617. 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 */
public class MergeTrees {
    /**
     * DFS
     * 时间：O(min(m,n)),m和n分别为两树节点数目
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        TreeNode left = mergeTrees(root1.left, root2.left);
        TreeNode right = mergeTrees(root1.right, root2.right);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     *  BFS
     *  时间: O(m+n)
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        //如果 t1和t2中，只要有一个是null，函数就直接返回
        if(t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(t1);
        deque.addLast(t2);
        while(!deque.isEmpty()) {
            TreeNode r1 = deque.removeFirst();
            TreeNode r2 = deque.removeFirst();
            r1.val += r2.val;
            //如果r1和r2的左子树都不为空，就放到队列中
            //如果r1的左子树为空，就把r2的左子树挂到r1的左子树上
            if(r1.left != null && r2.left != null){
                deque.addLast(r1.left);
                deque.addLast(r2.left);
            } else if(r1.left == null) {
                r1.left = r2.left;
            }
            //对于右子树也是一样的
            if(r1.right != null && r2.right != null) {
                deque.addLast(r1.right);
                deque.addLast(r2.right);
            } else if(r1.right == null) {
                r1.right = r2.right;
            }
        }
        return t1;
    }
}
