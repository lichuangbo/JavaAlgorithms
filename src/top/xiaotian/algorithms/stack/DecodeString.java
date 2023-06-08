package top.xiaotian.algorithms.stack;

import java.util.Stack;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 */
public class DecodeString {

    /**
     * 辅助栈
     * 时间O(n),s字符串只需遍历一次
     * 空间O(n)，遇见一个[multi和last_res就要入栈，和n中[成正相关
     */
    public String decodeString(String s) {
        /**
         * 3[a2[c]]
         * 1. a2[c]的结果拼接三次    accaccacc,作为最外层循环次数3，需要将3入栈（3, ""）
         * 2. a与2[c]的结果拼接起来  acc，a需要与嵌套结构拼接，a需要入栈,存储的a是last_res的含义（3, ""）（2, "a"）
         * 3. c拼接两次             cc
         *    遇见第一个]，需要出栈，操作数栈顶2 * c = cc
         *    字符串栈栈顶a last_res,和2[c] curr_res结果拼接起来，组成acc
         *    遇见第二个]，继续出栈3 * curr_res = accaccacc
         * 注意：拼接好后不用再入栈，比如2[c]拼接得到cc，此时需要和前边的a组合起来才行，如果cc入栈后续处理就复杂了
         */
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        Stack<String> stack_str = new Stack<>();
        Stack<Integer> stack_multi = new Stack<>();
        int multi = 0;
        for (char ch : chars) {
            if (ch >= '0' && ch <= '9') {
                multi = multi * 10 + (ch - '0');
            } else if (ch == '[') {
                stack_multi.add(multi);
                multi = 0;

                stack_str.add(sb.toString());
                sb = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder tmp = new StringBuilder();
                int num = stack_multi.pop();
                for (int i = 0; i < num; i++) {
                    tmp.append(sb);
                }
                sb = new StringBuilder(stack_str.pop() + tmp);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public String decodeString2(String s) {
        return dfs(s.toCharArray(), 0)[0];
    }

    //当String[]的长度为2时，第二个元素为解码出的子字符串，第一个元素为解码出的子字符串的最后字符']'在s中的下标
    //当String[]的长度为1时，数组中只存储了解码出的字符串
    //dfs语义：对字符串s从i往后的子串进行解码，并存在数组里；必要的时候还会在数组里存子串最后那个']'的下标
    private String[] dfs(char[] chars, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < chars.length) {
            char ch = chars[i];
            if (ch >= '0' && ch <= '9') {
                multi = multi * 10 + (ch - '0');
            } else if (ch == '[') {
                String[] tmp = dfs(chars, i + 1);
                i = Integer.parseInt(tmp[0]);
                // 按照数目拼接字符，形成字符串
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (ch == ']') {
                return new String[]{String.valueOf(i), res.toString()};
            } else {
                res.append(ch);
            }
            i++;
        }
        return new String[]{res.toString()};
    }
}
