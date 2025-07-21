package top.xiaotian.algorithms.dp.sub_sequence;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class MaxLenOfRepeatedSubArray {
    public int findLength(int[] nums1, int[] nums2) {
        // dp[i][j]表示以nums1[i]结尾和以nums2[j]结尾的的最长重复子数组长度
        // 状态转移方程：如果nums1[i]==nums2[j]时，dp[i][j]的结果等于从以nums1[i-1]结尾和以nums2[j-1]结尾的最长长度+1
        int len1 = nums1.length, len2 = nums2.length;
        int[][] dp = new int[len1][len2];
        int res = 0;
        // 初始化: 第一行dp[0][j],数组1的首元素和数组2的元素只要有相等的就将该位置赋值为1
        for (int j = 0; j < len2; j++) {
            if (nums1[0] == nums2[j]) {
                // 因为在状态推演时候，忽略了第一行第一列，所以在初始化时候就要把res记录下来
                res = 1;
                dp[0][j] = 1;
            }
        }
        // 初始化: 第一列dp[i][0],数组2的首元素和数组1的元素只要有相等的就将该位置赋值为1
        for (int i = 0; i < len1; i++) {
            if (nums1[i] == nums2[0]) {
                res = 1;
                dp[i][0] = 1;
            }
        }
        // 状态推演
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    public int findLength2(int[] nums1, int[] nums2) {
        // 冗余空间，减少额外的初始化操作
        // dp[i][j]表示以nums1[i-1]结尾和以nums2[j-1]结尾的的最长重复子数组长度
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int res = 0;
        // dp[i][0]和dp[0][j]都变得没有意义了，只要dp[0][0]=0就不影响状态推演
        /**
         * nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
         * [[0, 0, 0, 0, 0, 0],
         *  [0, 0, 0, 1, 0, 0],
         *  [0, 0, 1, 0, 0, 0],
         *  [0, 1, 0, 0, 0, 0],
         *  [0, 0, 2, 0, 0, 0],
         *  [0, 0, 0, 3, 0, 0]]
         */
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * 滚动数组，状态压缩
     * 时间   O(len1*len2)
     * 空间   O(len2)
     */
    public int findLength3(int[] nums1, int[] nums2) {
        // dp[i][j]都是由dp[i - 1][j - 1]推出。压缩为一维数组，也就是dp[j]都是由dp[j - 1]推出
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] dp = new int[len2 + 1];
        int res = 0;
        for (int i = 1; i <= len1; i++) {
            // 从后向前遍历，这样避免重复覆盖
            for (int j = len2; j >= 1; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    res = Math.max(res, dp[j]);
                } else {// 滚动数组，不相等时需要手动重置为0
                    dp[j] = 0;
                }
            }
        }
        return res;
    }
}
