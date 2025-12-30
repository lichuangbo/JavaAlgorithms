package top.xiaotian.algorithms.backtrack.combination;

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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        help(candidates, target, 0, 0, resList, new LinkedList<>());
        return resList;
    }

    private void help(int[] candidates, int target, int index, int sum, List<List<Integer>> resList, LinkedList<Integer> currList) {
        // 递归入口剪枝: 检查当前递归层的初始状态（即上一层选择后的 sum）是否已经无效。如果无效，则立即返回
        if (sum > target) {
            return;
        }
        if (sum == target) {
            resList.add(new ArrayList<>(currList));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // 循环内提前判断: 提前判断选择这个数字后的状态是否已经无效。如果无效，则根本不选择它，直接跳过本次循环，尝试下一个数字
            if (sum + candidates[i] > target) {
                continue;
            }
            currList.add(candidates[i]);
            sum += candidates[i];
            help(candidates, target, i, sum, resList, currList);
            currList.removeLast();
            sum -= candidates[i];
        }
    }
}
