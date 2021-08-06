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

    /**
     * 窗口移动
     * 时间：频次统计O(pLen + (sLen-pLen+1)*pLen),滑动窗口O(sLen-pLen+1)
     * 空间：频次数组O(26 + (sLen-pLen+1)*26)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return resList;
        }

        // 统计p出现的频率
        int[] freq = calcStrFreq(p);

        // 1. 滑动窗口
        int l = 0, r = -1;
        while (l < sLen - pLen + 1) {
            if (r + 1 < sLen && r - l + 1 < pLen) {
                r++;
            } else {
                l++;
            }

            if (r - l + 1 == pLen && isAnagrams(s.substring(l, r + 1), freq)) {
                resList.add(l);
            }
        }

        // 2. 固定窗口
//        int l = 0, r = p.length() - 1;// 滑动区间：[l...r]
//        while (r < s.length()) {
//            if (isAnagrams(s.substring(l, r + 1), freq)) {
//                resList.add(l);
//            }
//            l++;
//            r++;
//        }

        return resList;
    }

    // 判断判断字符串字母频次和给定频次是否一致
    private boolean isAnagrams(String a, int[] freq) {
        int[] aFreq = calcStrFreq(a);

        for (int i = 0; i < aFreq.length; i++) {
            if (aFreq[i] != freq[i]) {
                return false;
            }
        }
        return true;
    }

    // 计算字符串中字母出现的频率
    private int[] calcStrFreq(String str) {
        char[] strArr = str.toCharArray();
        int[] tmp = new int[26];
        for (int i = 0; i < strArr.length; i++) {
            tmp[strArr[i] - 'a']++;
        }
        return tmp;
    }

    /**
     * 时间：O(n)
     * 空间：O(C)
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return res;
        }

        // 频率统计
        int[] sFreq = new int[26];
        int[] pFreq = new int[26];
        for (int i = 0; i < pLen; i++) {
            sFreq[s.charAt(i) - 'a']++;
            pFreq[p.charAt(i) - 'a']++;
        }

        for (int i = -1; i < sLen - pLen; i++) {
            if (i >= 0) {
                sFreq[s.charAt(i) - 'a']--;
                sFreq[s.charAt(i + pLen) - 'a']++;
            }
            boolean isAnagrams = true;
            for (int j = 0; j < 26; j++) {
                isAnagrams &= sFreq[j] == pFreq[j];
                if (!isAnagrams) break;
            }
            if (isAnagrams) res.add(i + 1);
        }
        return res;
    }
}
