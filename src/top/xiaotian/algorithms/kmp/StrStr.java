package top.xiaotian.algorithms.kmp;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 */
public class StrStr {
  public int strStr(String haystack, String needle) {
    char[] chars1 = haystack.toCharArray();
    char[] chars2 = needle.toCharArray();
    int len1 = chars1.length;
    int len2 = chars2.length;
    int[] next = new int[len2];
    getNext(next, chars2);

    int j = 0;
    for (int i = 0; i < len1; i++) {
      while (j > 0 && chars2[j] != chars1[i]) {
        j = next[j - 1];
      }
      if (chars2[j] == chars1[i]) {
        j++;
      }
      if (j == len2) {
        return i - len2 + 1;
      }
    }
    return -1;
  }

  private void getNext(int[] next, char[] chars) {
    int j = 0;
    next[0] = 0;
    for (int i = 1; i < chars.length; i++) {
      while (j > 0 && chars[j] != chars[i]) {
        j = next[j - 1];
      }
      if (chars[j] == chars[i]) {
        j++;
      }
      next[i] = j;
    }
  }
}
