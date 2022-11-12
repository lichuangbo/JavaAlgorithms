package top.xiaotian.algorithms.string;

/**
 * 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 */
public class ReverseStr {
  public String reverseStr(String s, int k) {
    char[] chars = s.toCharArray();
    int len = chars.length;
    for (int i = 0; i < len; i += (2 * k)) {
      if (i + k < len) {
        help(chars, i, i + k - 1);
      } else {
        help(chars, i, len - 1);
      }
    }
    return new String(chars);
  }

  private void help(char[] chars, int low, int high) {
    while (low < high) {
      char tmp = chars[low];
      chars[low] = chars[high];
      chars[high] = tmp;
      low++;
      high--;
    }
  }
}
