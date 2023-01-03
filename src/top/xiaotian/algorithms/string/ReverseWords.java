package top.xiaotian.algorithms.string;

/**
 * 151. 反转字符串中的单词
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "the sky  is blue"
 * 输出："blue is sky the"
 */
public class ReverseWords {
  public String reverseWords(String s) {
    char[] chars = s.toCharArray();
    int low = 0;
    int high = chars.length - 1;
    // 开头空格
    while (chars[low] == ' ') {
      low++;
    }
    // 结尾空格
    while (chars[high] == ' ') {
      high--;
    }
    // 中间空格
    StringBuilder sb = new StringBuilder();
    while (low <= high) {
      if (chars[low] != ' ') {// 不是空格，无条件加入
        sb.append(chars[low]);
      } else {
        if (sb.charAt(sb.length() - 1) != ' ') {// 是空格，但是是第一次出现加入
          sb.append(chars[low]);
        }
      }
      low++;
    }

    chars = sb.toString().toCharArray();
    // 整体翻转
    help(chars, 0, chars.length - 1);

    // 单词翻转
    low = 0;
    high = 0;
    while (low < chars.length) {
      while (high < chars.length && chars[high] != ' ') {
        high++;
      }
      help(chars, low, high - 1);
      low = high + 1;
      high = low;
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
