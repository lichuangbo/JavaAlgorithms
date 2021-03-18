package top.xiaotian.algorithms.dp;

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
    private int[][] mono;
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * abcde    t1[len1]与t2[len2]相同， 返回t1[0...len1-1]与t2[0...len2-1]的最长公共子序列长度+1
         * ace                     不相同，返回max(t1[0...len1-1]与t2[0...len2], t1[0...len1]与t2[0...len2-1])
         */
        int len1 = text1.length(), len2 = text2.length();
        mono = new int[len1 + 1][len2 + 1];
        return help(text1, len1, text2, len2);
    }

    private int help(String text1, int i, String text2, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (mono[i][j] != 0) {
            return mono[i][j];
        }

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            mono[i][j] = help(text1, i - 1, text2, j - 1) + 1;
        } else {
            mono[i][j] = Math.max(help(text1, i - 1, text2, j), help(text1, i, text2, j - 1));
        }
        return mono[i][j];
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
