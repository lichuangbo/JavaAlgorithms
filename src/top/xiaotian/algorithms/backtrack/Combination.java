package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合问题
 */
public class Combination {
    private List<List<Integer>> res;

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        if (k <= 0 || k > n) {
            return res;
        }

        generateCombinations(n, k, 1, new LinkedList<>());
        return res;
    }

    // 求解C(n, k), 当前已经找到的组合存储在curr中，需要从start开始搜索新的元素
    private void generateCombinations(int n, int k, int start, LinkedList<Integer> curr) {
        // 已经凑齐k个数，说明找到了一组解
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        /**
         * 剪枝：现在i遍历区间[start...n]，但是当i=n时，在循环里继续递归还会+1，此时已经没有空位可选了
         * curr中存储着已经找到的元素，说明还有k-curr.size()个空位可以排列，i取多少，可以确保[i...n]区间中至少有k-curr.size个元素
         * 2个空位：k-curr.size=2,i应该取n-1       1个空位：k-curr.size=1,i应该取n
         * 观察：i最多取 n - (k - curr.size) + 1
         */
//        for (int i = start; i <= n; i++) {
        for (int i = start; i <= n - (k - curr.size()) + 1; i++) {
            curr.addLast(i);
            generateCombinations(n, k, i + 1, curr);
            curr.removeLast();
        }
    }

    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }

        Arrays.sort(candidates);
        generateSum(candidates, target, 0, new LinkedList<>());
        return res;
    }

    private void generateSum(int[] candidates, int target, int start, LinkedList<Integer> curr) {
        if (target <= 0) {
            if (target == 0) {
                res.add(new ArrayList<>(curr));
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝：如果相减之差已经小于0了，之后再递归只会更小
            if (target - candidates[i] < 0) {
                break;
            }

            curr.addLast(candidates[i]);
            generateSum(candidates, target - candidates[i], i, curr);
            curr.removeLast();
        }
    }

    // 40. 组合总和 II(元素只能使用一次, 但是给的candidates是有重复元素的)
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
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            // 基于39的剪枝去重：当减去的是同一个数时，会产生重复，应该跳过
            /*
            在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
            必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
            第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
             */
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            curr.addLast(candidates[i]);
            generateSumII(candidates, target - candidates[i], i + 1, curr);
            curr.removeLast();
        }
    }
}
