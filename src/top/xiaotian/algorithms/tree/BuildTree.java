package top.xiaotian.algorithms.tree;


import top.xiaotian.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class BuildTree {
  private final Map<Integer, Integer> memMap = new HashMap<>();

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || inorder == null) {
      return null;
    }

    for (int i = 0; i < inorder.length; i++) {
      memMap.put(inorder[i], i);
    }
    return help(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  private TreeNode help(int[] preorder, int preSt, int preEn, int[] inorder, int inSt, int inEn) {
    if (preSt > preEn || inSt > inEn) {
      return null;
    }

    int rootVal = preorder[preSt];
    TreeNode root = new TreeNode(rootVal);
    int i = memMap.get(rootVal);
    // i - 1 - inSt是以root为根的左子树的长度，preSt + 1 + (i - 1 - inSt)是前序遍历结果的右边界
    root.left = help(preorder, preSt + 1, preSt + 1 + (i - 1 - inSt), inorder, inSt, i - 1);
    root.right = help(preorder, preSt + 1 + (i - 1 - inSt) + 1, preEn, inorder, i + 1, inEn);
    return root;
  }
}
