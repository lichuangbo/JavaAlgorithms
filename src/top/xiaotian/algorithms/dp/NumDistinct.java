package top.xiaotian.algorithms.dp;

import java.util.Arrays;

/**
 * 115. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例 1：
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/17
 */
public class NumDistinct {
    private int[][] mono;
    public int numDistinct(String s, String t) {
        /**
         * s:babgbag    最后一个字符相同，有两种选择：一使用s[i]匹配掉t[j]  二不用s[i]去匹配t[j]，使用i之前的字符去匹配
         * t:bag        结果为两种选择之和
         *              如果字符不相同，只能使用s的前i-1个字符去匹配t，因为t是不能变的
         */
        mono = new int[s.length()][t.length()];
        for (int i = 0; i < mono.length; i++) {
            for (int j = 0; j < mono[0].length; j++) {
                mono[i][j] = -1;
            }
        }
        return help(s, s.length() - 1, t, t.length() - 1);
    }
    // 返回从开头到s[i]的子串中，出现『从开头到t[j]的子串』的次数
    private int help(String s, int i, String t, int j) {
        // t变成空串，不管s有多少个字符，都只有一种匹配方法--删除s的所有字符
        if (j < 0) {
            return 1;
        }
        // s变成空串，但t不是空串，s无法操作
        if (i < 0) {
            return 0;
        }

        if (mono[i][j] != -1) {
            return mono[i][j];
        }
        if (s.charAt(i) == t.charAt(j)) {
            mono[i][j] = help(s, i - 1, t, j - 1) + help(s, i - 1, t, j);
        } else {
            mono[i][j] = help(s, i - 1, t, j);
        }
        return mono[i][j];
    }

    public int numDistinct2(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        // dp状态：以i-1为结尾的s子串中出现以j-1为结尾的t子串的个数
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 1;
        }
        /**
         * dp[i][0]表示s子串可以任意删除字符去匹配t空子串，1
         * dp[0][j]表示s子串为空，但是t子串不空，无法操作0
         * [1, 0, 0, 0, 0, 0, 0],
         * [1, 1, 0, 0, 0, 0, 0],
         * [1, 1, 1, 0, 0, 0, 0],
         * [1, 1, 1, 1, 0, 0, 0],
         * [1, 1, 1, 2, 1, 0, 0],
         * [1, 1, 1, 3, 3, 0, 0],
         * [1, 1, 1, 3, 3, 3, 0],
         * [1, 1, 1, 3, 3, 3, 3]
         */
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[sLen][tLen];
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        NumDistinct numDistinct = new NumDistinct();
        int res = numDistinct.numDistinct2(s, t);
        System.out.println(res);
    }
}
