package top.xiaotian.algorithms.dp.sub_sequence;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 示例 1:
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/3/18 11:03
 * @Description: 描述:
 */
public class LCS {
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * abcde    t1[len1]与t2[len2]相同， 返回t1[0...len1-1]与t2[0...len2-1]的最长公共子序列长度+1
         * ace                     不相同，返回max(t1[0...len1-1]与t2[0...len2], t1[0...len1]与t2[0...len2-1])
         */
        int len1 = text1.length(), len2 = text2.length();
        memo = new int[len1 + 1][len2 + 1];
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
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

    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        // dp[i][j]表示以str1[i]结尾和以str2[j]结尾的字符串的最长公共子序列的长度
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
}
