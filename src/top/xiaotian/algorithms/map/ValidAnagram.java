package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 * <p>
 * <p>
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * @author lichuangbo
 * @date 2022/9/27
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
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
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        for (char ch : t.toCharArray()) {
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
