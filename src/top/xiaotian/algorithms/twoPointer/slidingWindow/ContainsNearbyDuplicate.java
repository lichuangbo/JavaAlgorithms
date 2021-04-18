package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 存在重复元素问题
 * 217 219 220
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/13 16:59
 * @Description: 描述:
 */
public class ContainsNearbyDuplicate {

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     *
     *
     * 示例 1:
     *
     * 输入: [1,2,3,1]
     * 输出: true
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        // 时间O(nlogn)
        /*Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;*/

        // 时间O(n)
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    /**
     * 219. 存在重复元素 II
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
     * 示例 1:
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // O(n2)
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
//                    return true;
//                }
//            }
//        }

        // 时间O(n)
        // 维护一个大小为k+1的滑动窗口[l...l+k]（这样保证了i和j距离最多为k），对于一个新的元素，如果能够在窗口内找到两个元素是相等的，就返回true
        // 窗口的移动规则：如果新元素和窗口内的某元素相同，返回结果；否则将新的元素纳入到窗口中[l+1...l+k+1]（如果窗口已经满了，去除滑动窗口的首元素[l+1...l+k]）
        Set<Integer> set = new HashSet<>();// 窗口固定，维护他的大小为k+1即可
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);// 移除滑动窗口最左侧值，举例：i=k时，左侧元素下标为0，右侧元素下标为k，窗口大小为k+1
            }
        }
        return false;
    }

    /**
     * 220. 存在重复元素 III
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
     * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
     * 如果存在则返回 true，不存在返回 false。
     * 示例 1:
     * 输入: nums = [1,2,3,1], k = 3, t = 0
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 维护一个大小为k+1的滑动窗口[l...l+k]（这样保证了i和j距离最多为k），对于一个新的元素，如果能够满足abs(v-x)<=t，就返回true(V的范围在[v-t...v+t])
        // 窗口的移动规则：如果新元素和窗口内的某元素相同，返回结果；否则将新的元素纳入到窗口中[l+1...l+k+1]（如果窗口已经满了，去除滑动窗口的首元素[l+1...l+k]）
        TreeSet<Long> set = new TreeSet<>();// 窗口固定，维护他的大小为k+1即可
        for (int i = 0; i < nums.length; i++) {
            // ceiling(x)返回大于等于x的最小数
            if (set.ceiling((long)nums[i] - t) != null && set.ceiling((long)nums[i] - t) <= (long)nums[i] + t) {
                return true;
            }

            set.add((long)nums[i]);
            if (set.size() == k + 1) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }
}
