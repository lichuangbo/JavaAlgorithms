package top.xiaotian.algorithms.dp.edit_distance;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/3/18 14:15
 * @Description: 描述:
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        /**
         * s: abc
         * t: ahbgbc
         * 贪心策略：s串中字符b在t串中出现了两次，匹配第一个是不会影响到结果的判断。
         * 例子中，如果选择第二个去匹配的话，它的选择范围变小了bc，而如果选择第一个它的范围是gbc，更容易成功匹配
         */
        int sLen = s.length(), tLen = t.length();
        int i = 0, j = 0;
        while (i < sLen && j < tLen) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == sLen;
    }

    // 动态规划
    public boolean isSubsequence2(String s, String t) {
        // dp[i][j]表示以s[i-1]结尾和以t[j-1]结尾的的相同子序列长度
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int [len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
          for (int j = 1; j <= len2; j++) {
            if (chars1[i - 1] == chars2[j - 1]) {// 在s/t中找到了一个相同的字符，更新dp[i][j]状态
              dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {// 不相同，t字符串删除这个字符，继续判断
              dp[i][j] = dp[i][j - 1];
            }
          }
        }
        return dp[len1][len2] == len1;
    }
}
