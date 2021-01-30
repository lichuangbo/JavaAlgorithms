package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/30
 */
public class SubSet {
    private List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        generateSubSet2(nums, 0, new LinkedList<>());
        return res;
    }

    private void generateSubSet(int[] nums, int start, LinkedList<Integer> curr) {
        System.out.println("start: " + start + ", " + curr);
        if (start == nums.length) {
            res.add(new ArrayList<>(curr));
            System.out.println("get " + curr);
            return;
        }

        // 选该值，向后递归查找
        curr.addLast(nums[start]);
        System.out.println("curr1: " + curr);
        generateSubSet(nums, start + 1, curr);
        // 不选该值，向后递归查看
        curr.removeLast();
        System.out.println("curr2: " + curr);
        generateSubSet(nums, start + 1, curr);
    }

    private void generateSubSet2(int[] nums, int start, LinkedList<Integer> curr) {
        // 每次在递归入口都加入结果集
        res.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            curr.addLast(nums[i]);
            generateSubSet2(nums, i + 1, curr);
            curr.removeLast();
        }
    }

    // 90. 子集 II(不可重复)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        generateSubSetII2(nums, 0, new LinkedList<>());
        return res;
    }

    private void generateSubSetII(int[] nums, int start, LinkedList<Integer> curr) {
        if (start == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.addLast(nums[start]);
        generateSubSetII(nums, start + 1, curr);
        curr.removeLast();
        // 去重
        while (start + 1 < nums.length && nums[start + 1] == nums[start]) {
            start++;
        }
        generateSubSetII(nums, start + 1, curr);
    }

    private void generateSubSetII2(int[] nums, int start, LinkedList<Integer> curr) {
        res.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1] ) {
                continue;
            }

            curr.addLast(nums[i]);
            generateSubSetII2(nums, i + 1, curr);
            curr.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        SubSet subSet = new SubSet();
        List<List<Integer>> res = subSet.subsetsWithDup(nums);
        System.out.println(res);
    }
}
