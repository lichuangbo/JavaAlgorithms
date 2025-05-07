package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 */
public class MinimumWindowSubstring {
    /**
     * 滑动窗口
     * 时间 O(n)
     * 空间 O(k) k为t字符串中不同字符的数量
     * <p>
     * 解析：
     * 1. 题目中 “对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量”的说明，表示需要统计t字符串中字符出现频率
     * 2. 连续子串问题，可以考虑滑动窗口思路
     */
    public String minWindow(String s, String t) {
        // 统计t字符串中字符出现的频次
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        char[] sArr = s.toCharArray();
        // 窗口，key是字符，value是字符出现的频次
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        // s子字符串在t字符串中频次相同的字符个数
        int sameFreqCh = 0;
        // s子字符串已覆盖t时，s子字符串的开始下标
        int start = 0;
        // s子字符串已覆盖t时，最短的子字符串长度
        int minLen = Integer.MAX_VALUE;
        while (right < sArr.length) {
            char rightCh = sArr[right];
            // 扩展右边界
            if (freqMap.containsKey(rightCh)) {// 如果t字符串中不包含扩展的边界字符，不纳入窗口，减少无用循环
                window.put(rightCh, window.getOrDefault(rightCh, 0) + 1);
                if (window.get(rightCh).equals(freqMap.get(rightCh))) {
                    sameFreqCh++;
                }
            }

            // 收缩左边界
            while (sameFreqCh == freqMap.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                char leftCh = sArr[left];
                if (freqMap.containsKey(leftCh)) {// 如果t字符串中不包含收缩的边界字符，跳过
                    if (window.get(leftCh).equals(freqMap.get(leftCh))) {
                        sameFreqCh--;
                    }
                    window.put(leftCh, window.get(leftCh) - 1);
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        String s = minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }
}
