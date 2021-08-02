package top.xiaotian.dataStructures.tree.practice;

import top.xiaotian.util.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *
 * 示例 1：
 *
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 示例 2：
 *
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 */
public class ConvertBST {

  private int num;

  /**
   * 反向中序遍历
   * 时间O(n)
   * 空间O(nlogn),递归树高度
   */
  public TreeNode convertBST(TreeNode root) {
    if (root == null) {
      return null;
    }
    convertBST(root.right);
    root.val = root.val + num;
    // 树节点元素累加
    num = root.val;
    convertBST(root.left);
    return root;
  }
}
