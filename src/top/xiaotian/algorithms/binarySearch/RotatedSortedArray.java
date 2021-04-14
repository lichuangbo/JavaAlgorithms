package top.xiaotian.algorithms.binarySearch;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * @author lichuangbo
 * @version 1.0
 * @created 2021/4/7
 */
public class RotatedSortedArray {
    public int search(int[] nums, int target) {
        /**
         * 4 5 6 7 0 1 2   target=0
         * l=0, r=6, m=3   nums[m]=7, 包含自己的左右区间始终有一个是有序的，进一步可以判断m元素位于左右那个区间中（注意：不包含的话，两侧都是有序的）
         * target0不在有序数组[4,5,6]中,只能去右区间查找，更新l=m+1=4
         * l=4, r=6, m=5   nums[m]=1, 程序判断为m位于左区间
         * target0在有序数据[0]中，更新r=m-1
         * l=4, r=4, m=4   nums[m]=target,返回对应索引
         */
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[l] <= nums[m]) {// m位于左半段
                // target元素在[l...m)上升区间中，更新r区间(注意：这里不能写为[l...m-1]，这在边界条件m=0中，会数组越界的)
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {// m位于右半段
                // target元素在(m...r]上升区间中，更新l区间（同样，[m+1...r]也会导致数组越界）
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }


    // 81. 搜索旋转排序数组 II
    public boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return true;
            }
            // 一种情况，l==m==r==0，此时l更新为1，r更新为-1，会出现数组越界（l也有可能更新为length）
            if (nums[l] == nums[m] && nums[m] == nums[r]) {
                l++;r--;
                continue;
            }
            if (nums[l] <= nums[m]) {// m位于左半段
                // target元素在[l...m)上升区间中，更新r区间(注意：这里不能写为[l...m-1]，这在边界条件m=0中，会数组越界的)
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {// m位于右半段
                // target元素在(m...r]上升区间中，更新l区间（同样，[m+1...r]也会导致数组越界）
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};
        boolean res = new RotatedSortedArray().searchII(nums, 3);
        System.out.println(res);
    }
}
