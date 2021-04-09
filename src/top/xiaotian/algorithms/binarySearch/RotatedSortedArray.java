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

    // 153. 寻找旋转排序数组中的最小值
    public int findMin(int[] nums) {
        /**
         *    例： 1 2 3 4 5
         * 旋转1： 1 2 3 4 5   左 < 中 < 右
         * 旋转2: 3 4 5 1 2   左 < 中 > 右
         * 旋转3: 4 5 1 2 3   左 > 中 < 右
         * 注意：旋转点会到达首位，旋转点前一个会到达末尾，而原数组递增，那么就表明首位一定大于末尾
         *
         * 中 < 右，最小值在左，收缩右边界
         * 中 > 右，最小值在右，收缩左边界
         *
         * m 更接近左，它采用floor除法，奇数个时位于偏左，偶数个时位于正中间
         * 所以存在 l <= m, m < r 的关系
         *
         * 一个元素输入，直接返回
         * 两个以上元素输入，最终结果都只会是 num[l]==nums[m]  nums[r],  l==m==r-1
         *
         * nums[l]==nums[m]>nums[r],说明左大右小，执行l=m+1后，l和r重叠，l和r下标中都保存了最小值
         * nums[l]==nums[m]<nums[r]，说明左小右大，执行r=m后，l和r重叠，l和r下标中都保存了最小值
         */
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;// 中值>右值，中值肯定不是最小，可以放心跳过
            } else {// nums[m] < nums[r], 不会出现等于的情况
                r = m;// 中值<右值，有可能中值就是最小值，不可以跳过当前值
            }
        }
        return nums[l];
    }

    // 154. 寻找旋转排序数组中的最小值 II(原升序数组有重复元素)
    public int findMinII(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;// 中值>右值，中值肯定不是最小，可以放心跳过
            } else if (nums[m] < nums[r]) {
                r = m;// 中值<右值，有可能中值就是最小值，不可以跳过当前值
            } else {
                r--;// 因为原数组存在重复，中值=右值时，r左移并不会将最小值漏掉   同时也不会越界的风险（r>l>=0）
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};
        boolean res = new RotatedSortedArray().searchII(nums, 3);
        System.out.println(res);
    }
}
