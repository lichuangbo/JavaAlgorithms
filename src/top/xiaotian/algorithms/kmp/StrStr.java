package top.xiaotian.algorithms.kmp;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 * <p>
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        char[] chars1 = haystack.toCharArray();
        char[] chars2 = needle.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[] next = new int[len2];
        getNext(next, chars2);

        int j = 0;
        for (int i = 0; i < len1; i++) {
            while (j > 0 && chars2[j] != chars1[i]) {
                j = next[j - 1];
            }
            if (chars2[j] == chars1[i]) {
                j++;
            }
            if (j == len2) {
                return i - len2 + 1;
            }
        }
        return -1;
    }

    private void getNext(int[] next, char[] chars) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < chars.length; i++) {
            while (j > 0 && chars[j] != chars[i]) {
                j = next[j - 1];
            }
            if (chars[j] == chars[i]) {
                j++;
            }
            next[i] = j;
        }
    }


    public int strStr2(String haystack, String needle) {
        int len1 = haystack.length(), len2 = needle.length();
        char[] s = haystack.toCharArray(), p = needle.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= len1 - len2; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int m = i, n = 0;
            while (n < len2 && s[m] == p[n]) {
                m++;
                n++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (n == len2) return i;
        }
        return -1;
    }
}
