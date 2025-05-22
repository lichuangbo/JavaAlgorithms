package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class FindAnagrams {

    /**
     * 暴力翻译：在s串中截取相同的长度和p进行比较
     * 时间：O(sLen*pLen)
     */
    public List<Integer> findAnagrams0(String s, String p) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = p.toCharArray();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < chars1.length - chars2.length + 1; i++) {
            if (isValid(chars1, i, i + chars2.length - 1, chars2)) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean isValid(char[] chars1, int st, int en, char[] chars2) {
        int[] freq = new int[26];
        for (int i = 0; i < chars2.length; i++) {
            freq[chars2[i] - 'a']++;
        }

        for (int i = st; i <= en; i++) {
            freq[chars1[i] - 'a']--;
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 滑动窗口
     * 时间复杂度：O(sLen)
     * 空间复杂度：O(1)
     */
    public List<Integer> findAnagrams1(String s, String p) {
        // 先统计p的频次
        int[] pFreq = new int[26];
        for (char ch : p.toCharArray()) {
            pFreq[ch - 'a']++;
        }

        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        char[] sArr = s.toCharArray();
        int[] sFreq = new int[26];
        while (right < sArr.length) {
            char rightCh = sArr[right];
            sFreq[rightCh - 'a']++;
            if (right - left + 1 == p.length()) {
                if (check(pFreq, sFreq)) {
                    res.add(left);
                }
                sFreq[sArr[left] - 'a']--;
                left++;
            }
            right++;
        }
        return res;
    }

    /**
     * 比较两个频次数组是否完全相同
     */
    private boolean check(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

}
