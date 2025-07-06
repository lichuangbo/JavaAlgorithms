package top.xiaotian.algorithms.dp.split;

import java.util.List;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅由小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 * @author lichuangbo
 * @date 2022/8/16
 */
public class WordBreak {

    /**
     * 记忆化搜索
     * 从字符串的起始位置开始，尝试所有可能的分割点，将字符串分成两部分：当前子串和剩余部分。如果当前子串在字典中，并且剩余部分也可以被拆分（递归判断）,就找到了一组解直接返回
     *
     * 存在重复子问题
     * 考虑 s = "aaaa", wordDict = ["a", "aa"]
     *            aaaa
     *       /   |   |    \
     *     aaa  aa   a    空    第一轮分割，剩余子串
     *    / | \
     *   aa a 空                第二轮分割，剩余子串
     * 剩余子串aa存在重复计算
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // memo[i]表示从索引i开始到字符串末尾的子串是否可以被拆分 0表示未计算，1表示计算过可行，-1表示计算过不可行
        int[] memo = new int[s.length()];
        return help(s, wordDict, 0, memo);
    }

    private boolean help(String s, List<String> wordDict, int index, int[] memo) {
        // 当当前索引指到字符串末尾，结束
        if (index == s.length()) {
            return true;
        }
        if (memo[index] != 0) {
            return memo[index] == 1;
        }

        for (int i = index; i < s.length(); i++) {
            String subWord = s.substring(index, i + 1);
            if (wordDict.contains(subWord) && help(s, wordDict, i + 1, memo)) {
                memo[index] = 1;
                return true;
            }
        }
        memo[index] = -1;
        return false;
    }

    /**
     * 动态规划
     * dp[i]表示字符串[0, i-1]区间内是否可以拆分为字典中的值
     * 状态转移方程：
     *   为了求解 dp[i]，我们需要检查所有可能的 j（0 <= j < i），如果：
     *      1. 前 j 个字符可以被拆分（即 dp[j] 为 true），
     *      2. 并且子串 s[j:i]（即从索引 j 到 i-1 的子串）在字典中，
     *   那么 dp[i] 就为 true。
     *   即：dp[i] = 存在某个 j (0 <= j < i)，使得 dp[j] 为 true 且 s.substring(j, i) 在 wordDict 中。
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        // 初始化：空字符串可以被拆分，初始化为true
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                String subWord = s.substring(j, i);
                if (dp[j] && wordDict.contains(subWord)) {
                    dp[i] = true;
                    // 后续的不用在计算了
                    break;
                }
            }
        }
        return dp[len];
    }
}
