package top.xiaotian.algorithms.dfs;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是
 * "bccfi", 1 2 2 5 8
 * "bwfi",  1 22 5 8
 * "bczi",  1 2 25 8
 * "mcfi"   12 2 5 8
 * 和"mzi"  12 25 8
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num < 231
 */
public class TranslateNum {
  int count = 0;

  public int translateNum(int num) {
    // 数字在9以内的只有一种含义
    if (num <= 9) {
      return 1;
    }

    char[] chars = String.valueOf(num).toCharArray();
    help(chars, 0);
    return count;
  }

  private void help(char[] chars, int index) {
    if (index == chars.length) {
      count++;
      return;
    }

    // 逐字计算
    help(chars, index + 1);
    // 组合计算：以1打头的都会出现两种含义   以2打头的第二个数字在[1,5]之间的也会出现两种含义
    if (index + 1 < chars.length && (chars[index] == '1' || (chars[index] == '2' && chars[index + 1] <= '5'))) {
      help(chars, index + 2);
    }
  }

  // 动态规划
  public int translateNum2(int num) {
    char[] chars = String.valueOf(num).toCharArray();
    int len = chars.length;
    // dp[i]表示考虑[0, i]数组区间，可以翻译的方式
    int[] dp = new int[len + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= len; i++) {
      // 当可以组合计算时：逐字翻译的方式 + 组合翻译的方式
      if (chars[i - 2] == '1' || (chars[i - 2] == '2' && chars[i - 1] <= '5')) {
        dp[i] = dp[i - 1] + dp[i - 2];
      } else {// 只能逐字计算
        dp[i] = dp[i - 1];
      }
    }
    return dp[len];
  }
}
