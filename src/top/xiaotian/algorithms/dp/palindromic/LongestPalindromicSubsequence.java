package top.xiaotian.algorithms.dp.palindromic;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 */
public class LongestPalindromicSubsequence {
  /**
   * 典型的区间dp问题：从小区间的回文状态进一步推导大区间的回文状态
   * 长度为len的回文串，一定是从len-1或者len-2的回文串中扩展来的
   */
  public int longestPalindromeSubseq(String s) {
    char[] chars = s.toCharArray();
    int len = chars.length;
    /**
     * dp[i][j]表示在chars[i, j]范围内的最长回文子序列长度
     * i在字符串开头处，j在字符串结尾处，
     * chars[i]==chars[j]: dp[i][j]=dp[i+1][j-1]+2   i和j都向中间移动（len是从len-2扩展来的）
     * chars[i]!=chars[j]: dp[i][j]=Math.max(dp[i][j-1], dp[i+1][j])  i移动，j不动 or i不动，j移动
     */
    int[][] dp = new int[len][len];
    // 初始化：当i与j相同，dp[i][j]一定是等于1的(只有一个字符的情况)；其他情况初始化为0
    for (int i = 0; i < len; i++) {
      dp[i][i] = 1;
    }
    /**
     * [[1, 2, 3, 3, 4],
     *  [0, 1, 2, 2, 3],
     *  [0, 0, 1, 1, 3],
     *  [0, 0, 0, 1, 1],
     *  [0, 0, 0, 0, 1]]
     */
    for (int i = len - 1; i >= 0; i--) {
      // 从dp定义来看，j一定是大于等于i的，也就是只需要遍历右上部分
      // 推演过程中当头尾两个字符相等时，有i+1，不能从反斜线上开始遍历，得从反斜线上方开始   这时候必须要初始化反斜线
      for (int j = i + 1; j < len; j++) {
        if (chars[i] == chars[j]) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
        }
      }
    }
    return dp[0][len - 1];
  }
}
