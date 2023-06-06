package top.xiaotian.algorithms.twoPointer.slidingWindow;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3  438 76
 */
public class LongestSubstring {
    /**
     * 滑动窗口: 滑动窗口走一遍，同时用变量存储最大窗口值
     * 时间O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        // 滑动窗口左闭右闭，初始大小为0
        int l = 0, r = -1;
        int res = 0;
        char[] chars = s.toCharArray();
        while (l < chars.length) {
            if (r + 1 < chars.length && freq[chars[r + 1]] == 0) {// 右边界+1位置元素与滑动窗口内的元素不重复
                r++;
                freq[chars[r]]++;
            } else {// 出现重复，缩小窗口
                freq[chars[l]]--;
                l++;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        int[] freq = new int[256];
        char[] chars = s.toCharArray();
        int res = 0;
        for (int l = 0, r = 0; r < chars.length; r++) {
            freq[chars[r]]++;
            // while控制chars[r]没有出现重复  比如pww，当r指向2时，l移动一次是不行的，ww仍然是重复字符
            while (freq[chars[r]] > 1) {
                freq[chars[l]]--;
                l++;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
