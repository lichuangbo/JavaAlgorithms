package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集问题
 */
public class SubSet {
    private List<List<Integer>> res;

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        help(nums, 0, new LinkedList<>());
        return res;
    }

    private void help(int[] nums, int index, LinkedList<Integer> curr) {
        // 每次在递归入口都加入结果集
        res.add(new ArrayList<>(curr));

        for (int i = index; i < nums.length; i++) {
            curr.addLast(nums[i]);
            help(nums, i + 1, curr);
            curr.removeLast();
        }
    }

    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        help2(nums, 0, new LinkedList<>());
        return res;
    }

    private void help2(int[] nums, int index, LinkedList<Integer> curr) {
        res.add(new ArrayList<>(curr));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1] ) {
                continue;
            }

            curr.addLast(nums[i]);
            help2(nums, i + 1, curr);
            curr.removeLast();
        }
    }

}
