package top.xiaotian.algorithms.tree.binary_search_tree;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 * <p>
 * <p>
 * <p>
 * 参考以下这颗二叉搜索树：
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * 示例 1：
 * <p>
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: [1,3,2,6,5]
 * 输出: true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 数组长度 <= 1000
 */
public class VerifyPostOrder {

  public boolean verifyPostorder(int[] postorder) {
    return help(postorder, 0, postorder.length - 1);
  }

  // 判断postorder在[postSt, postEn]区间内表示的二插搜索树后序遍历顺序是否正确
  boolean help(int[] postorder, int postSt, int postEn) {
    // 仅有一个节点，判定为后序遍历顺序正确
    if (postSt >= postEn) {
      return true;
    }

    // 从postSt开始遍历，寻找第一个大于根节点的下标p
    int rootVal = postorder[postEn];
    int p = postSt;
    while (postorder[p] < rootVal) {
      p++;
    }
    // 右子树所有节点都必须大于根节点
    for (int k = p; k <= postEn - 1; k++) {
      if (postorder[k] < rootVal) {
        return false;
      }
    }
    return help(postorder, postSt, p - 1) && help(postorder, p, postEn - 1);
  }

}
