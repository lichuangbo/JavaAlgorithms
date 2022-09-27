package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 242. 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram" 输出: true
 *
 * @author lichuangbo
 * @date 2022/9/27
 */
public class ValidAnagram {

  public boolean isAnagram(String s, String t) {
    int[] freq = new int[26];
    char[] chars1 = s.toCharArray();
    for (char ch : chars1) {
      freq[ch - 'a']++;
    }

    char[] chars2 = t.toCharArray();
    for (char ch : chars2) {
      freq[ch - 'a']--;
    }

    for (int item : freq) {
      if (item != 0) {
        return false;
      }
    }
    return true;
  }

  public boolean isAnagram2(String s, String t) {
    Map<Character, Integer> freqMap = new HashMap<>();
    char[] chars1 = s.toCharArray();
    char[] chars2 = t.toCharArray();
    for (char ch : chars1) {
      freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
    }

    for (char ch : chars2) {
      freqMap.put(ch, freqMap.getOrDefault(ch, 0) - 1);
    }
    for (Entry<Character, Integer> entry : freqMap.entrySet()) {
      if (entry.getValue() != 0) {
        return false;
      }
    }
    return true;
  }

}
