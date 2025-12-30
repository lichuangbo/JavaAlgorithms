package top.xiaotian.algorithms.backtrack.combination;

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

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(candidates);
        help(candidates, target, 0, resList, new LinkedList<>());
        return resList;
    }

    /**
     * 去重逻辑
     * @see top.xiaotian.algorithms.twoPointer.collisionPointer.ThreeSum
     * 剪枝逻辑
     * @see top.xiaotian.algorithms.backtrack.combination.CombinationSum
     */
    private void help(int[] candidates, int target, int start, List<List<Integer>> resList, LinkedList<Integer> curr) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            resList.add(new LinkedList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target - candidates[i] < 0) {
                continue;
            }
            curr.add(candidates[i]);
            help(candidates, target - candidates[i], i + 1, resList, curr);
            curr.removeLast();
        }
    }
}
