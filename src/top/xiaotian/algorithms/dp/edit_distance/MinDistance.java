package top.xiaotian.algorithms.dp.edit_distance;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * <p>
 * 每步 可以删除任意一个字符串中的一个字符。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * 示例  2:
 * <p>
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
public class MinDistance {
    public int minDistance(String word1, String word2) {
        char[] chars1 = word1.toCharArray(), chars2 = word2.toCharArray();
        int len1 = chars1.length, len2 = chars2.length;
        // dp[i][j]表示以chars1[i-1]结尾，chars2[j-1]结尾的两个字符串，要达到相等需要的最小步数
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化：dp[0][j]表示word2可以删除所有字符串,dp[i][0]表示word1可以删除所有字符串,dp[0][0]=0
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 删除word2字符来匹配word1，删除word1字符来匹配word2，两者取最小然后因为删除了一次+1
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
