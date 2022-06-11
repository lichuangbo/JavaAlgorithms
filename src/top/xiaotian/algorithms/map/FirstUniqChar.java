package top.xiaotian.algorithms.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 * <p>
 * 输入：s = ""
 * 输出：' '
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 50000
 */
public class FirstUniqChar {
  public char firstUniqChar(String s) {
    if (s == null || s.trim().length() == 0) {
      return ' ';
    }

    Map<Character, Integer> map = new LinkedHashMap<>();
    char[] chars = s.toCharArray();
    for (char ch : chars) {
      map.compute(ch, (k, v) -> v == null ? 1 : v + 1);
    }
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey();
      }
    }
    return ' ';
  }

  public char firstUniqChar2(String s) {
    if (s == null || s.trim().length() == 0) {
      return ' ';
    }

    int[] freq = new int[26];
    char[] chars = s.toCharArray();
    // 统计字符出现频率
    for (char ch : chars) {
      freq[ch - 'a']++;
    }
    // 再按照字符串顺序，查看某个字符是不是出现1次
    for (char ch : chars) {
      if (freq[ch - 'a'] == 1) {
        return ch;
      }
    }
    return ' ';
  }
}
