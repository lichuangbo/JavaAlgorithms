package top.xiaotian.algorithms.binarySearch;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;// [l, r]
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] < target) {// [mid+1, r]
                l = mid + 1;
            } else if (nums[mid] > target) {// [l, mid-1]
                r = mid - 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    // 找到第一个后，线性向后遍历找最后一个
                    int index = mid;
                    for (int i = mid; i <= nums.length - 1; i++) {
                        if (nums[i] != target) {
                            break;
                        } else {
                            index = i;
                        }
                    }
                    return new int[]{mid, index};
                } else {
                    r = mid - 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int[] searchRange2(int[] nums, int target) {
        return new int[]{getFirst(nums, target), getLast(nums, target)};
    }

    public int getFirst(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;// [l...r]
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > target) {// [l...mid-1]
                r = mid - 1;
            } else if (nums[mid] < target) {// [mid+1...r]
                l = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {// 前一个值不等于目标值，说明找见了
                    return mid;
                } else {// 说明不是第一个，向前找
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public int getLast(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;// [l...r]
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > target) {// [l...mid-1]
                r = mid - 1;
            } else if (nums[mid] < target) {// [mid+1...r]
                l = mid + 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {// 后一个值不等于目标值，说明找见了
                    return mid;
                } else {// 说明不是最后一个，向后找
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}
