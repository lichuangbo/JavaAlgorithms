package top.xiaotian.algorithms.math;

/**
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 *
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12 输出：5
 *
 * 示例 2：
 *
 * 输入：n = 13 输出：6
 *
 *
 *
 * 限制：
 *
 * 1 <= n < 2^31
 */
public class CountDigitOne {

  // 数据规模太大，递归做不了    n=3184191
  public int countDigitOne(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }

    int num = 0;
    int tmp = n;
    while (tmp > 0) {
      if (tmp % 10 == 1) {
        num++;
      }
      tmp = tmp / 10;
    }
    return countDigitOne(n - 1) + num;
  }

  public int countDigitOne2(int n) {
    int digit = 1, res = 0;
    int high = n / 10, cur = n % 10, low = 0;
    while (high != 0 || cur != 0) {
      if (cur == 0) {
        res += high * digit;
      } else if (cur == 1) {
        res += high * digit + low + 1;
      } else {
        res += (high + 1) * digit;
      }
      low += cur * digit;
      cur = high % 10;
      high /= 10;
      digit *= 10;
    }
    return res;
  }
}
