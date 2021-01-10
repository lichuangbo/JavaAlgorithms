package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        if (s.length() < p.length()) {
            return resList;
        }

        // 统计p出现的频率
        int[] freq = new int[26];
        char[] pArr = p.toCharArray();
        for (int i = 0; i < pArr.length; i++) {
            freq[pArr[i] - 'a']++;
        }

//        int l = 0, r = -1;// 滑动区间：[l...r]
//        while (l < s.length()) {
//            if (r + 1 < s.length() && r - l + 1 < p.length()) {
//                r++;
//            } else {
//                l++;
//            }
//
//            if (r - l + 1 == p.length() && isAnagrams(s.substring(l, r + 1), freq)) {
//                resList.add(l);
//            }
//        }

        // 滑动窗口大小固定，耗时变长了
        int l = 0, r = p.length() - 1;// 滑动区间：[l...r]
        while (r < s.length()) {
            if (isAnagrams(s.substring(l, r + 1), freq)) {
                resList.add(l);
            }
            l++;
            r++;
        }

        return resList;
    }

    // 判断两个字符串是否为互为字母异位词
    private boolean isAnagrams(String a, int[] freq) {
        char[] aArr = a.toCharArray();
        int[] tmp = new int[26];
        for (int i = 0; i < aArr.length; i++) {
            tmp[aArr[i] - 'a']++;
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != tmp[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        List<Integer> anagrams = new FindAnagrams().findAnagrams(s, p);
        System.out.println(anagrams);
    }
}
