package top.xiaotian.algorithms.tree.binary_search_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import top.xiaotian.util.TreeNode;

/**
 * 501. 二叉搜索树中的众数
 *
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 *
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 *
 * 假定 BST 满足如下定义：
 *
 * 结点左子树中所含节点的值 小于等于 当前节点的值 结点右子树中所含节点的值 大于等于 当前节点的值 左子树和右子树都是二叉搜索树
 *
 * @author lichuangbo
 * @date 2022/9/23
 */
public class FindMode {

  private Map<Integer, Integer> freqMap;

  // 当做二叉树来看，先统计频率，拿到最大的，再从频率数组中找和最大频率一样的返回
  public int[] findMode(TreeNode root) {
    freqMap = new HashMap<>();
    if (root == null) {
      return new int[0];
    }

    help(root);
    List<Map.Entry<Integer, Integer>> mapList = freqMap.entrySet().stream()
        .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
        .collect(Collectors.toList());
    // 拿到最大频率
    int maxFreq = mapList.get(0).getValue();
    List<Integer> resList = new ArrayList<>();
    // 把频率最高的加入 resList
    for (int i = 0; i < mapList.size(); i++) {
      if (mapList.get(i).getValue().equals(maxFreq)) {
        resList.add(mapList.get(i).getKey());
      } else {
        break;
      }
    }
    return resList.stream().mapToInt(Integer::intValue).toArray();
  }

  private void help(TreeNode root) {
    if (root == null) {
      return;
    }
    freqMap.put(root.val, freqMap.getOrDefault(root.val, 0) + 1);
    help(root.left);
    help(root.right);
  }

  List<Integer> resList;
  int maxCount;
  int count;
  TreeNode pre;

  public int[] findMode2(TreeNode root) {
    resList = new ArrayList<>();
    help2(root);
    return resList.stream().mapToInt(Integer::intValue).toArray();
  }

  public void help2(TreeNode root) {
    if (root == null) {
      return;
    }
    help2(root.left);

    // 计数
    if (pre == null || root.val != pre.val) {
      count = 1;
    } else {
      count++;
    }
    // 更新结果以及maxCount
    if (count > maxCount) {
      resList.clear();
      resList.add(root.val);
      maxCount = count;
    } else if (count == maxCount) {
      resList.add(root.val);
    }
    pre = root;

    help2(root.right);
  }
}
