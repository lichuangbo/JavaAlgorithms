package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/4
 */
public class ThreeSum {
    /**
     * 对撞指针
     * 时间 排序O(nlogn)+遍历、指针对撞O(n2)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            // 排完序后，i作为固定的三数第一个不可能大于0
            if (nums[i] > 0) {
                break;
            }
            // (-4,-1,-1,0,1,2)处理重复，nums[i-1]已经纳入考虑范围了，就不要nums[i]了
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            // 根据题目要求，l和r不能指向为同一个元素
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    r--;
                    while (r > l && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (sum < 0) {
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                } else {
                    r--;
                    // 去重操作，nums[r+1]已经纳入考虑了，nums[r]就不要了
                    while (r > l && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}
