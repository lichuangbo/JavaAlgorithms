package top.xiaotian.algorithms.dp.sub_sequence;

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
   * 时间: O(n2)
   *
   * @param s
   * @return
   */
  public int countSubStrings2(String s) {
    int len = s.length(), res = 0;
    // https://leetcode-cn.com/problems/palindromic-substrings/solution/liang-dao-hui-wen-zi-chuan-de-jie-fa-xiang-jie-zho/    奇偶中心点产生的原因
    // https://leetcode-cn.com/problems/palindromic-substrings/solution/hui-wen-zi-chuan-by-leetcode-solution/   回文左右起始位置推算
    // 中心点,奇数中心点len个，偶数中心点len-1个
    for (int c = 0; c < 2 * len - 1; c++) {
      int l = c / 2, r = l + c % 2;
      while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
        l--;
        r++;
        res++;
      }
    }
    return res;
  }

  /**
   * 时间：O(n2)
   * 递推公式：
   * 当s[i]与s[j]不相等，dp[i][j]一定是false。
   * 当s[i]与s[j]相等时，有如下三种情况
   * 情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
   * 情况二：下标i 与 j相差为1，例如aa，也是文子串
   * 情况三：下标i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是
   * i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
   */
  public int countSubStrings3(String s) {
    int res = 0;
    int len = s.length();
    char[] chars = s.toCharArray();
    // dp[i][j]表示区间范围[i,j]的子串是否是回文子串
    boolean[][] dp = new boolean[len][len];
    for (int i = len - 1; i >= 0; i--) {  // 从下到上，从左到右遍历
      for (int j = 0; j < len; j++) {
        if (chars[i] != chars[j]) {
          dp[i][j] = false;
        } else {
          if (i == j) {
            res++;
            dp[i][j] = true;
          } else if (j - i == 1) {
            res++;
            dp[i][j] = true;
          } else if (dp[i + 1][j - 1]) {
            // dp[i+1][j-1]在dp[i][j]左下方，而且dp[i][j]的更新也是根据dp[i+1][j-1]状态而定的，需要从下往上，从左到右遍历
            res++;
            dp[i][j] = true;
          }
        }
      }
    }
    return res;
  }
}
