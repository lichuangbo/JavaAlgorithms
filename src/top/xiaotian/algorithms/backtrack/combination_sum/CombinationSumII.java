package top.xiaotian.algorithms.backtrack.combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class CombinationSumII {

  private List<List<Integer>> res;

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    res = new ArrayList<>();
    if (candidates == null || candidates.length == 0) {
      return res;
    }

    Arrays.sort(candidates);
    generateSumII(candidates, target, 0, new LinkedList<>());
    return res;
  }

  private void generateSumII(int[] candidates, int target, int start, LinkedList<Integer> curr) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      // 剪枝操作：和39一样
      if (target - candidates[i] < 0) {
        continue;
      }
      // 放过第一次出现的，跳过之后重复的
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue;
      }

      curr.addLast(candidates[i]);
      generateSumII(candidates, target - candidates[i], i + 1, curr);
      curr.removeLast();
    }
  }
}
