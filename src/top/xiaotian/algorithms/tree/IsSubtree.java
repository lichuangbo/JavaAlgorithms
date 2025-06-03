package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 572. 另一棵树的子树
 * 提示
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 */
public class IsSubtree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 空子树是任何树的子树
        if (subRoot == null)    return true;
        // 树是空的，除了空子树外不可能有匹配的
        if (root == null)   return false;
        // 先检查根节点和子树是否相同 再检查左右子树的
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.val == q.val;
    }
}
