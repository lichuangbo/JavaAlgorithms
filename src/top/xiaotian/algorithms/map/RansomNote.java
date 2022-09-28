package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b" 输出：false
 *
 * 提示：
 *
 * 1 <= ransomNote.length, magazine.length <= 105 ransomNote 和 magazine 由小写英文字母组成
 *
 * @author lichuangbo
 * @date 2022/9/28
 */
public class RansomNote {

  // 查找表法
  public boolean canConstruct(String ransomNote, String magazine) {
    Map<Character, Integer> map = new HashMap<>();
    char[] chars1 = magazine.toCharArray();
    for (char ch : chars1) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

    char[] chars2 = ransomNote.toCharArray();
    for (char ch : chars2) {
      if (map.containsKey(ch)) {
        map.put(ch, map.get(ch) - 1);
        if (map.get(ch) < 0) {
          return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }

  // 由于只有小写字母，可以使用频率数组优化空间
  public boolean canConstruct2(String ransomNote, String magazine) {
    int[] freq = new int[26];
    char[] chars1 = magazine.toCharArray();
    for (char ch : chars1) {
      freq[ch - 'a']++;
    }

    char[] chars2 = ransomNote.toCharArray();
    for (char ch : chars2) {
      if (freq[ch - 'a'] > 0) {
        freq[ch - 'a']--;
        if (freq[ch - 'a'] < 0) {
          return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }
}
