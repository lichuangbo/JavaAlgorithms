package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
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
     * 时间复杂度：O(pLen)+O(C*sLen)
     * 空间复杂度：O(C)
     */
    public List<Integer> findAnagrams1(String s, String p) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = p.toCharArray();
        int sLen = chars1.length;
        int pLen = chars2.length;
        int[] sFreq = new int[26];
        int[] pFreq = new int[26];
        // 先统计p的频次
        for (int i = 0; i < pLen; i++) {
            pFreq[chars2[i] - 'a']++;
        }

        /**
         * s="abab",p="ab"
         * l=0,r=0 [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
         * l=0,r=1 [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  √
         * l=0,r=2 [2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  窗口开始缩小
         * l=1,r=2 [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  √ 窗口缩小完毕
         * l=1,r=3 [1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  窗口开始缩小
         * l=2,r=3 [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  √ 窗口缩小完毕
         * l=2,r=4 退出
         */
        List<Integer> ans = new ArrayList<>();
        // l,r双指针实现滑动窗口
        for (int l = 0, r = 0; r < sLen; r++) {
            // r指针不断自增，同时将r指针指向的字符计入频次数组中
            sFreq[chars1[r] - 'a']++;
            // 如果维护的窗口大小已经超过了pLen，l指针开始移动（窗口缩小），同时将l指针指向的字符从频次数组中移除
            if (r - l + 1 > pLen) {
                sFreq[chars1[l] - 'a']--;
                l++;
            }

            // 检查两个频次数组是否完全一致，一致的话加入结果集
            if (check(sFreq, pFreq)) {
                ans.add(l);
            }
        }
        return ans;
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


    /**
     * 优化check操作
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char[] chars1 = s.toCharArray();
        char[] chars2 = p.toCharArray();
        int sLen = chars1.length;
        int pLen = chars2.length;
        int[] freq = new int[26];
        // 先统计p的频次
        for (int i = 0; i < pLen; i++) {
            freq[chars2[i] - 'a']++;
        }

        // 统计p字符串中不同字符个数
        int a = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                a++;
            }
        }
        for (int l = 0, r = 0, b = 0; r < sLen; r++) {
            // 往窗口增加字符，进行词频的抵消操作，如果抵消后词频为 0，说明有一个新的字符词频与 p 完全相等
            if (--freq[chars1[r] - 'a'] == 0) {
                b++;
            }
            // 若窗口长度超过规定，将窗口左端点右移，执行词频恢复操作，如果恢复后词频为 1（恢复前为 0），说明少了一个词频与 p 完全性相等的字符
            if (r - l + 1 > pLen) {
                if (++freq[chars1[l++] - 'a'] == 1) {
                    b--;
                }
            }
            if (b == a) {
                ans.add(l);
            }
        }
        return ans;
    }
}
