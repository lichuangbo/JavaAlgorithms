package top.xiaotian.algorithms.string;


/**
 * 面试题 01.03. URL化
 *
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 *
 *
 *
 * 示例 1：
 *
 * 输入："Mr John Smith    ", 13 输出："Mr%20John%20Smith"
 *
 * @author lichuangbo
 * @date 2022/9/30
 */
public class ReplaceSpaces {

  // 代码翻译
  public String replaceSpaces(String S, int length) {
    char[] chars = S.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      char ch = chars[i];
      if (ch == ' ') {
        sb.append("%20");
      } else {
        sb.append(ch);
      }
    }

    return sb.toString();
  }

  // 双指针
  public String replaceSpaces2(String S, int length) {
    // 边界条件
    if (S == null || S.length() == 0) {
      return S;
    }

    char[] chars = S.toCharArray();
    // last指向字符串的末尾（该字符串尾部有足够的空间）
    int last = chars.length - 1;
    // i指向数组末尾
    // 为什么从后向前？因为每一次替换空格，都会导致之后的字符后移，倒序操作减少了字符的移动
    for (int i = length - 1; i >= 0; i--) {
      if (chars[i] == ' ') {
        chars[last--] = '0';
        chars[last--] = '2';
        chars[last--] = '%';
      } else {
        chars[last--] = chars[i];
      }
    }
    return new String(chars, last + 1, chars.length - last - 1);
  }

  /**
   * 剑指 Offer 05. 替换空格
   * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
   *
   *
   *
   * 示例 1：
   *
   * 输入：s = "We are happy."
   * 输出："We%20are%20happy."
   */
  public String replaceSpace(String s) {
    char[] chars = s.toCharArray();
    int oldLen = chars.length;
    char[] newChars = new char[oldLen * 3];
    System.arraycopy(chars, 0, newChars, 0, oldLen);

    int newLen = newChars.length;
    int last = newLen - 1;
    for (int i = oldLen - 1; i >= 0; i--) {
      if (newChars[i] == ' ') {
        newChars[last--] = '0';
        newChars[last--] = '2';
        newChars[last--] = '%';
      } else {
        newChars[last--] = chars[i];
      }
    }
    return new String(newChars, last + 1, newLen - last - 1);
  }
}
