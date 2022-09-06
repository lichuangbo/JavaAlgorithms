package top.xiaotian.algorithms.backtrack;

import java.util.*;

/**
 * 491. 递增子序列
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * <p>
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 */
public class IncreasingSubsequences {
  private List<List<Integer>> res;

  public List<List<Integer>> findSubsequences(int[] nums) {
    res = new ArrayList<>();
    help(nums, 0, new LinkedList<>());
    return res;
  }

  private void help(int[] nums, int index, LinkedList<Integer> curr) {
    // 递增子序列中至少有两个元素
    if (curr.size() >= 2) {
      res.add(new ArrayList<>(curr));
    }

    Set<Integer> set = new HashSet<>();
    for (int i = index; i < nums.length; i++) {
      // 不满足递增特性的，不尝试直接跳过
      if (curr.size() > 0 && curr.getLast() > nums[i]) {
        continue;
      }
      // 使用set去除重复
      // 和子集问题去重不一样，这里是不可以排序的，case2排完序结果也变了
      if (set.contains(nums[i])) {
        continue;
      }
      set.add(nums[i]);

      curr.add(nums[i]);
      help(nums, i + 1, curr);
      curr.removeLast();
    }
  }
}
