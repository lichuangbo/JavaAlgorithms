package top.xiaotian.algorithms.tree;

import java.util.HashMap;
import java.util.Map;
import top.xiaotian.util.TreeNode;

/**
 * 从遍历序列中构建二叉树
 *
 * @author lichuangbo
 * @date 2022/9/22
 */
public class ConstructBinaryTree {

  /**
   * 106. 从中序与后序遍历序列构造二叉树
   *
   * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
   * inorder 左根右     [9,3,15,20,7] postorder 左右根   [9,15,7,20,3] 提示:
   *
   * 1 <= inorder.length <= 3000 postorder.length == inorder.length -3000 <= inorder[i],
   * postorder[i] <= 3000 inorder 和 postorder 都由 不同 的值组成 postorder 中每一个值都在 inorder 中 inorder
   * 保证是树的中序遍历 postorder 保证是树的后序遍历
   */
  private Map<Integer, Integer> memoMap;

  public TreeNode buildTreeI(int[] inorder, int[] postorder) {
    memoMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      memoMap.put(inorder[i], i);
    }
//    return helpI(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    return helpI2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
  }

  private TreeNode helpI(int[] inorder, int inSt, int inEn, int[] postorder, int postSt,
      int postEn) {
    if (inSt > inEn && postSt > postEn) {
      return null;
    }
    TreeNode root = new TreeNode(postorder[postEn]);
    for (int i = inSt; i <= inEn; i++) {
      // i是root节点下标
      if (inorder[i] == postorder[postEn]) {
        // i-inSt-1是根节点的左子树在inorder数组中的长度（偏移）
        root.left = helpI(inorder, inSt, i - 1, postorder, postSt, postSt + (i - inSt - 1));
        root.right = helpI(inorder, i + 1, inEn, postorder, postSt + (i - inSt - 1) + 1,
            postEn - 1);
        break;
      }
    }

    return root;
  }

  private TreeNode helpI2(int[] inorder, int inSt, int inEn, int[] postorder, int postSt,
      int postEn) {
    if (inSt > inEn && postSt > postEn) {
      return null;
    }
    TreeNode root = new TreeNode(postorder[postEn]);
    Integer i = memoMap.get(postorder[postEn]);
    root.left = helpI2(inorder, inSt, i - 1, postorder, postSt, postSt + (i - inSt - 1));
    root.right = helpI2(inorder, i + 1, inEn, postorder, postSt + (i - inSt - 1) + 1, postEn - 1);

    return root;
  }

  /**
   * 105. 从前序与中序遍历序列构造二叉树
   *
   * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。 提示:
   *
   * preorder 根左右 [3,9,20,15,7]  inorder 左根右 [9,3,15,20,7]
   *
   * 1 <= preorder.length <= 3000 inorder.length == preorder.length -3000 <= preorder[i], inorder[i]
   * <= 3000 preorder 和 inorder 均 无重复 元素 inorder 均出现在 preorder preorder 保证 为二叉树的前序遍历序列 inorder 保证
   * 为二叉树的中序遍历序列
   */
  public TreeNode buildTreeII(int[] preorder, int[] inorder) {
    memoMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      memoMap.put(inorder[i], i);
    }
    return helpII(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  private TreeNode helpII(int[] preorder, int preSt, int preEn, int[] inorder, int inSt, int inEn) {
    if (preSt > preEn && inSt > inEn) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preSt]);
    Integer rootIndex = memoMap.get(preorder[preSt]);
    // rootIndex-inSt-1是根节点的左子树在inorder的偏移
    root.left = helpII(preorder, preSt + 1, preSt + 1 + (rootIndex - inSt - 1), inorder, inSt,
        rootIndex - 1);
    root.right = helpII(preorder, preSt + 1 + (rootIndex - inSt - 1) + 1, preEn, inorder, rootIndex + 1,
        inEn);
    return root;
  }
}
