package top.xiaotian.algorithms.backtrack.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
public class SubSetII {
    private List<List<Integer>> res;

    /**
     * 时间 O(n*2^n) n个元素会产生2^n个子集，resList.add复制操作是O(n)
     * 空间 O(n)递归栈
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        help(nums, 0, new LinkedList<>());
        return res;
    }

    /**
     * 去重逻辑
     * @see top.xiaotian.algorithms.twoPointer.collisionPointer.ThreeSum
     */
    private void help(int[] nums, int index, LinkedList<Integer> curr) {
        res.add(new ArrayList<>(curr));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1] ) {
                continue;
            }

            curr.addLast(nums[i]);
            help(nums, i + 1, curr);
            curr.removeLast();
        }
    }

}
