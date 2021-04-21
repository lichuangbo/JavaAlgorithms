package top.xiaotian.algorithms.dp;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A" ，
 * 从而得到 "AAA" ，或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
 * <p>
 * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6
 */
public class NumDecodings {
    public int numDecodings(String s) {
        // 226
        // 解码为6，继续解码22的解法   +   解码为26，继续解码2的解法
        if (s.startsWith("0")) {
            return 0;
        }
        int[] mono = new int[s.length()];
        return help(s, s.length() - 1, mono);
    }

    private int help(String s, int index, int[] mono) {
        if (index <= 0) {
            return 1;
        }

        if (mono[index] != 0) {
            return mono[index];
        }

        char prev = s.charAt(index - 1), curr = s.charAt(index);
        int count1 = 0, count2 = 0;
        // 单个解码时（将10,20情况放入复合解码计算）
        /**
         * 为什么要放到复合解码逻辑中？
         * 以210为例，从后向前看当前元素为0，而前边是1，不能拆分，应解码为10再加上继续解码2的可能，总共为1
         */
        if (curr > '0') {
            count1 = help(s, index - 1, mono);
        }
        // 复合解码时
        if (prev == '1' || (prev == '2' && curr <= '6')) {// [11...19] [21...26]可以组成复合解码   +    [10,20]的处理
            count2 = help(s, index - 2, mono);
        }
        mono[index] = count1 + count2;
        return count1 + count2;
    }

    public int numDecodings2(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int[] dp = new int[s.length()];
        dp[0] = 1;
        if ((s.charAt(0) == '1' && s.charAt(1) > '0') || (s.charAt(0) == '2'&& s.charAt(1) > '0' && s.charAt(1) <= '6' )) {// [11...19]  [21...26]
            dp[1] = 2;
        } else if (s.charAt(0) > '2' && s.charAt(1) == '0') {// 30,40等不可解码
            dp[1] = 0;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) > '0') { //（将10,20情况放入复合解码计算）
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length() - 1];
    }


    public static void main(String[] args) {
        int res = new NumDecodings().numDecodings("210");
        System.out.println(res);
    }
}
