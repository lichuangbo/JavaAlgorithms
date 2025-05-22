package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 *
 * @author lichuangbo
 * @date 2022/9/28
 */
public class RansomNote {

    // 查找表法
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : ransomNote.toCharArray()) {
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
        for (char ch : magazine.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (char ch : ransomNote.toCharArray()) {
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

    /**
     * 时间 O(max(m,n)) m是ransomNote的长度，n是magazine的长度
     * 空间 O(1)
     * 相比方法2多了一个for循环，还多声明了空间
     */
    public boolean canConstruct3(String ransomNote, String magazine) {
        int[] rFreq = new int[26];
        for (char ch : ransomNote.toCharArray()) {
            rFreq[ch - 'a']++;
        }
        int[] mFreq = new int[26];
        for (char ch : magazine.toCharArray()) {
            mFreq[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (rFreq[i] > 0 && rFreq[i] > mFreq[i]) {
                return false;
            }
        }
        return true;
    }
}
