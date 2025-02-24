package top.xiaotian.algorithms.binarySearch;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;// [l...r]
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {// [mid+1...r]
                l = mid + 1;
            } else {// [l...mid-1]
                r = mid - 1;
            }
        }
        /**
         * 1, 3, 5, 6   2
         *         (l,r)0,3
         * (m)1 3>2     0,0
         *    0 1<2     1,0
         * break 1
         *
         * 1, 3, 5, 6   7
         *              0,3
         *    1 3<7     1,3
         *    2 5<7     3,3
         *    3 6<7     4,3
         * break 4
         */
        return l;
    }

    public int searchInsert2(int[] nums, int target) {
        int l = 0;
        int r = nums.length;// [l...r)
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {// [mid+1...r)
                l = mid + 1;
            } else {// [l...mid)
                r = mid;
            }
        }
        return l;
    }
}
