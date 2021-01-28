package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/28 93 131  46
 */
public class LetterCombinations {
    private String[] letterMap = {
            "",// 0
            "!@#",
            "abc",// 2
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"// 9
    };

    private List<String> res;

    // 回溯，时间O(3^n)
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        findCombination(digits, 0, "");
        return res;
    }

    // s保存从digits[0...index-1]翻译得到的一个字母字符串
    private void findCombination(String digits, int index, String s) {
        // 当递归到数字串的最后时，说明找到了一组解，加入结果集
        System.out.println(index + " : " + s);// 递归进入前打印
        if (index == digits.length()) {
            res.add(s);
            System.out.println("get " + s);
            return;
        }

        char ch = digits.charAt(index);
        String letters = letterMap[ch - '0'];// 找到当前数字按键对应的字符，开始遍历所有可能的字符
        for (int i = 0; i < letters.length(); i++) {
            System.out.println("digits[" + index + "] = " + ch + " , use " + letters.charAt(i));// 递归调用前打印
            // 基于选定的这一个字符，向下递归：去处理下一个数字键代表的字符
            // 递归语义中，s此时应该传入[0...index]翻译得到的字母字符串
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> res = new LetterCombinations().letterCombinations(digits);
        System.out.println(res);
    }
}
