package top.xiaotian.algorithms.dp;

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
 */
public class MaxLenOfRepeatedSubArray {
  public int findLength(int[] nums1, int[] nums2) {
    // dp[i][j]表示以nums1[i]结尾和以nums2[j]结尾的的最长重复子数组长度
    int len1 = nums1.length;
    int len2 = nums2.length;
    int[][] dp = new int[len1][len2];
    int res = 0;
    // 初始化
    for (int j = 0; j < len2; j++) {
      if (nums1[0] == nums2[j]) {
        res = 1;
        dp[0][j] = 1;
      }
    }
    for (int i = 0; i < len1; i++) {
      if (nums1[i] == nums2[0]) {
        res = 1;
        dp[i][0] = 1;
      }
    }
    for (int i = 1; i < len1; i++) {
      for (int j = 1; j < len2; j++) {
        if (nums1[i] == nums2[j]) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
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

  // 滚动数组，状态压缩
  public int findLength3(int[] nums1, int[] nums2) {
    // dp[i][j]都是由dp[i - 1][j - 1]推出。压缩为一维数组，也就是dp[j]都是由dp[j - 1]推出
    // 相当于可以把上一层dp[i - 1][j]拷贝到下一层dp[i][j]来继续用
    int len1 = nums1.length;
    int len2 = nums2.length;
    int[] dp = new int[len2 + 1];
    int res = 0;
    for (int i = 1; i <= len1; i++) {
      // 从后向前遍历，这样避免重复覆盖
      for (int j = len2; j >= 1; j--) {
        if (nums1[i - 1] == nums2[j - 1]) {
          dp[j] = dp[j - 1] + 1;
        } else {// 滚动数组，不同时需要手动重置为0
          dp[j] = 0;
        }
        res = Math.max(res, dp[j]);
      }
    }
    return res;
  }
}
