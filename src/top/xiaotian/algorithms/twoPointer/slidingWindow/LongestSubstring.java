package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。(s由英文字母、数字、符号和空格组成)
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3  438 76
 */
public class LongestSubstring {
    /**
     * 滑动窗口: 滑动窗口走一遍，同时用变量存储最大窗口值
     * 时间O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 记录元素是否重复，0是 1否
        int[] freq = new int[256];
        int l = 0, r = -1;
        int res = 0;
        char[] chars = s.toCharArray();
        while (l < chars.length) {
            if (r + 1 < chars.length && freq[chars[r + 1]] == 0) {// 右边界+1位置元素与滑动窗口内的元素不重复
                r++;
                freq[chars[r]]++;
            } else {
                freq[chars[l]]--;
                l++;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        // 划定当前窗口的坐标为(start,i],左开右闭
        int max = 0, start = -1;
        // 使用哈希表map统计各字符最后一次出现的索引位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);

            // 当字符在map中已经存储时，需要判断其索引值index和当前窗口start的大小以确定是否需要对start进行更新:
            // 当index>start时，start更新为当前的index,否则保持不变
            if (map.containsKey(tmp)) {
                start = Math.max(start, map.get(tmp));
            }
            map.put(tmp, i);

            max = Math.max(max, i - start);
        }
        return max;
    }
}
