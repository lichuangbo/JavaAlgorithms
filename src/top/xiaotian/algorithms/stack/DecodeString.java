package top.xiaotian.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lichuangbo
 * @date 2021/9/28
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]" 输出："aaabcbc" 示例 2：
 *
 * 输入：s = "3[a2[c]]" 输出："accaccacc" 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef" 输出："abcabccdcdcdef" 示例 4：
 *
 * 输入：s = "abc3[cd]xyz" 输出："abccdcdcdxyz"
 */
public class DecodeString {

  /**
   * 时间O(n),s字符串只需遍历一次
   * 空间O(n)，遇见一个[multi和last_res就要入栈，和n中[成正相关
   */
  public String decodeString(String s) {
    StringBuilder res = new StringBuilder();
    int multi = 0;
    /**
     * 3[a2[c]]
     * 1. a2[c]的结果拼接三次    accaccacc,作为最外层循环次数3，需要将3入栈（3, ""）
     * 2. a与2[c]的结果拼接起来  acc，a需要与嵌套结构拼接，a需要入栈,存储的a是last_res的含义（3, ""）（2, "a"）
     * 3. c拼接三次             cc
     *    遇见第一个]，需要出栈，操作数栈顶2 * c = cc
     *    字符串栈栈顶a last_res,和2[c] curr_res结果拼接起来，组成acc
     *    遇见第二个]，继续出栈3 * curr_res = accaccacc
     */
    Deque<Integer> stack_multi = new ArrayDeque<>();
    Deque<String> stack_res = new ArrayDeque<>();
    for (Character c : s.toCharArray()) {
      if (c == '[') {
        stack_multi.addLast(multi);
        stack_res.addLast(res.toString());
        multi = 0;
        res = new StringBuilder();
      } else if (c == ']') {
        StringBuilder tmp = new StringBuilder();
        // 按照数目拼接字符，形成字符串
        int cur_multi = stack_multi.removeLast();
        for (int i = 0; i < cur_multi; i++) {
          tmp.append(res);
        }
        res = new StringBuilder(stack_res.removeLast() + tmp);
      } else if (c >= '0' && c <= '9') {
        // 有可能出现十位数
        multi = multi * 10 + Integer.parseInt(c + "");
      } else {
        res.append(c);
      }
    }
    return res.toString();
  }

  public String decodeString2(String s) {
    return dfs(s, 0)[0];
  }

  //当String[]的长度为2时，第二个元素为解码出的子字符串，第一个元素为解码出的子字符串的最后字符']'在s中的下标
  //当String[]的长度为1时，数组中只存储了解码出的字符串
  //dfs语义：对字符串s从i往后的子串进行解码，并存在数组里；必要的时候还会在数组里存子串最后那个']'的下标
  private String[] dfs(String s, int i) {
    StringBuilder res = new StringBuilder();
    int multi = 0;
    while (i < s.length()) {
      if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
      } else if (s.charAt(i) == '[') {
        String[] tmp = dfs(s, i + 1);
        i = Integer.parseInt(tmp[0]);
        // 按照数目拼接字符，形成字符串
        while (multi > 0) {
          res.append(tmp[1]);
          multi--;
        }
      } else if (s.charAt(i) == ']') {
        return new String[]{String.valueOf(i), res.toString()};
      } else {
        res.append(String.valueOf(s.charAt(i)));
      }
      i++;
    }
    return new String[]{res.toString()};
  }

  public static void main(String[] args) {
    String s = new DecodeString().decodeString("2[2[2[a]]]");
    System.out.println(s);
  }
}
