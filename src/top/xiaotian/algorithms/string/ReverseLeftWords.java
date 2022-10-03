package top.xiaotian.algorithms.string;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 * <p>
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= k < s.length <= 10000
 */
public class ReverseLeftWords {
  public String reverseLeftWords(String s, int n) {
    String right = s.substring(n);
    String left = s.substring(0, n);
    return right + left;
  }

  /**
   * 面试题 01.09. 字符串轮转
   * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
   * <p>
   * 示例1:
   * <p>
   * 输入：s1 = "waterbottle", s2 = "erbottlewat"
   * 输出：True
   */
  public boolean isFlipedString(String s1, String s2) {
    if (s1.equals(s2)) {
      return true;
    }
    for (int i = 0; i < s1.length(); i++) {
      String rotateStr = s1.substring(i) + s1.substring(0, i);
      if (s2.equals(rotateStr)) {
        return true;
      }
    }

    return false;
  }

  public boolean isFlipedString2(String s1, String s2) {
    if (s1.equals(s2)) {
      return true;
    }
    if (s1.length() != s2.length()) {
      return false;
    }
    // 延长s1至两倍
    s1 += s1;
    int i = 0;
    int j = 0;
    char[] chars1 = s1.toCharArray();
    char[] chars2 = s2.toCharArray();
    // 遍历s1，s2，看能否在s1中找到s2
    while (i < chars1.length && j < chars2.length) {
      if (chars1[i] == chars2[j]) {
        i++;
        j++;
      } else {
        i++;
        j = 0;
      }
    }
    return j == chars2.length;
  }
}
