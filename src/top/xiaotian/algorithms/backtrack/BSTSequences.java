package top.xiaotian.algorithms.backtrack;

import top.xiaotian.util.TreeNode;

import java.util.*;

/**
 * 面试题 04.09. 二叉搜索树序列
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 * <p>
 * 给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [2,1,3]
 * 输出: [[2,1,3],[2,3,1]]
 * 解释: 数组 [2,1,3]、[2,3,1] 均可以通过从左向右遍历元素插入树中形成以下二叉搜索树
 *   2
 *  / \
 * 1   3
 */
public class BSTSequences {

  private List<List<Integer>> resList;

  public List<List<Integer>> BSTSequences(TreeNode root) {
    resList = new ArrayList<>();
    if (root == null) {
      resList.add(new ArrayList<>());
      return resList;
    }

    List<TreeNode> currLevel = new ArrayList<>();
    currLevel.add(root);
    help(currLevel, new LinkedList<>());
    return resList;
  }

  /**
   *     2
   *    / \
   *   1   3
   *        \
   *         4
   *   限定条件: 任一节点root的子节点必须在该节点之后访问
   *   举例：1和3因为与2相连，所以(1,3)必须在2后面，同理4和3相连所以4必须放在3后面，同时4与2具有传递性，因此4也必须在2后面。而4与1无直接或传递关系，所以可以乱序
   *   假如我们遍历到节点3位置，下一层可以遍历的是兄弟节点1以及当前节点的子节点4
   */
  public void help(List<TreeNode> curLevel, LinkedList<Integer> currList) {
    // 当前集合没有需要遍历的元素，说明遍历到底,可以作为一个结果集
    if (curLevel.size() == 0) {
      resList.add(new LinkedList<>(currList));
      return;
    }

    List<TreeNode> nextLevel = new ArrayList<>(curLevel);
    for (TreeNode t : curLevel) {
      currList.add(t.val);

      // 除了当前节点，其余节点下一层都可以遍历（相当于去掉3本身）
      nextLevel.remove(t);
      // 当前节点的子节点也可以遍历（相当于加入4）
      if (t.left != null) {
        nextLevel.add(t.left);
      }
      if (t.right != null) {
        nextLevel.add(t.right);
      }
      help(nextLevel, currList);
      if (t.left != null) {
        nextLevel.remove(t.left);
      }
      if (t.right != null) {
        nextLevel.remove(t.right);
      }
      nextLevel.add(t);

      currList.removeLast();
    }
  }
}
