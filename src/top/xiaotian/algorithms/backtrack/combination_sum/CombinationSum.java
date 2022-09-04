package top.xiaotian.algorithms.backtrack.combination_sum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 */
public class CombinationSum {

  private List<List<Integer>> res;

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    res = new ArrayList<>();
    if (candidates == null || candidates.length == 0) {
      return res;
    }

    generateSum(candidates, target, 0, new LinkedList<>());
    return res;
  }

  private void generateSum(int[] candidates, int target, int start, LinkedList<Integer> curr) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    // 当前元素选择过后，下一轮可以继续从当前元素选：所以在向下递归时，不是i+1,是i
    for (int i = start; i < candidates.length; i++) {
      // 剪枝：如果在target的基础上尝试下一层的节点，已经小于0了，那么这次递归是不必要的
      if (target - candidates[i] < 0) {
        continue;
      }

      curr.addLast(candidates[i]);
      generateSum(candidates, target - candidates[i], i, curr);
      curr.removeLast();
    }
  }
}
