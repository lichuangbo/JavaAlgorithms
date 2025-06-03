package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 * <p>
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/4
 */
public class ThreeSum {
    /**
     * 对撞指针
     * 时间 排序O(nlogn)+遍历、指针对撞O(n2)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 排完序后，i作为固定的三元组第一个不可能大于0
            if (nums[i] > 0) {
                continue;
            }
            /**
             * 去重逻辑的思考
             * 以下两种方式是有很大区别的，第一种方式是和前一个元素比较，第二种方式是和后一个元素比较
             * 在case[0, 0, 0]种，如果使用方式2，直接就跳过了一个答案；而使用方式1，第一次进入会拿到一组解，在第二次会跳过重复元素，达到了目的
             * 方式2适用于结果中不能有重复的元素
             * if (i > 0 && nums[i] == nums[i - 1]) {
             *     continue;
             * }
             * if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
             *     continue;
             * }
             */
            // 对a去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            // left和right下标对应的元素代表了三元组的第二个和第三个元素，不可能相等
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    resList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 对b去重
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // 对c去重
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return resList;
    }
}
