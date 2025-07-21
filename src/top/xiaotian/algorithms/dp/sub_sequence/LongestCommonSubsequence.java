package top.xiaotian.algorithms.dp.sub_sequence;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class LongestCommonSubsequence {
    /**
     * 记忆化搜索
     * 超时
     */
    private int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * abcde    t1[i]与t2[j]相同，返回t1[0...i-1]与t2[0...j-1]的最长公共子序列长度+本次匹配的1
         * ace                不相同，返回max(t1[0...i-1]与t2[0...j], t1[0...i]与t2[0...j-1])
         */
        int len1 = text1.length(), len2 = text2.length();
        memo = new int[len1 + 1][len2 + 1];
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        return help(chars1, len1, chars2, len2);
    }

    private int help(char[] chars1, int i, char[] chars2, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        if (chars1[i - 1] == chars2[j - 1]) {
            memo[i][j] = help(chars1, i - 1, chars2, j - 1) + 1;
        } else {
            memo[i][j] = Math.max(help(chars1, i - 1, chars2, j), help(chars1, i, chars2, j - 1));
        }
        return memo[i][j];
    }

    // 动态规划-多使用一位空间，减少初始化操作
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        // dp[i][j]表示str1的前i个字符和str2的前j个字符的最长公共子序列的长度，不要求以`i`和`j`结尾（因为不要求连续，所以当前字符不相等时，可以取之前非连续的状态）
        // 初始化：dp[0][j]和dp[i][0]均为0，因为空字符串和任何字符串的最长公共子序列都是0
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 状态压缩
     * 当前状态只依赖于左上 左 上三个状态
     */
    public int longestCommonSubsequence3(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        int[] dp = new int[len2 + 1];
        for (int i = 1; i <= len1; i++) {
            int leftUp = 0; // 保存左上角的值（dp[i-1][j-1]）
            for (int j = 1; j <= len2; j++) {
                int temp = dp[j]; // 保存当前值（dp[i-1][j]）用于下次迭代

                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[j] = leftUp + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                // 更新左上角值为当前单元格的原始值（即dp[i-1][j]）
                leftUp = temp;
            }
        }
        return dp[len2];
    }
}
