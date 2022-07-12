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
    // 左闭右闭区间
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > target) {
        right = mid - 1;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        // 目标值等于数组中某一个元素  return mid;
        return mid;
      }
    }
    /**
     * 分别处理如下三种情况
     *  目标值在数组所有元素之前  [0, -1]
     *  目标值插入数组中的位置 [left, right]，right + 1
     *  目标值在数组所有元素之后的情况 [left, right]， right + 1
     */
    return right + 1;
  }

  public int searchInsert2(int[] nums, int target) {
    // 左闭右开区间
    int left = 0, right = nums.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > target) {
        right = mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        // 目标值等于数组中某一个元素  return mid;
        return mid;
      }
    }
    /**
     * 分别处理如下三种情况
     *  目标值在数组所有元素之前  [0, 0)
     *  目标值插入数组中的位置 [left, right)，right
     *  目标值在数组所有元素之后的情况 [left, right)， right
     */
    return right;
  }
}
