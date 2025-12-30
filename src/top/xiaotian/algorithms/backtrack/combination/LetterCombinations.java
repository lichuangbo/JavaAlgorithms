package top.xiaotian.algorithms.backtrack.combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例:
 * <p>
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/28 93 131  46
 */
public class LetterCombinations {

    /**
     * 时间    O(3^N*4^M)，N=对应3个字母的数字个数，M=对应4个字母的数字个数
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Character, String> map = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        List<String> resList = new ArrayList<>();
        help(map, digits.toCharArray(), 0, resList, new StringBuilder());
        return resList;
    }

    // sb保存从digits[0...index-1]翻译得到的一个字母字符串，然后继续寻找和digits[index]匹配的字母
    // (index指向的是digits字符串的遍历进度，比如说2的按键组合遍历完了，继续看3的按键组合)
    private void help(Map<Character, String> map, char[] chars, int index, List<String> resList, StringBuilder curr) {
        if (index == chars.length) {
            resList.add(curr.toString());
            return;
        }

        String numStr = map.get(chars[index]);
        char[] nums = numStr.toCharArray();
        for (char num : nums) {
            curr.append(num);
            help(map, chars, index + 1, resList, curr);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
