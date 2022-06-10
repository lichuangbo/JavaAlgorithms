package top.xiaotian.algorithms.binarySearch;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 *
 * 统计一个数字在排序数组中出现的次数。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8 输出: 2
 *
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6 输出: 0
 *
 * 提示：
 *
 * 0 <= nums.length <= 105 -109 <= nums[i] <= 109 nums 是一个非递减数组 -109 <= target <= 109
 */
public class Search {

  public int search(int[] nums, int target) {
    int first = getFirst(nums, target, 0, nums.length - 1);
    int last = getLast(nums, target, 0, nums.length - 1);
    if (first == -1 && last == -1) {
      return 0;
    } else if (first == -1) {
      return 1;
    } else if (last == -1) {
      return 1;
    } else {
      return last - first + 1;
    }
  }

  private int getFirst(int[] nums, int target, int left, int right) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < target) {
        left = mid + 1;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        // 下标已经是首元素了，一定是第一次出现  前一个值不等于目标值，，也是第一次出现
        if (mid == 0 || nums[mid - 1] != target) {
          return mid;
        } else {// 不是第一个，需要向前查找
          right = mid - 1;
        }
      }
    }
    return -1;
  }

  private int getLast(int[] nums, int target, int left, int right) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < target) {
        left = mid + 1;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        // 下标已经是尾元素了，一定是最后一次出现  后一个值不等于目标值，也是最后一次出现
        if (mid == nums.length - 1 || nums[mid + 1] != target) {
          return mid;
        } else {// 不是第一个，需要向后查找
          left = mid + 1;
        }
      }
    }
    return -1;
  }
}
