package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1. 两数之和
 * 提示
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * <p>
 * <p>
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/4
 */
public class TwoSum {

    // 两次遍历
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(target - nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i) {
                return new int[]{i, map.get(nums[i])};
            }
        }
        return new int[0];
    }

    /**
     * 查找表法(在遍历的同时记录一些关键信息，以空间换时间)
     * 时间O(n)
     * 空间O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 每遍历一个元素,看另一个数是否存在，存在的话说明找到了一组解
            int ano = target - nums[i];
            // 为什么可以不加 && map.get(target - nums[i]) != i ，因为put操作放在之后，存在的话也是下一轮了
            if (map.containsKey(ano)) {
                return new int[]{i, map.get(ano)};
            }
            // 不存在，就记录它的索引信息
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     */
    public int[] twoSumII(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(target - num)) {
                return new int[]{num, target - num};
            } else {
                set.add(num);
            }
        }
        return new int[0];
    }

    // 在剑指Offer57题中，nums数组是递增数组，所以可以使用对撞指针解决
    public int[] twoSumII2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int[] res = new int[2];
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return res;
    }
}
