package top.xiaotian.algorithms.dp.palindromic;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class PalindromicSubstrings {

  /**
   * 时间：O(n3)
   */
  public int countSubstrings(String s) {
    int res = 0;
    char[] chars = s.toCharArray();
    int len = s.length();
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len; j++) {
        if (isValid(chars, i, j)) {
          res++;
        }
      }
    }
    return res;
  }

  // 判断字符串[st...en)区间的子字符串是否为回文串
  private boolean isValid(char[] s, int st, int en) {
    while (st < en) {
      if (s[st] != s[en]) {
        return false;
      }
      st++;
      en--;
    }
    return true;
  }

  /**
   * 时间：O(n2)
   * 区间dp, 递推公式：
   * 当s[i]与s[j]不相等，dp[i][j]一定是false。
   * 当s[i]与s[j]相等时，有如下三种情况
   *  情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
   *  情况二：下标i 与 j相差为1，例如aa，也是回文子串
   *  情况三：下标i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，也就是看dp[i + 1][j - 1]是否为true
   */
  public int countSubStrings2(String s) {
    int res = 0;
    int len = s.length();
    char[] chars = s.toCharArray();
    // dp[i][j]表示s字符串[i,j]的子串是否是回文子串
    boolean[][] dp = new boolean[len][len];
    // 初始化 第一列和最后一行
    // 递推公式是从左下角来的，决定了遍历顺序是 从下到上，从左到右遍历
    for (int i = len - 1; i >= 0; i--) {
      // 因为dp[i][j]的定义，所以j一定是大于等于i的
      for (int j = i; j < len; j++) {
        if (chars[i] != chars[j]) {
          dp[i][j] = false;
        } else if (i == j || j - i == 1 || dp[i + 1][j - 1]) {
          res++;
          dp[i][j] = true;
        }
      }
    }
    return res;
  }
}
