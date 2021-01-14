package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/13 16:59
 * @Description: 描述: 217
 */
public class ContainsNearbyDuplicate {
    /**
     * 时间O(n)
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
//                    return true;
//                }
//            }
//        }


        // 维护一个大小为k+1的滑动窗口[l...l+k]（这样保证了i和j距离最多为k），对于一个新的元素，如果能够在窗口内找到两个元素是相等的，就返回true
        // 窗口的移动规则：如果新元素和窗口内的某元素相同，返回结果；否则将新的元素纳入到窗口中[l+1...l+k+1]（如果窗口已经满了，去除滑动窗口的首元素[l+1...l+k]）
        Set<Integer> set = new HashSet<>();// 窗口固定，维护他的大小为k+1即可
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
