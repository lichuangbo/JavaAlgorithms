package top.xiaotian.algorithms.backtrack.split;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6
 */
public class Partition {
    private List<List<String>> resList;

    /**
     * 时间：O(n*2^n),最坏情况下有 2ⁿ 种分割方式,每次分割需要O(n)判断是否是回文串
     * 空间：O(n)，递归栈深度
     */
    public List<List<String>> partition(String s) {
        resList = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return resList;
        }

        help(s, 0, new LinkedList<>());
        return resList;
    }

    /**
     * 树形问题
     *            aab
     *      a/    aa|    aab\
     *     ab      b
     *   a/  ab\ b/√
     *   b
     * b/√
     */
    private void help(String s, int index, LinkedList<String> curr) {
        if (index == s.length()) {
            resList.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            // 只有当尝试截断的字符串是回文串时，才去做后续的递归
            if (isValid(s, index, i)) {
                curr.addLast(s.substring(index, i + 1));
                help(s, i + 1, curr);
                curr.removeLast();
            }
        }
    }

    // 判断s[l, r]区间的字符串是否为回文串
    private boolean isValid(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++; r--;
        }
        return true;
    }


    private boolean[][] dp;

    /**
     * 时间：O(n2+2^n)
     * 空间：O(n2) dp数组保存状态
     * @see top.xiaotian.algorithms.dp.interval.PalindromicSubstrings
     * 使用动规计算子串是否为回文串
     */
    public List<List<String>> partition2(String s) {
        resList = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return resList;
        }

        int n = s.length();
        // 预处理回文判断表, dp[i][j]表示s[i...j]是否为回文串
        dp = new boolean[n][n];

        // 动态规划填充dp表
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        help2(s, 0, new LinkedList<>());
        return resList;
    }

    private void help2(String s, int index, LinkedList<String> curr) {
        if (index == s.length()) {
            resList.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            // 直接使用预处理的结果
            if (dp[index][i]) {
                curr.addLast(s.substring(index, i + 1));
                help2(s, i + 1, curr);
                curr.removeLast();
            }
        }
    }
}
