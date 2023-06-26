package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 最近公共祖先问题
 */
public class LowestCommonAncestor {
  /**
   * 235. 二叉搜索树的最近公共祖先
   * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
   * <p>
   * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
   * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
   * <p>
   * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
   */
  // 方法语义：在以root为根节点的二叉搜索树中寻找p和q的最近公共祖先，并返回
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }

    if (root.val < p.val && root.val < q.val) {// p、q节点都位于根的右子树，在右子树中寻找
      return lowestCommonAncestor(root.right, p, q);
    } else if (root.val > p.val && root.val > q.val) {// p、q节点都位于根的左子树，在左子树中寻找
      return lowestCommonAncestor(root.left, p, q);
    } else {
      return root;
    }
  }

  /**
   * 236. 二叉树的最近公共祖先
   * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
   *
   * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，\
   * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
   */
  // 方法语义：在以root为根节点的二叉树中寻找p和q的最近公共祖先，并返回
  public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
    // 探索到null递归终止
    if (root == null) {
      return null;
    }
    // 寻找到p或者q，返回给上层
    if (root == p || root == q) {
      return root;
    }

    // 后序遍历，模拟从下向上的顺序
    // 在左子树中寻找最近公共祖先
    TreeNode left = lowestCommonAncestorII(root.left, p, q);
    // 在右子树中寻找最近公共祖先
    TreeNode right = lowestCommonAncestorII(root.right, p, q);
    // 1.p, q其中有一个在root的右子树中,，此时返回的right指向的是p或q    2.p, q两节点都在root的右子树中，此时返回的right就是一个最近公共祖先
    /**
     *       3
     *     /   \
     *    5     1
     *  /  \   /  \
     * 6   2  0   8
     * case1: p=2,q=0,对以5为根节点的子树而言，left=null right=2  ans=2
     * case2: p=0,q=8,对以3为根节点的树而言，left=null, right=1 ans=1
     */
    if (left == null) {
      return right;
    } else if (right == null) {// 同上
      return left;
    } else {// p和q分布于root的两侧，说明root就是p, q的最近公共祖先
      return root;
    }
  }
}
